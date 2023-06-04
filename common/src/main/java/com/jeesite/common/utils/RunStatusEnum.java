package com.jeesite.common.utils;

public enum RunStatusEnum {
    // (0待执行,1执行中,2完毕,99异常)
    OK(0, "正常退出"),
    FAIL(1, "异常退出"),
    RUNNING(-1, "正在运行");

    private int value;
    private String label;

    private RunStatusEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        for (RunStatusEnum loop : RunStatusEnum.values()) {
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