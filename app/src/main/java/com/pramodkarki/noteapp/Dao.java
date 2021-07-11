package com.pramodkarki.noteapp;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM NotesDB ")
    List<Notes> getAllNotes();

    @Insert
    void insertNotes(Notes... notes);

    @Query("DELETE FROM NotesDB WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes... notes);

}
