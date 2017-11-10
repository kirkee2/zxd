package com.hellmoney.thca.common.util;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * Created by len on 2017. 6. 19..
 */

public class StringUtil {

    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }


    /**
     * 숫자에 천단위마다 콤마 넣기
     *
     * @return String
     */
    public static String toNumFormat(int num) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(num);
    }

    public static String formatPhoneNumber(String phoneNumber) {
        String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";

        if (!Pattern.matches(regEx, phoneNumber)) return null;

        return phoneNumber.replaceAll(regEx, "$1-$2-$3");

    }

}
