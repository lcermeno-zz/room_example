package com.qiubo.room.presenters;

import com.qiubo.room.entities.Developer;

/**
 * Created by Lawrence Cermeño on 20/03/18.
 */

public interface IMainPresenter {
    void onCreate();

    void onDestroy();

    void addDeveloper(String name);

    void deleteDeveloper(Developer developer);

    void bookmarkDeveloper(Developer developer);
}
