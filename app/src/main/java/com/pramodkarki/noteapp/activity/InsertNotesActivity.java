package com.pramodkarki.noteapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pramodkarki.noteapp.R;
import com.pramodkarki.noteapp.databinding.ActivityInsertNotesBinding;
import com.pramodkarki.noteapp.model.NotesEntity;
import com.pramodkarki.noteapp.viewModel.NotesViewModel;

import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding insertBinding;
    String title, subtitle, data;
    NotesViewModel notesViewModel;

    String COLOR_PRIORITY = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        insertBinding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(insertBinding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        insertBinding.greenPriority.setOnClickListener(v -> {
            insertBinding.greenPriority.setImageResource(R.drawable.ic_baseline_check);
            insertBinding.yellowPriority.setImageResource(0);
            insertBinding.redPriority.setImageResource(0);

            COLOR_PRIORITY = "1";
        });

        insertBinding.yellowPriority.setOnClickListener(v -> {
            insertBinding.greenPriority.setImageResource(0);
            insertBinding.yellowPriority.setImageResource(R.drawable.ic_baseline_check);
            insertBinding.redPriority.setImageResource(0);

            COLOR_PRIORITY = "2";
        });

        insertBinding.redPriority.setOnClickListener(v -> {
            insertBinding.greenPriority.setImageResource(0);
            insertBinding.yellowPriority.setImageResource(0);
            insertBinding.redPriority.setImageResource(R.drawable.ic_baseline_check);

            COLOR_PRIORITY = "3";
        });

        insertBinding.doneNotesBtn.setOnClickListener( v -> {
           title = insertBinding.notesTitle.getText().toString();
           subtitle = insertBinding.notesSubtitle.getText().toString();
           data = insertBinding.notesData.getText().toString();

           createNotes(title, subtitle, data);

        });
    }

    public void createNotes(String title, String subtitle, String data) {

        Date date = new Date();
        CharSequence dateSequence = DateFormat.format("MMMM d, YYYY", date.getTime());

        NotesEntity notes = new NotesEntity();
        notes.notesTitle = title;
        notes.notesSubTitle = subtitle;
        notes.notes = data;
        notes.notesPriority = COLOR_PRIORITY;
        notes.notesDate = dateSequence.toString();

        notesViewModel.insertNote(notes);

        Toast.makeText(this, "Notes Created Successfully!!", Toast.LENGTH_SHORT).show();

        finish();
    }
}