package com.qiubo.room.repositories.datasource.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.qiubo.room.entities.Developer;

import java.util.List;

/**
 * Created by Lawrence Cermeño on 18/03/18.
 */

@Dao
public interface IDeveloperDAO {
    @Query("SELECT * FROM developer")
    List<Developer> findAll();

    @Insert
    void insert(Developer... developers);

    @Update
    void update(Developer... developers);

    @Delete
    void delete(Developer... developers);
}
