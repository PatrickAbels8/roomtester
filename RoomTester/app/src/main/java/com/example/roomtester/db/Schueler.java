package com.example.roomtester.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "schueler")
public class Schueler {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "schueler_name")
    private String name;
    @ColumnInfo(name = "schueler_height")
    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
