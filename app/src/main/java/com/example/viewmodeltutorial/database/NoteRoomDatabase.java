package com.example.viewmodeltutorial.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.viewmodeltutorial.database.dao.NoteDao;
import com.example.viewmodeltutorial.database.entitys.Note;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteRoomDatabase extends RoomDatabase {
    private static volatile NoteRoomDatabase INSTANCE = null;
    private static String DB_NAME = "note_db";
    //private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

    public static synchronized NoteRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NoteRoomDatabase.class, DB_NAME)
                    .build();
        }
        return INSTANCE;
    }

    public abstract NoteDao noteDao();
}
