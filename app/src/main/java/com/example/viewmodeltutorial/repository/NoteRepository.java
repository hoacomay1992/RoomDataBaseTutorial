package com.example.viewmodeltutorial.repository;

import android.content.Context;
import android.util.Log;

import com.example.viewmodeltutorial.database.NoteCallback;
import com.example.viewmodeltutorial.database.NoteRoomDatabase;
import com.example.viewmodeltutorial.database.dao.NoteDao;
import com.example.viewmodeltutorial.database.entitys.Note;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class NoteRepository {
    private final NoteDao noteDao;
    private final ExecutorService executorService;

    public NoteRepository(Context context) {
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(context);
        noteDao = db.noteDao();
        executorService = NoteRoomDatabase.databaseWriteExecutor;
    }

    public void insertNote(final Note note) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.insert(note);
            }
        });
    }

    public void deleteNote(final Note note) {
//        Future<Boolean> isDelete = executorService.submit(new Callable<Boolean>() {
//            @Override
//            public Boolean call() throws Exception {
//                try {
//                    noteDao.delete(note);
//                    return true;
//                } catch (Exception e) {
//                    return false;
//                }
//            }
//        });
//        return isDelete.get();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.delete(note);
            }
        });
    }

    public void getAllNote(final NoteCallback callback) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Note> dataList = noteDao.getAllNotes();
                callback.onAllDataLoaded(dataList);
            }
        });
    }

    public void findNoteByTitle(String title, NoteCallback callback) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Note note = noteDao.findNoteByTitle(title);
                callback.onDataLoaded(note);
            }
        });

    }

    public void shutdownExecutor() {
        Log.d("HAU", "shutdownExecutor success");
        executorService.shutdown();
    }
}
