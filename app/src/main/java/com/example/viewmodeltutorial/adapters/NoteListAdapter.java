package com.example.viewmodeltutorial.adapters;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewmodeltutorial.R;
import com.example.viewmodeltutorial.database.NoteCallback;
import com.example.viewmodeltutorial.database.NoteRoomDatabase;
import com.example.viewmodeltutorial.database.entitys.Note;
import com.example.viewmodeltutorial.repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> implements NoteCallback {
    private Context context;
    private NoteRepository repository;

    private LayoutInflater inflater;
    private List<Note> notes = new ArrayList();

    public NoteListAdapter(Context context, NoteRepository repository) {
        this.context = context;
        this.repository = repository;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public NoteListAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListAdapter.NoteViewHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.titleItemView.setText(currentNote.title);
        holder.contentItemView.setText(currentNote.content);
        holder.deleteItemView.setOnClickListener(v -> {
            repository.deleteNote(currentNote);
            repository.getAllNote(this);
//            //xoá note hiện tại
//            try {
//                boolean isDelete = repository.deleteNote(currentNote);
//                Log.d("HAU", "isDelete: " + isDelete);
//                if (isDelete == true) {
//                    //lấy lại danh sách note sau khi xoá
//                    repository.getAllNote(this);
//                }
//            } catch (ExecutionException e) {
//                throw new RuntimeException(e);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public void onAllDataLoaded(List<Note> notes) {
        if (notes != null) {
            setNotes(notes);
        }
    }

    @Override
    public void onDataLoaded(Note note) {

    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView titleItemView;
        TextView contentItemView;
        Button deleteItemView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleItemView = itemView.findViewById(R.id.note_title);
            contentItemView = itemView.findViewById(R.id.note_content);
            deleteItemView = itemView.findViewById(R.id.button_delete);
        }

    }

    protected Handler uiHandler = new Handler(Looper.getMainLooper());

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }
}
