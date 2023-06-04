package com.jeesite.common.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;

import java.io.File;

public class RunPythonUtil {

    // TODO
    static String COMMAND_PATH = new File("command").getAbsolutePath();

    /**
     * block function
     * @param absolutePath
     * @param pyFileName
     * @return
     * @throws InterruptedException
     */
    public static RunStatusEnum runPythonCommandByAbsolutePath(String absolutePath, String pyFileName) throws InterruptedException {
        String pythonCommand = String.format("%s-%s-%s", "2020StartTime", System.currentTimeMillis(), pyFileName);
        String pythonPath = String.format("%s/%s", absolutePath.replace("/"+pyFileName, ""), pythonCommand);

        boolean exist = FileUtil.exist(pythonPath);
        if(false == exist) {
            // 创建命令 (递归创建)
            FileUtil.mkdir(pythonPath);
        }

        System.out.println("命令信息存储位置: " + pythonPath);

        String statePath = pythonPath + "/state";
        String successFlagPath = pythonPath + "/_.success";
        String failFlagPath = pythonPath + "/_.fail";

        String state = "-2";
        RunStatusEnum code = getExitCode(successFlagPath, failFlagPath);
        while(RunStatusEnum.RUNNING.getValue() == code.getValue()) {
            boolean statePathExist = FileUtil.exist(statePath);
            if(statePathExist) {
                FileReader fileReader = new FileReader(statePath);
                String currState = fileReader.readString();
                if(state.equals(currState) == false) {
                    state = currState;
                    System.out.println(String.format("命令执行状态: %s, %s", state, getStateStr(state)));
                }
                code = getExitCode(successFlagPath, failFlagPath);
            }
            Thread.sleep(100);
        }

        return code;

//        if(0 == code) {
//            System.out.println("正常退出，退出码 0");
//        } else {
//            throw new RuntimeException("异常退出，退出码" + code);
//        }

    }

    /**
     * 执行python的命令
     * @param pyFileName 需要执行的python文件名
     * @throws InterruptedException
     */
    public static void runPythonCommand(String pyFileName) throws InterruptedException {
        runPythonCommandByAbsolutePath(COMMAND_PATH, pyFileName);
    }
//    private static void _runPythonCommand(String pyFileName) throws InterruptedException {
//
//        String pythonCommand = String.format("%s-%s-%s", "2020StartTime", System.currentTimeMillis(), pyFileName);
//        String pythonPath = String.format("%s/%s", COMMAND_PATH, pythonCommand);
//
//        boolean exist = FileUtil.exist(pythonPath);
//        if(false == exist) {
//            // 创建命令 (递归创建)
//            FileUtil.mkdir(pythonPath);
//        }
//
//        System.out.println("命令信息存储位置: " + pythonPath);
//
//        String statePath = pythonPath  + "/state";
//        String successFlagPath = pythonPath + "/_.success";
//        String failFlagPath = pythonPath + "/_.fail";
//
//        String state = "-2";
//        int code = getExitCode(successFlagPath, failFlagPath);
//        while(-1 == code) {
//            boolean statePathExist = FileUtil.exist(statePath);
//            if(statePathExist) {
//                FileReader fileReader = new FileReader(statePath);
//                String currState = fileReader.readString();
//                if(state.equals(currState) == false) {
//                    state = currState;
//                    System.out.println(String.format("命令执行状态: %s, %s", state, getStateStr(state)));
//                }
//                code = getExitCode(successFlagPath, failFlagPath);
//            }
//            Thread.sleep(10);
//        }
//
//        if(0 == code) {
//            System.out.println("正常退出，退出码 0");
//        } else {
//            throw new RuntimeException("异常退出，退出码" + code);
//        }
//
//    }

    /**
     * 获取 退出码
     * @param successFlagPath
     * @param failFlagPath
     * @return
     */
    static RunStatusEnum getExitCode(String successFlagPath, String failFlagPath) {
        // 执行成功
        if(FileUtil.exist(successFlagPath)) {
            return RunStatusEnum.OK;
        }
        // 执行失败
        if(FileUtil.exist(failFlagPath)) {
            return RunStatusEnum.FAIL;
        }
        // 正在执行
        return RunStatusEnum.RUNNING;

    }

    static String getStateStr(String state) {
        switch (state) {
            case "0": return "已读取";
            case "1": return "开始执行";
            case "2": return "正在执行";
            case "3": return "执行结束";
        }
        return "未读取";
    }

}
