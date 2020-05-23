package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //test population of RecyclerView
        ArrayList<String> noteListArr = new ArrayList<String>();
        for (int i = 0; i < 100; i++) { //TODO: Delete this for loop
            noteListArr.add("Note " + String.valueOf(i));
        }

        //Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.noteList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, noteListArr);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


    }

    public void onClickAddNewNote(View view){

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position), Toast.LENGTH_SHORT).show(); //TODO: Delete this line
    }
}
