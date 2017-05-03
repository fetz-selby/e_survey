package com.steve.housing;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by SOVAVY on 5/2/2017.
 */

public class MyApplication extends Application {
    private static MyApplication singleton;
    // The Realm file will be located in Context.getFilesDir() with name "default.realm"

    public static MyApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .build();
        Realm.setDefaultConfiguration(config);
        init();
    }

    private void init() {
        singleton = this;
    }
}
