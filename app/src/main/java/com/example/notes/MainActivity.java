package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    public static final String EXTRA_ID = "com.example.notes.EXTRA_ID";

    RecyclerViewAdapter adapter;
    private List<Note> allNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();

        NoteDAO noteDAO = database.getNoteDAO();


        allNotes = noteDAO.getAllNotes();

        //populate the ArrayList for the RecyclerView
        ArrayList<String> noteListArr = new ArrayList<String>();
        for (int i = 0; i < allNotes.size(); i++) {
            noteListArr.add(allNotes.get(i).getNoteTitle());
        }

        //Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.noteList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, noteListArr);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }

    public void onClickAddNewNote(View view){
        Intent intent = new Intent(this, CreateNote.class);
        startActivity(intent);

    }

    //Searches through the database for the id of a given note
    public long findID(String title){
        boolean found = false;
        int i=0;
        while((found==false)&&(i<allNotes.size())){
            if(allNotes.get(i).getNoteTitle()==title){
                found = true;
            }
            else{
                i+=1;
            }
        }
        return allNotes.get(i).getId();
    }

    @Override
    public void onItemClick(View view, int position) {
        String id = String.valueOf(findID(adapter.getItem(position)));
        Intent intent = new Intent(this, EditNote.class);
        intent.putExtra(EXTRA_ID, id);
        startActivity(intent);
    }
}
