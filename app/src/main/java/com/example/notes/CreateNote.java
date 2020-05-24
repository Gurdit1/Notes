package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateNote extends AppCompatActivity {

    AppDatabase database;
    NoteDAO noteDAO;
    private ArrayList<String> noteListArr;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        editText = findViewById(R.id.editText);

        database = Room.databaseBuilder(this, AppDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();

        noteDAO = database.getNoteDAO();
        List<Note> allNotes = noteDAO.getAllNotes();

        //populate the ArrayList to check for pre-existing notes
        noteListArr = new ArrayList<String>();
        for (int i = 0; i < allNotes.size(); i++) {
            noteListArr.add(allNotes.get(i).getNoteTitle());
        }


    }



    public void onClick(View view){
        String noteTitle = editText.getText().toString();

        //TODO: Check for repeated titles
        boolean valid = true;

        if(valid==false){
            Toast.makeText(CreateNote.this, "Title already exists", Toast.LENGTH_SHORT).show();
        }
        else{
            Note newNote = new Note();
            newNote.setNoteTitle(noteTitle);
            newNote.setNoteContent("");
            noteDAO.insert(newNote);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
