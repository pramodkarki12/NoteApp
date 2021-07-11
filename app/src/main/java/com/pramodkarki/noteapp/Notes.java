package com.pramodkarki.noteapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NotesDB")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "notes_title")
    String notesTitle;

    @ColumnInfo(name = "notes_subtitle")
    String notesSubTitle;

    @ColumnInfo(name = "notes")
    String notes;

    @ColumnInfo(name = "notes_date")
    String notesDate;

    @ColumnInfo(name = "notes_priority")
    String notesPriority;


}
