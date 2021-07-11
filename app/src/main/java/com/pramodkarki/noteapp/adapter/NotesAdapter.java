package com.pramodkarki.noteapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pramodkarki.noteapp.MainActivity;
import com.pramodkarki.noteapp.R;
import com.pramodkarki.noteapp.activity.UpdateNotesActivity;
import com.pramodkarki.noteapp.model.NotesEntity;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity;
    List<NotesEntity> notes;

    public NotesAdapter(MainActivity mainActivity, List<NotesEntity> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
    }

    @Override
    public NotesAdapter.notesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes, parent, false));
    }

    @Override
    public void onBindViewHolder(NotesAdapter.notesViewHolder holder, int position) {
        NotesEntity note = notes.get(position);

        switch (note.notesPriority) {
            case "1":
                holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
                break;
            case "2":
                holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
                break;
            case "3":
                holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
                break;
        }
        holder.title.setText(note.notesTitle);
        holder.subTitle.setText(note.notesSubTitle);
        holder.notesDate.setText(note.notesDate);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, UpdateNotesActivity.class);

            intent.putExtra("id", note.id);
            intent.putExtra("title", note.notesTitle);
            intent.putExtra("subTitle", note.notesSubTitle);
            intent.putExtra("priority", note.notesPriority);
            intent.putExtra("notes", note.notes);

            mainActivity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class notesViewHolder extends RecyclerView.ViewHolder {
        TextView title, subTitle, notesDate;
        View notesPriority;

        public notesViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notesTitle);
            subTitle = itemView.findViewById(R.id.notesSubtitle);
            notesDate = itemView.findViewById(R.id.notesDate);
            notesPriority = itemView.findViewById(R.id.notesPriority);
        }
    }
}
