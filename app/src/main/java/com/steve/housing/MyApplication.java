package com.steve.housing;

import android.app.Application;

/**
 * Created by SOVAVY on 5/2/2017.
 */

public class MyApplication extends Application {
    private static MyApplication singleton;
    public static MyApplication getInstance(){
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }
    private void init(){
        singleton = this;
    }
}
