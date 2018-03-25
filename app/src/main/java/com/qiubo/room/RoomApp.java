package com.qiubo.room;

import android.app.Application;

/**
 * Created by Lawrence Cermeño on 20/03/18.
 */

public class RoomApp extends Application {

    private static RoomApp sInstance;

    public static RoomApp getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
