package com.hellmoney.thca.common;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.kakao.auth.KakaoSDK;
import com.kakao.push.PushService;

/**
 * Created by kirkee on 2017. 10. 28..
 */

public class HelloMoneyConsultantApplication extends Application {
    private static volatile HelloMoneyConsultantApplication instance = null;

    /**
     * singleton 애플리케이션 객체를 얻는다.
     * @return singleton 애플리케이션 객체
     */
    public static HelloMoneyConsultantApplication getGlobalApplicationContext() {
        if(instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }

    /**
     * 이미지 로더, 이미지 캐시, 요청 큐를 초기화한다.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        KakaoSDK.init(new KakaoSDKAdapter());
        PushService.init();
    }

    /**
     * 애플리케이션 종료시 singleton 어플리케이션 객체 초기화한다.
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
