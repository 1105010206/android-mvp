/**
 *
 */
package com.android.mvp.demo.utils;

import android.content.Context;
import android.text.TextUtils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bobo
 * @date 2014-4-16下午05:32:16
 */
public class FormatUtils {

    public static boolean isEmailFormat(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    public static boolean isPhoneFormat(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isAccountNameFormat(String userName) {
        Pattern p = Pattern.compile("^\\w+$");
        Matcher m = p.matcher(userName);

        return m.matches();
    }

    public static boolean isPasswordFormat(String password) {
        Pattern p = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher m = p.matcher(password);
        return m.matches();
    }

    public static String removeChinaAreaMsisdnAndSpace(String phoneNum) {
        if (TextUtils.isEmpty(phoneNum)) {
            return phoneNum;
        } else {
            phoneNum = phoneNum.replaceAll(" ", "").replaceAll("-", "");
            // //导致运行缓慢
            if (phoneNum.startsWith("+86")) {
                return phoneNum.substring(3);
            } else if (phoneNum.startsWith("86")) {
                return phoneNum.substring(2);
            } else {
                return phoneNum;
            }
        }
    }
}
