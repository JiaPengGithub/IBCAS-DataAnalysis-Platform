/**
 * Copyright (c)
 * No deletion without permission, or be held responsible to law.
 */
package com.jeesite.modules.sys.service;

import com.jeesite.modules.sys.entity.RunTask;
import com.jeesite.modules.sys.entity.Task;
import com.jeesite.modules.sys.entity.TaskRunLog;

import java.util.List;
import java.util.Map;

public interface TaskService {

    public List<Task> findOne();

    public void save(Task task);

    public void delete(String taskNum);

    public void runTask(String batchNo, String runTaskMan, String runRemark, List<RunTask> runTaskList);

    public List<TaskRunLog> findLog100();

    public Map findTaskDetail(String logId);

}
