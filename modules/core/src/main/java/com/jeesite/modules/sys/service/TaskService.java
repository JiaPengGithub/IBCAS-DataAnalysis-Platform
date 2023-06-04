/**
 * Copyright (c)
 * No deletion without permission, or be held responsible to law.
 */
package com.jeesite.modules.sys.service;

import com.jeesite.modules.sys.entity.RunTask;
import com.jeesite.modules.sys.entity.Task;
import com.jeesite.modules.sys.entity.TaskRunLog;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface TaskService {

    public List<Task> findAll();

    public List<Task> findAllPython();

    public List<Task> findAllMatlab();

    public void save(Task task);

    public void addTaskFile(MultipartFile multipartFile, HttpServletRequest request);

    public void delete(String taskNum);

    public void runTask(String batchNo, String runTaskMan, String runRemark, List<RunTask> runTaskList);

    public List<TaskRunLog> findLog100();

    public Map findTaskDetail(String logId);

}
