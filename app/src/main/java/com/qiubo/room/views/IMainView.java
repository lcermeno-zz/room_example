package com.qiubo.room.views;

import com.qiubo.room.entities.Developer;

import java.util.List;

/**
 * Created by Lawrence Cermeño on 20/03/18.
 */

public interface IMainView {
    void onGetDevelopers(List<Developer> developers);

    void onDeveloperAdded(Developer developer);

    void onDeveloperDeleted(Developer developer);

    void onDeveloperUpdated(Developer developer);
}
