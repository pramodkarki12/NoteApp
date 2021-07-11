package com.pramodkarki.noteapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pramodkarki.noteapp.model.NotesEntity;
import com.pramodkarki.noteapp.dao.NotesDao;

@Database(entities = {NotesEntity.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NotesDao notesDao();

    public static NotesDatabase INSTANCE;

    public static NotesDatabase getDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NotesDatabase.class,
                    "notes_dB").build();
        }
        return INSTANCE;
    }
}
