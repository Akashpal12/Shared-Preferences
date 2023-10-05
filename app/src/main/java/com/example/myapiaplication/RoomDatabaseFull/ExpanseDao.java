package com.example.myapiaplication.RoomDatabaseFull;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpanseDao {

    @Query("select * from akash_expanse")
    List<Expanse>getAllExpanse();

    @Insert
    void insert(Expanse expanse);

    @Update
    void updateTask(Expanse expanse);

    @Delete
    void deleteTask(Expanse expanse);

}
