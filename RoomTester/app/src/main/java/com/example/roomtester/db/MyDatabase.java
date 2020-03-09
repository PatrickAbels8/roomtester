package com.example.roomtester.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Schueler.class}, version=1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract MyDao myDao();
}
