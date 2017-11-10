package com.hellmoney.thca.common.util;

import android.content.SharedPreferences;

import com.hellmoney.thca.common.HelloMoneyConsultantApplication;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by kirkee on 2017. 11. 4..
 */

public class SharedReferenceUtil {
     public static boolean getLocalLogin() {
        SharedPreferences sharedPreferences = HelloMoneyConsultantApplication.getGlobalApplicationContext().getSharedPreferences("helloMoney", MODE_PRIVATE);
        return sharedPreferences.getBoolean("LocalLogin",false);
    }

    public static void setLocalLogin(){
        SharedPreferences sharedPreferences = HelloMoneyConsultantApplication.getGlobalApplicationContext().getSharedPreferences("helloMoney", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(!sharedPreferences.getBoolean("LocalLogin",false)){
            editor.putBoolean("LocalLogin", true);
            editor.apply();
        }
    }
}
