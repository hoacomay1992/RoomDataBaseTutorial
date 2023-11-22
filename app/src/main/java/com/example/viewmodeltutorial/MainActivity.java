package com.example.viewmodeltutorial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.example.viewmodeltutorial.adapters.NoteListAdapter;
import com.example.viewmodeltutorial.base.BaseActivity;
import com.example.viewmodeltutorial.database.NoteCallback;
import com.example.viewmodeltutorial.database.NoteRoomDatabase;
import com.example.viewmodeltutorial.database.entitys.Note;
import com.example.viewmodeltutorial.repository.NoteRepository;
import com.example.viewmodeltutorial.view_model.LocationListenerCallback;
import com.example.viewmodeltutorial.view_model.MyLocationManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import android.widget.Toast;

import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.viewmodeltutorial.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends BaseActivity implements LocationListenerCallback, NoteCallback {


    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private final int REQUEST_LOCATION_PERMISSION_CODE = 1;
    private MyLocationManager myLocationManager;
    private NoteListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //  setSupportActionBar(binding.toolbar);

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAnchorView(R.id.fab)
//                        .setAction("Action", null).show();
//            }
//        });
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION_CODE);
        } else {
            // Permission is already granted, proceed with your location-related tasks
            // e.g., initialize location updates
            // startLocationUpdates();
          //  setupLocationListener();
        }
        adapter = new NoteListAdapter(MainActivity.this, noteRepository);
        binding.recyclerNotes.setAdapter(adapter);
        binding.recyclerNotes.setLayoutManager(new LinearLayoutManager(this));
        binding.buttonNewNote.setOnClickListener(v -> {
            Intent newNoteIntent = new Intent(this, NewNoteActivity.class);
            startActivity(newNoteIntent);
        });
        binding.buttonFind.setOnClickListener(v -> {
            findNote();
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllNotes();
    }

    // Get all notes
    private void getAllNotes() {
        noteRepository.getAllNote(this);
    }

    // Find note
    private void findNote() {
        String strFind = binding.edittextFind.getText().toString();
        if (!TextUtils.isEmpty(strFind)) {
            noteRepository.findNoteByTitle(strFind, this);
        } else {
            getAllNotes();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setupLocationListener() {
        myLocationManager = new MyLocationManager(this, this);
        getLifecycle().addObserver(myLocationManager);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with your location-related tasks
                // e.g., initialize location updates
                // startLocationUpdates();
              //  setupLocationListener();
            } else {
                Toast.makeText(this, "Bạn chưa có quyền truy cập vào GPS của thiết bị", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        binding.tvContent.setText(location.getLatitude() + ", " + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListenerCallback.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListenerCallback.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListenerCallback.super.onProviderDisabled(provider);
    }

    @Override
    public void onAllDataLoaded(List<Note> notes) {
        if (notes != null) {
            adapter.setNotes(notes);
        }
    }

    @Override
    public void onDataLoaded(Note note) {
        if (note != null) {
            List<Note> notes = new ArrayList<>();
            notes.add(note);
            adapter.setNotes(notes);
        }
    }
}