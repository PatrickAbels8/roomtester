package com.example.roomtester.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    void addSchueler(Schueler schueler);

    @RawQuery
    List<Schueler> getSchueler(SupportSQLiteQuery query);

}
