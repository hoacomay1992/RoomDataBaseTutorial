package com.example.viewmodeltutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.viewmodeltutorial.base.BaseActivity;
import com.example.viewmodeltutorial.database.NoteRoomDatabase;
import com.example.viewmodeltutorial.database.entitys.Note;
import com.example.viewmodeltutorial.databinding.ActivityNewNoteBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewNoteActivity extends BaseActivity {
    private ActivityNewNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonSave.setOnClickListener(v -> {
            String strTitle = binding.titleNote.getText().toString();
            String strContent = binding.contentNote.getText().toString();
            noteRepository.insertNote(new Note(strTitle, strContent));
            finish();
        });
    }
}