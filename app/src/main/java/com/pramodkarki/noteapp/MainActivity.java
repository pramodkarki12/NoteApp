package com.pramodkarki.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pramodkarki.noteapp.activity.InsertNotesActivity;
import com.pramodkarki.noteapp.adapter.NotesAdapter;
import com.pramodkarki.noteapp.model.NotesEntity;
import com.pramodkarki.noteapp.viewModel.NotesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesBtn;
    NotesViewModel notesViewModel;
    RecyclerView notesRecycleView;
    NotesAdapter adapter;

    TextView noFilter, highToLowFilter, lowToHighFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newNotesBtn = (FloatingActionButton) findViewById(R.id.newNotesBtn);
        notesRecycleView = findViewById(R.id.notesRecycleView);

        noFilter = findViewById(R.id.no_filter);
        highToLowFilter = findViewById(R.id.high_to_low_filter);
        lowToHighFilter = findViewById(R.id.low_to_high_filter);

        /** set Background Resources */
        noFilter.setOnClickListener(v -> {
            loadData(0);
            noFilter.setBackgroundResource(R.drawable.filter_selected_shape);
            highToLowFilter.setBackgroundResource(0);
            lowToHighFilter.setBackgroundResource(0);
        });
        highToLowFilter.setOnClickListener(v -> {
            loadData(1);
            noFilter.setBackgroundResource(0);
            highToLowFilter.setBackgroundResource(R.drawable.filter_selected_shape);
            lowToHighFilter.setBackgroundResource(0);
        });

        lowToHighFilter.setOnClickListener(v -> {
            loadData(2);
            noFilter.setBackgroundResource(0);
            highToLowFilter.setBackgroundResource(0);
            lowToHighFilter.setBackgroundResource(R.drawable.filter_selected_shape);
        });

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        newNotesBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
        });

        /** displays the notes from the database */
        /*notesViewModel.getAllNotes.observe(this, notes -> {
            notesRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
            adapter = new NotesAdapter(MainActivity.this, notes);
            notesRecycleView.setAdapter(adapter);
        });*/
        notesViewModel.getAllNotes.observe(this, new Observer<List<NotesEntity>>() {
            @Override
            public void onChanged(List<NotesEntity> notes) {
                setAdapter(notes);
            }
        });
    }

    public void loadData(int i) {
        if (i == 0) {
            notesViewModel.getAllNotes.observe(this, new Observer<List<NotesEntity>>() {
                @Override
                public void onChanged(List<NotesEntity> notes) {
                    setAdapter(notes);
                }
            });
        } else if (i == 1) {
            notesViewModel.highToLow.observe(this, new Observer<List<NotesEntity>>() {
                @Override
                public void onChanged(List<NotesEntity> notes) {
                    setAdapter(notes);
                }
            });
        } else if (i == 2) {
            notesViewModel.lowToHigh.observe(this, new Observer<List<NotesEntity>>() {
                @Override
                public void onChanged(List<NotesEntity> notes) {
                    setAdapter(notes);
                }
            });
        }


    }

    public void setAdapter(List<NotesEntity> notes) {
        notesRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new NotesAdapter(MainActivity.this, notes);
        notesRecycleView.setAdapter(adapter);
    }
}