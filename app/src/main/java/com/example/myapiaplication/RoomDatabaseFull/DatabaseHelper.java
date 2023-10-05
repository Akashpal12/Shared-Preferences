package com.example.myapiaplication.RoomDatabaseFull;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Expanse.class,version = 1,exportSchema = false)
public abstract class DatabaseHelper extends RoomDatabase {
    private static final String DATABASE_NAME="expense_database";
    private static DatabaseHelper instanse;

    public static synchronized DatabaseHelper getDB(Context context){
        if (instanse==null){
            instanse = Room.databaseBuilder(context,DatabaseHelper.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        return instanse;
    }

    public abstract ExpanseDao expanseDao();

}
