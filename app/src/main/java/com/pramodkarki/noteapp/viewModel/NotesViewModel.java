package com.pramodkarki.noteapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.pramodkarki.noteapp.model.NotesEntity;
import com.pramodkarki.noteapp.repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    public NotesRepository repository;
    public LiveData<List<NotesEntity>> getAllNotes;

    public NotesViewModel(Application application) {
        super(application);

        repository = new NotesRepository(application);
        getAllNotes = repository.getLiveNotesData;
    }

    public void insertNote(NotesEntity notes) {
        repository.insertNotes(notes);
    }

    public void deleteNote(int id) {
        repository.deleteNotes(id);
    }

    public void updateNote(NotesEntity notes) {
        repository.updateNotes(notes);
    }
}
