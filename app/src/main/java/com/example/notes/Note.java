package com.example.notes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.security.Key;

@Entity(tableName="notesTable")
public class Note{
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String noteTitle;
    private String noteContent;

}
