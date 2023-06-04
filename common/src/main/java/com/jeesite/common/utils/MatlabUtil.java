package com.jeesite.common.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MatlabUtil {

    /**
     * TODO 返回状态需要优化
     * @param matlabPath
     * @return
     */
    public static RunStatusEnum runMatlabByAbsolutePath(String matlabPath, String inPath, String outPath) {
        try {
            String shell = String.format(
                    "java -jar %s %s %s", matlabPath, inPath, outPath
            );
            System.out.println("shell = " + shell);
            Runtime rt = Runtime.getRuntime();
            rt.exec(shell);
        } catch (Exception e) {
            e.printStackTrace();
            return RunStatusEnum.FAIL;
        }
        return RunStatusEnum.OK;
    }

    /**
     * 运行shell
     * @param shStr
     *      需要执行的shell
     * @return
     * @throws IOException
     */
    public static List runShell(String shStr) throws Exception {
        List<String> strList = new ArrayList();

        Process process;
        process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",shStr},null,null);
        InputStreamReader ir = new InputStreamReader(process
                .getInputStream());
        LineNumberReader input = new LineNumberReader(ir);
        String line;
        process.waitFor();
        while ((line = input.readLine()) != null){
            strList.add(line);
        }

        return strList;
    }

}
