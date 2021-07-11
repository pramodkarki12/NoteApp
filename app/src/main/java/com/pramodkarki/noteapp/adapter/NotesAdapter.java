package com.pramodkarki.noteapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pramodkarki.noteapp.MainActivity;
import com.pramodkarki.noteapp.R;
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

        if (note.notesPriority.equals("1")) {
            holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
        } else if (note.notesPriority.equals("2")) {
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
        } else if (note.notesPriority.equals("3")) {
            holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
        }

        holder.title.setText(note.notesTitle);
        holder.subTitle.setText(note.notesSubTitle);
        holder.notesDate.setText(note.notesDate);
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
            subTitle = itemView.findViewById(R.id.subtitle);
            notesDate = itemView.findViewById(R.id.notesDate);
            notesPriority = itemView.findViewById(R.id.notesPriority);
        }
    }
}
