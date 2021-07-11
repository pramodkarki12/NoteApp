package com.pramodkarki.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pramodkarki.noteapp.activity.InsertNotesActivity;
import com.pramodkarki.noteapp.adapter.NotesAdapter;
import com.pramodkarki.noteapp.model.NotesEntity;
import com.pramodkarki.noteapp.viewModel.NotesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesBtn;
    NotesViewModel notesViewModel;
    RecyclerView notesRecycleView;
    NotesAdapter adapter;

    TextView noFilter, highToLowFilter, lowToHighFilter;
    List<NotesEntity> filterNotesAllItem;

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
                filterNotesAllItem = notes;
            }
        });
    }

    public void loadData(int i) {
        if (i == 0) {
            notesViewModel.getAllNotes.observe(this, new Observer<List<NotesEntity>>() {
                @Override
                public void onChanged(List<NotesEntity> notes) {
                    setAdapter(notes);
                    filterNotesAllItem = notes;
                }
            });
        } else if (i == 1) {
            notesViewModel.highToLow.observe(this, new Observer<List<NotesEntity>>() {
                @Override
                public void onChanged(List<NotesEntity> notes) {
                    setAdapter(notes);
                    filterNotesAllItem = notes;
                }
            });
        } else if (i == 2) {
            notesViewModel.lowToHigh.observe(this, new Observer<List<NotesEntity>>() {
                @Override
                public void onChanged(List<NotesEntity> notes) {
                    setAdapter(notes);
                    filterNotesAllItem = notes;
                }
            });
        }
    }

    public void setAdapter(List<NotesEntity> notes) {
        notesRecycleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(MainActivity.this, notes);
        notesRecycleView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes, menu);

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes... ");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                notesFilter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void notesFilter(String newText) {
        ArrayList<NotesEntity> filterNames = new ArrayList<>();

        for (NotesEntity notes : this.filterNotesAllItem) {
            if (notes.notesTitle.contains(newText) || notes.notesSubTitle.contains(newText)) {
                filterNames.add(notes);
            }
        }
        this.adapter.searchNote(filterNames);

        Log.e("@@@@", "Notes Filter " + newText);
    }
}