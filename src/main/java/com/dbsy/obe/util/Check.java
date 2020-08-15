package com.dbsy.obe.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
    private final static String emailCheck = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private final static String phoneCheck = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])|(18[0-9])|(19[8,9]))\\d{8}$";

    public static boolean isEmail(String email) {
        if (email == null) return false;
        return check(emailCheck, email);
    }

    public static boolean isPhone(String phone) {
        if (phone == null) return false;
        return check(phoneCheck, phone);
    }

    private static boolean check(String strCheck, String str) {
        boolean flag;
        try {
            Pattern regex = Pattern.compile(strCheck);
            Matcher matcher = regex.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
