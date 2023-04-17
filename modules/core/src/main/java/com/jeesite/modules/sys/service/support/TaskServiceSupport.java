/**
 * Copyright (c)
 * No deletion without permission, or be held responsible to law.
 */
package com.jeesite.modules.sys.service.support;

import com.jeesite.common.utils.UUIDUtil;
import com.jeesite.modules.sys.dao.TaskDao;
import com.jeesite.modules.sys.entity.RunTask;
import com.jeesite.modules.sys.entity.Task;
import com.jeesite.modules.sys.entity.TaskRunItem;
import com.jeesite.modules.sys.entity.TaskRunLog;
import com.jeesite.modules.sys.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.jeesite.common.utils.RunPythonUtil.runPythonCommand;

@Service
public class TaskServiceSupport implements TaskService {

    @Autowired
    private TaskDao taskDao;

    public List<Task> findOne() {
        List<Task> all = taskDao.findAll();
        return all;
    }

    public void save(Task task) {
        if(task.getTaskNum() == null) {
            task.setTaskNum(System.currentTimeMillis()+"");
        }
        task.setUpdateTime(new Date());
        taskDao.save(task);
    }

    public void delete(String taskNum) {
        taskDao.delete(taskNum);
    }

    public void runTask(String batchNo, String runTaskMan, String runRemark, List<RunTask> runTaskList) {
        /**
         * TODO run task
         * (异步任务, 开一个线程池?)
         * vv 1. db插入一条数据
         * 2. 开始执行
         * 3. 每个脚本呢的执行进行记录 到db
         */
        System.out.println(batchNo);
        System.out.println(runTaskList);

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
    private static void runTaskLogic(List<TaskRunItem> taskRunItemList) {

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for(TaskRunItem taskRunItem : taskRunItemList) {
                                // TODO 生成代码文件, 并开始执行, 更新状态, 删除代码文件
//                                runPythonCommand("demo.py");
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
