package com.hellmoney.thca.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by len on 2017. 6. 15..
 */

public class TimeUtil {


    public static int timeLeftSecondParsing(Date endDate) {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        Calendar tempcal = Calendar.getInstance();

        long endTime = endDate.getTime(); //현재의 시간 설정
        Calendar cal = Calendar.getInstance();
        Date startDate = cal.getTime();

        long startTime = startDate.getTime();
        long mills = endTime - startTime; //분으로 변환

        //long hour=mills/3600000;
        //long min=mills/60000;
        int second = (int) mills / 1000;

        return second;
    }

    public static String formatNumber2(int num){
        return String.format("%02d",num);
    }

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

        /*
        사용방법

            int leftSecond  = mainPageViewPagerObject.getLeftSecond();
            int hour = leftSecond/3600;
            int tmp = leftSecond%3600;
            int minute = tmp/60;
            int second = tmp%60;

            if(leftSecond > 0){
               leftTime.setText("마감까지 " + CommonClass.formatNumber2(hour) + ":" + CommonClass.formatNumber2(minute)  + ":" + CommonClass.formatNumber2(second) + " 남았습니다.");

            }else{
                leftTime.setText("마감까지 " + "00" + ":" +"00" + " 남았습니다.");
            }
             */

}
