package com.pramodkarki.noteapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.pramodkarki.noteapp.R;
import com.pramodkarki.noteapp.databinding.ActivityUpdateNotesBinding;
import com.pramodkarki.noteapp.model.NotesEntity;
import com.pramodkarki.noteapp.viewModel.NotesViewModel;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {

    ActivityUpdateNotesBinding updateBinding;
    String COLOR_PRIORITY = "1";

    NotesViewModel notesViewModel;

    String mTitle, mSubTitle, mPriority, mNotes;
    int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateBinding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(updateBinding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        /** Get the message from the previous intent(i.e., MainActivity) */
        mId = getIntent().getIntExtra("id", 0);
        mTitle = getIntent().getStringExtra("title");
        mSubTitle = getIntent().getStringExtra("subTitle");
        mPriority = getIntent().getStringExtra("priority");
        mNotes = getIntent().getStringExtra("notes");

        /** set the above messages into their respective fields */
        updateBinding.updateTitle.setText(mTitle);
        updateBinding.updateSubtitle.setText(mSubTitle);
        updateBinding.updateNotes.setText(mNotes);
        switch (mPriority) {
            case "1":
                updateBinding.greenPriority.setImageResource(R.drawable.ic_baseline_check);
                break;
            case "2":
                updateBinding.yellowPriority.setImageResource(R.drawable.ic_baseline_check);
                break;
            case "3":
                updateBinding.redPriority.setImageResource(R.drawable.ic_baseline_check);
                break;
        }

        /** if user choose any of theses priority, then the check image will be displayed */
        updateBinding.greenPriority.setOnClickListener(v -> {
            updateBinding.greenPriority.setImageResource(R.drawable.ic_baseline_check);
            updateBinding.yellowPriority.setImageResource(0);
            updateBinding.redPriority.setImageResource(0);
        });

        updateBinding.yellowPriority.setOnClickListener(v -> {
            updateBinding.greenPriority.setImageResource(0);
            updateBinding.yellowPriority.setImageResource(R.drawable.ic_baseline_check);
            updateBinding.redPriority.setImageResource(0);
        });

        updateBinding.redPriority.setOnClickListener(v -> {
            updateBinding.greenPriority.setImageResource(0);
            updateBinding.yellowPriority.setImageResource(0);
            updateBinding.redPriority.setImageResource(R.drawable.ic_baseline_check);
        });

        updateBinding.updateNotesBtn.setOnClickListener(v -> {
            String title = updateBinding.updateTitle.getText().toString();
            String subTitle = updateBinding.updateSubtitle.getText().toString();
            String notes = updateBinding.updateNotes.getText().toString();

            updateNotes(title, subTitle, notes);
        });
    }

    public void updateNotes(String title, String subTitle, String notes) {
        Date date = new Date();
        CharSequence dateSequence = DateFormat.format("MMMM d, yyyy", date.getTime());

        NotesEntity updateNotes = new NotesEntity();
        updateNotes.notesTitle = title;
        updateNotes.notesSubTitle = subTitle;
        updateNotes.notes = notes;
        updateNotes.notesPriority = COLOR_PRIORITY;
        updateNotes.notesDate = dateSequence.toString();

        notesViewModel.updateNote(updateNotes);

        Toast.makeText(this, "Notes Updated Successfully!!", Toast.LENGTH_SHORT).show();
        finish();
    }
}