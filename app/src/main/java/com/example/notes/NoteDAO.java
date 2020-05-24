package com.example.notes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {
    @Insert
    public void insert(Note... notes);
    @Update
    public void update(Note... notes);
    @Delete
    public void delete(Note... note);

    //Get all notes
    @Query("SELECT * FROM notesTable")
    public List<Note> getAllNotes();

    //Get specific note
    @Query("SELECT * FROM notesTable WHERE id = :id")
    public Note getNote(Long id);

    //Updates specific note
    @Query("UPDATE notesTable SET noteContent = :noteContent WHERE id = :id")
    public void updateOneNote(String noteContent, long id);

//    //Deletes all data from the database - Only used for testing
//    @Query("DELETE FROM notesTable")
//    public void doNotUse();

}
