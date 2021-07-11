package com.pramodkarki.noteapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.pramodkarki.noteapp.model.NotesEntity;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM notes_dB ")
    LiveData<List<NotesEntity>> getAllNotes();

    @Query("SELECT * FROM notes_dB ORDER BY notes_priority DESC")
    LiveData<List<NotesEntity>> getHighToLowNotes();

    @Query("SELECT * FROM notes_dB ORDER BY notes_priority ASC")
    LiveData<List<NotesEntity>> getLowToHighNotes();

    @Insert
    void insertNotes(NotesEntity... notes);

    @Query("DELETE FROM notes_dB WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(NotesEntity... notes);

}
