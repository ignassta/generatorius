package com.example.ignas.roomwordssample;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table")
public class Record {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String word;
    @ColumnInfo(name = "title")
    private String title;


    Record(@NonNull String word, String title) {
        this.word = word;
        this.title = title;
    }

    public String getWord(){
        return this.word;
    }

    public String getTitle() {
        return title;
    }
}
