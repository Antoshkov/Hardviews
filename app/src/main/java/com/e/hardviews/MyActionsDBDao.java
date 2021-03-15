package com.e.hardviews;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyActionsDBDao {
    @Query("SELECT * FROM `Action`")
    List<Action> getAll();

    @Query("SELECT * FROM `Action` WHERE id = :id")
    Action getById(int id);

    @Insert
    void insert(List<Action> actionsList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Action action);

    @Update
    void update(Action action);

    @Delete
    void delete(Action action);

    @Query("DELETE FROM `Action`")
    void deleteAll();
}