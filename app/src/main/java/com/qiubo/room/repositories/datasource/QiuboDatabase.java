package com.qiubo.room.repositories.datasource;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.qiubo.room.RoomApp;
import com.qiubo.room.entities.Developer;
import com.qiubo.room.repositories.datasource.dao.IDeveloperDAO;

/**
 * Created by Lawrence Cermeño on 18/03/18.
 */

@Database(entities = {Developer.class}, version = 1)
public abstract class QiuboDatabase extends RoomDatabase {

    private static volatile QiuboDatabase sInstance;
    private static final String DB_NAME = "qioubo.db";

    public static QiuboDatabase getInstance() {
        if (sInstance == null) {
            sInstance = create(RoomApp.getInstance().getApplicationContext());
        }
        return sInstance;
    }

    private static QiuboDatabase create(Context context) {
        return Room.databaseBuilder(
                context,
                QiuboDatabase.class,
                DB_NAME).build();
    }

    public abstract IDeveloperDAO getDeveloperDAO();
}
