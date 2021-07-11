package com.pramodkarki.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pramodkarki.noteapp.activity.InsertNotesActivity;
import com.pramodkarki.noteapp.adapter.NotesAdapter;
import com.pramodkarki.noteapp.viewModel.NotesViewModel;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesBtn;
    NotesViewModel notesViewModel;
    RecyclerView notesRecycleView;
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newNotesBtn = (FloatingActionButton) findViewById(R.id.newNotesBtn);
        notesRecycleView = findViewById(R.id.notesRecycleView);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        newNotesBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
        });

        notesViewModel.getAllNotes.observe(this, notes -> {
            notesRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new NotesAdapter(MainActivity.this, notes);
            notesRecycleView.setAdapter(adapter);
        });

    }
}