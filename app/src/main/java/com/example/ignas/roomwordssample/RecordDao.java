package com.example.ignas.roomwordssample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Record record);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Delete
    void deleteWord(Record record);

    @Query("SELECT * from word_table")
    LiveData<List<Record>> getAllWords();
}
