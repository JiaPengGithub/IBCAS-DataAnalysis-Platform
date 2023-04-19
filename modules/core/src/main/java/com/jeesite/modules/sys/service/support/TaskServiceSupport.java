/**
 * Copyright (c)
 * No deletion without permission, or be held responsible to law.
 */
package com.jeesite.modules.sys.service.support;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import com.jeesite.common.utils.RunPythonUtil;
import com.jeesite.common.utils.UUIDUtil;
import com.jeesite.modules.sys.dao.TaskDao;
import com.jeesite.modules.sys.entity.RunTask;
import com.jeesite.modules.sys.entity.Task;
import com.jeesite.modules.sys.entity.TaskRunItem;
import com.jeesite.modules.sys.entity.TaskRunLog;
import com.jeesite.modules.sys.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;

import java.io.File;
import java.util.*;

import static com.jeesite.common.utils.RunPythonUtil.runPythonCommand;
import static com.jeesite.common.utils.RunPythonUtil.runPythonCommandByAbsolutePath;

@Service
public class TaskServiceSupport implements TaskService {

    @Autowired
    private TaskDao taskDao;

    public List<Task> findOne() {
        List<Task> all = taskDao.findAll();
        return all;
    }

    public void save(Task task) {
        if(task.getTaskNum() == null || "".equals(task.getTaskNum())) {
            task.setTaskNum(System.currentTimeMillis()+"");
        }
        task.setUpdateTime(new Date());
        List<Task> taskList = taskDao.findTaskByIds(Arrays.asList(task.getTaskNum()));
        if(taskList.size() == 0) {
            taskDao.save(task);
        } else {
            taskDao.updateTask(task);
        }
    }

    public void delete(String taskNum) {
        taskDao.delete(taskNum);
    }

    public void runTask(String batchNo, String runTaskMan, String runRemark, List<RunTask> runTaskList) {

//        System.out.println(batchNo);
//        System.out.println(runTaskList);

        // 1. 执行日志, db js_task_run_log
        String runId = UUIDUtil.getUUID();
        Date currDate = new Date();
        TaskRunLog taskRunLog = new TaskRunLog(
                runId, runTaskMan, batchNo, currDate, runRemark, currDate
        );
        taskDao.saveTaskRunLog(taskRunLog);

        // 3. 初始化item
        List<TaskRunItem> taskRunItemList = new ArrayList<>();
        for(RunTask runTask : runTaskList) {
            TaskRunItem taskRunItem = new TaskRunItem(
                    UUIDUtil.getUUID(), runId, runTask.getTaskNum(), TaskRunItem.StatusEnum.WAIT.getValue(),
                    null, null, runTask.getInputPathStr(), runTask.getOutputPathStr(), currDate
            );
            taskRunItemList.add(taskRunItem);
//            runTask.getScriptName();
        }
        taskDao.saveTaskItemList(taskRunItemList);

        // 2. 开始执行
        runTaskLogic(taskRunItemList);

        System.out.println("run.");

    }

    /**
     * 执行逻辑
     *  1. 生成代码文件
     *  2. 并开始执行
     *  3. 更新db item 状态
     *  4. 删除代码文件
     */
    private void runTaskLogic(List<TaskRunItem> taskRunItemList) {

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for(TaskRunItem taskRunItem : taskRunItemList) {
                                // TODO 超时退出机制
//                                runPythonCommand("demo.py");

                                // 1. 生成临时代码文件
                                String taskNum = taskRunItem.getTaskNum();
                                List<Task> taskList = taskDao.findTaskByIds(Arrays.asList(taskNum));
                                if(taskList.size() == 0) {
                                    throw new RuntimeException("您执行了已被删除的任务，请您刷新界面重新执行。");
                                }
                                Task task = taskList.get(0);
                                String pyFileName = task.getTaskNum()+"s"+System.nanoTime()+".py";
                                FileWriter writer = new FileWriter("./script_code/"+pyFileName);
                                Base64.Decoder decoder = Base64.getDecoder();
                                String code = new String(decoder.decode(task.getCode()));
                                // 替换占位符
                                code = code.replaceAll("_inputPath_", taskRunItem.getInputPath());
                                code = code.replaceAll("_outputPath_", taskRunItem.getOutputPath());
                                writer.write(code);
                                String absolutePath = writer.getFile().getAbsolutePath();
                                System.out.println("absolutePath = " + absolutePath);

                                // 2. 提交任务 & 更新状态(runing)
//                                runPythonCommand("demo.py");
                                taskRunItem.setTaskStatus(TaskRunItem.StatusEnum.RUNING.getValue());
                                taskRunItem.setStartTime(new Date());
                                System.out.println(taskRunItem.getStartTime());
                                taskDao.updateTaskItem(taskRunItem);
                                RunPythonUtil.RunStatusEnum runStatusEnum = runPythonCommandByAbsolutePath(absolutePath, pyFileName);

                                // 3. 更新状态 (结束 or 异常)
                                if(runStatusEnum.getValue() == RunPythonUtil.RunStatusEnum.OK.getValue()) {
                                    taskRunItem.setTaskStatus(TaskRunItem.StatusEnum.OVER.getValue());
                                } else {
                                    taskRunItem.setTaskStatus(TaskRunItem.StatusEnum.ERROR.getValue());
                                }
                                taskRunItem.setEndTime(new Date());
                                System.out.println(taskRunItem.getEndTime());
                                taskDao.updateTaskItem(taskRunItem);

                                // 4. 删除临时代码文件
                                File file = FileUtil.file(absolutePath);
                                boolean delete = file.delete();
                                System.out.println(delete);

                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).start();

    }

    public List<TaskRunLog> findLog100() {
        List<TaskRunLog> taskRunLogList = taskDao.findLog100();
        return taskRunLogList;
    }

    public Map findTaskDetail(String logId) {
        Map map = new HashMap();

        List<TaskRunItem> taskRunItemList = taskDao.findItemList(logId);
        for(TaskRunItem taskRunItem : taskRunItemList) {
            taskRunItem.setTaskStatusStr(
                    TaskRunItem.StatusEnum.valueOf(taskRunItem.getTaskStatus()));
        }
        map.put("taskRunItemList", taskRunItemList);

        TaskRunLog log = taskDao.findLogOne(logId);
        // 头部信息
        map.put("runTaskMan", log.getRunTaskMan());
        map.put("batchNo", log.getBatchNo());
        map.put("runRemark", log.getRunRemark());
        map.put("startTime", log.getStartTime());
        // 任务详细信息
        List<String> taskNumList = new ArrayList<>();
        for(TaskRunItem taskRunItem : taskRunItemList) {
            taskNumList.add(taskRunItem.getTaskNum());
        }
        List<Task> taskByIdList = taskDao.findTaskByIds(taskNumList);
        Map<String, Task> taskMap = new HashMap<>();
        for(Task task : taskByIdList) {
            taskMap.put(task.getTaskNum(), task);
        }
        map.put("taskMap", taskMap);

        return map;
    }

}
