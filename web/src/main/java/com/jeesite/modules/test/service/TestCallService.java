/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 * No deletion without permission, or be held responsible to law.
 */
package com.jeesite.modules.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.jeesite.modules.test.service.RunPythonUtil.runPythonCommand;

/**
 * 测试数据Service
 * @author JiaPeng
 * @version 2022-02-04
 */
@Service
public class TestCallService {

	private static Logger logger = LoggerFactory.getLogger(TestCallService.class);

	public void call() {
		System.out.println("test task.");
	}

	public void task01() throws Exception {

		logger.info("task01 start.");
		Thread.sleep(5 * 1000);
		logger.info("task01 end.");

	}

	public void task02(String command) throws Exception {

		logger.info("task02 start.");
		Thread.sleep(5 * 1000);
		logger.info("task02 end.");

	}

	public void task03(String command) throws Exception {

		runPythonCommand("demo.py");

	}

	public void task04(String command) throws Exception {

		runPythonCommand("VI_129.py");

	}

	public void taskAll(String command) throws Exception {
		runPythonCommand(command + ".py");
	}

}