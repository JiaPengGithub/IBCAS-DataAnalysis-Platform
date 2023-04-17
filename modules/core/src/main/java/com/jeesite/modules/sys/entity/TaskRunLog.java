/**
 * Copyright (c)
 * No deletion without permission, or be held responsible to law.
 */
package com.jeesite.modules.sys.entity;

import java.util.Date;

public class TaskRunLog {

	private String id;

	private String runTaskMan;

	private String batchNo;

	private Date startTime;

	private String runRemark;

	private Date updateTime;

	public TaskRunLog(String id, String runTaskMan, String batchNo, Date startTime, String runRemark, Date updateTime) {
		this.id = id;
		this.runTaskMan = runTaskMan;
		this.batchNo = batchNo;
		this.startTime = startTime;
		this.runRemark = runRemark;
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRunTaskMan() {
		return runTaskMan;
	}

	public void setRunTaskMan(String runTaskMan) {
		this.runTaskMan = runTaskMan;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getRunRemark() {
		return runRemark;
	}

	public void setRunRemark(String runRemark) {
		this.runRemark = runRemark;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
