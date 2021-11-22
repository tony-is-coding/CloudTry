package com.cnc.fp.user.utils;

import io.netty.util.internal.StringUtil;

public class CommonUtils {
    public static Boolean isMobile(String maybeMobileString) {
        return maybeMobileString.matches("[0-9]+") && maybeMobileString.length() == 11;
    }
}
