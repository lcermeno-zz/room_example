package com.qiubo.room.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Lawrence Cermeño on 18/03/18.
 */

@Entity
public class Developer implements Comparable<Developer> {
    @PrimaryKey(autoGenerate = true)
    private int mId;
    private String mName;
    private boolean mBookmark;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public int compareTo(@NonNull Developer developer) {
        return Integer.compare(mId, developer.getId());
    }

    @Override
    public boolean equals(Object o) {
        boolean equals = false;
        if (o instanceof Developer) {
            Developer entity = (Developer) o;
            equals = entity.getId() == this.getId();
        }
        return equals;
    }

    public boolean isBookmark() {
        return mBookmark;
    }

    public void setBookmark(boolean bookmark) {
        mBookmark = bookmark;
    }
}
