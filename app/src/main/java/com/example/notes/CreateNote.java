package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CreateNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
    }

    public void onClick(View view){
        //TODO: Check if title already exists
        boolean valid = true;

        if(valid=false){
            Toast.makeText(CreateNote.this, "Title already exists", Toast.LENGTH_SHORT).show();
        }
        else{

        }
    }
}
