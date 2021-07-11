package com.pramodkarki.noteapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.pramodkarki.noteapp.dao.NotesDao;
import com.pramodkarki.noteapp.database.NotesDatabase;
import com.pramodkarki.noteapp.model.NotesEntity;

import java.util.List;

public class NotesRepository {

    public NotesDao nDao;
    public LiveData<List<NotesEntity>> getLiveNotesData;

    public NotesRepository(Application application) {
        NotesDatabase db = NotesDatabase.getDatabaseInstance(application);

        nDao = db.notesDao();
        getLiveNotesData = nDao.getAllNotes();
    }

    public void insertNotes(NotesEntity notes) {
        nDao.insertNotes(notes);
    }

    public void deleteNotes(int noteId) {
        nDao.deleteNotes(noteId);
    }

    public void updateNotes(NotesEntity notes) {
        nDao.updateNotes(notes);
    }
}
