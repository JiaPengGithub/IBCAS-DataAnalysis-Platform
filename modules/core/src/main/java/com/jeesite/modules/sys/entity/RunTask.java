/**
 * Copyright (c)
 * No deletion without permission, or be held responsible to law.
 */
package com.jeesite.modules.sys.entity;

public class RunTask {

	private String taskNum;

	private String scriptName;

	private String inputPathStr;

	private String outputPathStr;

	public String getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(String taskNum) {
		this.taskNum = taskNum;
	}

	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	public String getInputPathStr() {
		return inputPathStr;
	}

	public void setInputPathStr(String inputPathStr) {
		this.inputPathStr = inputPathStr;
	}

	public String getOutputPathStr() {
		return outputPathStr;
	}

	public void setOutputPathStr(String outputPathStr) {
		this.outputPathStr = outputPathStr;
	}

}
