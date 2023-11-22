package com.example.viewmodeltutorial.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.viewmodeltutorial.repository.NoteRepository;

public class BaseActivity extends AppCompatActivity {
    protected NoteRepository noteRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteRepository = new NoteRepository(this);
    }

    @Override
    protected void onDestroy() {
//        if (noteRepository != null) {
//            noteRepository.shutdownExecutor();
//        }
        super.onDestroy();

    }
}
