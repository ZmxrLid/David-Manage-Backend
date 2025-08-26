package com.zmxrlid.backend.common;

public class Quchu {
    public static String removeQuotesIfPresent(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }

        // 检查并去除双引号
        if (str.startsWith("\"") && str.endsWith("\"")) {
            return str.substring(1, str.length() - 1);
        }

        // 检查并去除单引号
        if (str.startsWith("'") && str.endsWith("'")) {
            return str.substring(1, str.length() - 1);
        }

        // 没有引号则保持原样
        return str;
    }

}
