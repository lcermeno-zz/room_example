package com.qiubo.room.presenters;

import com.qiubo.room.entities.Developer;
import com.qiubo.room.repositories.datasource.QiuboDatabase;
import com.qiubo.room.views.IMainView;

import java.util.List;

/**
 * Created by Lawrence Cermeño on 20/03/18.
 */

public class MainPresenter implements IMainPresenter {
    private IMainView mView;

    public MainPresenter(IMainView view) {
        mView = view;
    }

    @Override
    public void onCreate() {
        new Thread(() -> {
            QiuboDatabase qiuboDatabase = QiuboDatabase.getInstance();
            List<Developer> developers = qiuboDatabase.getDeveloperDAO().findAll();
            if (mView != null) {
                mView.onGetDevelopers(developers);
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void addDeveloper(String name) {
        if (name != null && !name.trim().isEmpty()) {
            Developer developer = new Developer();
            developer.setName(name);
            new Thread(() -> QiuboDatabase
                    .getInstance()
                    .getDeveloperDAO()
                    .insert(developer)).start();
            if (mView != null) {
                mView.onDeveloperAdded(developer);
            }
        }
    }

    @Override
    public void deleteDeveloper(Developer developer) {
        new Thread(() -> {
            QiuboDatabase
                    .getInstance()
                    .getDeveloperDAO()
                    .delete(developer);
            if (mView != null) {
                mView.onDeveloperDeleted(developer);
            }
        }).start();
    }

    @Override
    public void bookmarkDeveloper(Developer developer) {
        new Thread(() -> {
            developer.setBookmark(!developer.isBookmark());
            QiuboDatabase
                    .getInstance()
                    .getDeveloperDAO()
                    .update(developer);
            if (mView != null) {
                mView.onDeveloperUpdated(developer);
            }
        }).start();
    }
}
