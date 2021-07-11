package com.pramodkarki.noteapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_icon) {
            BottomSheetDialog dialog = new BottomSheetDialog(UpdateNotesActivity.this, R.style.BottomSheetStyle);

            View view = LayoutInflater.from(UpdateNotesActivity.this)
                    .inflate(R.layout.delete_confirmation_dialog, (LinearLayout) findViewById(R.id.bottomSheet));

            dialog.setContentView(view);

            TextView yes_btn, no_btn;
            yes_btn = view.findViewById(R.id.yes_delete);
            no_btn = view.findViewById(R.id.no_delete);

            yes_btn.setOnClickListener(v -> {
                notesViewModel.deleteNote(mId);
                finish();
            });

            no_btn.setOnClickListener(v -> {
                dialog.dismiss();
            });
            dialog.show();
        }
        return true;
    }
}