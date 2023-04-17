package com.jeesite.common.utils;

import java.util.UUID;

/**
 * @author Jia Peng
 */
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
