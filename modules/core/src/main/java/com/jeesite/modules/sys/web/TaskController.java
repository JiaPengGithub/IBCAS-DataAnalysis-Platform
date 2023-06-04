/**
 * Copyright (c)
 * No deletion without permission, or be held responsible to law.
 */
package com.jeesite.modules.sys.web;

import com.alibaba.fastjson.JSONArray;
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.idgen.IdGen;
import com.jeesite.common.lang.DateUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.utils.excel.ExcelExport;
import com.jeesite.common.utils.excel.annotation.ExcelField.Type;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.sys.entity.*;
import com.jeesite.modules.sys.service.OfficeService;
import com.jeesite.modules.sys.service.TaskService;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.sys.web.user.EmpUserController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 任务调度Controller
 * @author JiaPeng
 * @version 2023-03-22
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/task")
@ConditionalOnProperty(name="web.core.enabled", havingValue="true", matchIfMissing=true)
public class TaskController extends BaseController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private EmpUserController empUserController;

	/**
	 * 任务调度列表 页面
	 */
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "index")
	public String index(Model model) {
		return "modules/sys/taskIndex";
	}

	/**
	 * 配置 python任务 页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "addTaskIndex")
	public String addPythonTaskIndex(Model model) {
		return "modules/sys/taskAddIndex";
	}

	/**
	 * 配置 matlab任务 页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "addMatlabTaskIndex")
	public String addMatlabTaskIndex(Model model) {
		return "modules/sys/matlabTaskAddIndex";
	}

	/**
	 * 任务日志 页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "taskLogIndex")
	public String taskLogIndex(Model model) {
		return "modules/sys/taskLogIndex";
	}

	/**
	 * 添加任务接口
	 */
	@RequiresPermissions("sys:office:view")
	@PostMapping(value = "addTaskConfig")
	public String addTaskConfig(Task task, Model model) {
		taskService.save(task);
		return task.getTaskNum();
	}
	/**
	 * 添加任务文件接口
	 */
	@RequiresPermissions("sys:office:view")
	@PostMapping(value = "addTaskFile")
	public String addTaskFile(@RequestParam MultipartFile multipartFile, HttpServletRequest request) {
		taskService.addTaskFile(multipartFile, request);
		return "ok";
	}

	/**
	 * 删除任务接口
	 */
	@RequiresPermissions("sys:office:view")
	@PostMapping(value = "deleteTaskConfig")
	public String deleteTaskConfig(String taskNum, Model model) {
		taskService.delete(taskNum);
		return "ok";
	}

	/**
	 * 执行任务接口
	 */
	@RequiresPermissions("sys:office:view")
	@PostMapping(value = "runTask")
	public String runTask(RunTaskSubmit runTaskSubmit, Model model) {
		JSONArray jsonArray = JSONArray.parseArray(runTaskSubmit.getList());
		List<RunTask> runTaskList = new ArrayList<>();
		for(int i=0; i<jsonArray.size(); i++) {
			RunTask runTask = jsonArray.getObject(i, RunTask.class);
			runTaskList.add(runTask);
		}
		taskService.runTask(runTaskSubmit.getBatchNo(), runTaskSubmit.getRunTaskMan(), runTaskSubmit.getRunRemark(), runTaskList);
		return "ok";
	}

	/**
	 * 查询任务列表(所有任务)
	 */
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<Task> listData() {
		List<Task> taskList = taskService.findAll();
		return taskList;
	}

	/**
	 * 查询任务列表(所有python任务)
	 */
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "listDataPython")
	@ResponseBody
	public List<Task> listDataPython() {
		List<Task> taskList = taskService.findAllPython();
		return taskList;
	}

	/**
	 * 查询任务列表(所有matlab任务)
	 */
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "listDataMatlab")
	@ResponseBody
	public List<Task> listDataMatlab() {
		List<Task> taskList = taskService.findAllMatlab();
		return taskList;
	}

	/**
	 * 查询任务log
	 * (最近100条任务日志, 超过100条的先不查询出来？)
	 */
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "logList")
	@ResponseBody
	public List<TaskRunLog> logList() {
		List<TaskRunLog> taskRunLogList = taskService.findLog100();
		return taskRunLogList;
	}

	/**
	 * 查询任务log
	 * (最近100条任务日志, 超过100条的先不查询出来？)
	 */
	@RequiresPermissions("sys:office:view")
	@RequestMapping(value = "taskDetail")
	@ResponseBody
	public Map taskDetail(String logId) {
		Map taskDetailMap = taskService.findTaskDetail(logId);
		return taskDetailMap;
	}

}
