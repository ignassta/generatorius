package com.example.ignas.roomwordssample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Record.class}, version = 2, exportSchema = false)
public abstract class RecordRoomDatabase extends RoomDatabase {

    public abstract RecordDao wordDao();
    private static RecordRoomDatabase INSTANCE;

    static RecordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RecordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        RecordRoomDatabase.class, "word_database")

                        .fallbackToDestructiveMigration()
                        .build();
                }
            }
        }
        return INSTANCE;
    }
}
