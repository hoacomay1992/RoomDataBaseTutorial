package com.example.viewmodeltutorial.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.viewmodeltutorial.database.entitys.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note_table ORDER BY id ASC")
    List<Note> getAllNotes();

    @Query("SELECT * FROM note_table WHERE title LIKE :title")
    Note findNoteByTitle(String title);

    @Insert
    void insert(Note note);

    @Delete
    void delete(Note note);
}
