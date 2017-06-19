package com.hellmoney.thca.util;

import java.text.DecimalFormat;

/**
 * Created by len on 2017. 6. 19..
 */

public class StringUtil {

    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }


    /**
     * 숫자에 천단위마다 콤마 넣기
     * @return String
     * */
    public static String toNumFormat(int num) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(num);
    }

}
