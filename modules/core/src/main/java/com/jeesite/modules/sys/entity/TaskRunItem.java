/**
 * Copyright (c)
 * No deletion without permission, or be held responsible to law.
 */
package com.jeesite.modules.sys.entity;

import java.util.Date;

public class TaskRunItem {

	public enum StatusEnum {
		// (0待执行,1执行中,2完毕,99异常)
		WAIT(0, "待执行"),
		RUNING(1, "执行中"),
		OVER(2, "完毕"),
		ERROR(99, "异常");

		private int value;
		private String label;

		private StatusEnum(int value, String label) {
			this.value = value;
			this.label = label;
		}

		public static String valueOf(Integer value) {
			if (value == null) {
				return null;
			}
			for (StatusEnum loop : StatusEnum.values()) {
				if (value == loop.getValue()) {
					return loop.getLabel();
				}
			}
			return null;
		}

		public int getValue() {
			return value;
		}

		public String getLabel() {
			return label;
		}
	}

	private String id;
	private String runId;
	private String taskNum;
	private Integer taskStatus;
	private String taskStatusStr;
	private Date startTime;
	private Date endTime;
	private String inputPath;
	private String outputPath;
	private Date updateTime;

	public TaskRunItem(String id, String runId, String taskNum, Integer taskStatus, Date startTime, Date endTime, String inputPath, String outputPath, Date updateTime) {
		this.id = id;
		this.runId = runId;
		this.taskNum = taskNum;
		this.taskStatus = taskStatus;
		this.startTime = startTime;
		this.endTime = endTime;
		this.inputPath = inputPath;
		this.outputPath = outputPath;
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRunId() {
		return runId;
	}

	public void setRunId(String runId) {
		this.runId = runId;
	}

	public String getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(String taskNum) {
		this.taskNum = taskNum;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getTaskStatusStr() {
		return taskStatusStr;
	}

	public void setTaskStatusStr(String taskStatusStr) {
		this.taskStatusStr = taskStatusStr;
	}
}
