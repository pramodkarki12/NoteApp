package com.pramodkarki.noteapp;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM notes_dB ")
    List<NotesEntity> getAllNotes();

    @Insert
    void insertNotes(NotesEntity... notes);

    @Query("DELETE FROM notes_dB WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(NotesEntity... notes);

}
