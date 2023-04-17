/**
 * Copyright (c)
 * No deletion without permission, or be held responsible to law.
 */
package com.jeesite.modules.sys.dao;

import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.sys.entity.Task;
import com.jeesite.modules.sys.entity.TaskRunItem;
import com.jeesite.modules.sys.entity.TaskRunLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface TaskDao {

    public List<Task> findAll();

    public void save(@Param("task") Task task);

    public void delete(@Param("taskNum") String taskNum);

    public void saveTaskRunLog(@Param("log") TaskRunLog taskRunLog);

    public void saveTaskItemList(@Param("itemList") List<TaskRunItem> taskRunItemList);

    public List<TaskRunLog> findLog100();

    public TaskRunLog findLogOne(@Param("logId") String logId);

    public List<TaskRunItem> findItemList(@Param("orgId") String orgId);

    public List<Task> findTaskByIds(@Param("taskNumList") List<String> taskNumList);

}