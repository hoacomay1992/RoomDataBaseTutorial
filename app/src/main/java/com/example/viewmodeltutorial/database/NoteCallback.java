package com.example.viewmodeltutorial.database;

import com.example.viewmodeltutorial.database.entitys.Note;

import java.util.List;

public interface NoteCallback {
    void onAllDataLoaded(List<Note> notes);

    void onDataLoaded(Note note);
}
