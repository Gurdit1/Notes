package com.example.notes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toolbar;

public class EditNote extends AppCompatActivity {

    private EditText editText;
    AppDatabase database;
    NoteDAO noteDAO;
    private long id;
    private String noteTitle;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        editText = findViewById(R.id.editText2);

        database = Room.databaseBuilder(this, AppDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();

        Intent intent = getIntent();
        String idStr = intent.getStringExtra(MainActivity.EXTRA_ID);
        id = Long.parseLong(idStr);

        noteDAO = database.getNoteDAO();
        Note note = noteDAO.getNote(id);

        noteTitle = note.getNoteTitle();
        String noteContent = note.getNoteContent();

        //change toolbar name
        getSupportActionBar().setTitle(noteTitle);

        //sets EditText
        editText.setText(noteContent);

    }

    public void onClickSave(View view){
        String noteContent = editText.getText().toString();
        noteDAO.updateOneNote(noteContent, id);
        goToMainActivity();
    }

    public void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickDelete(View view){
        new AlertDialog.Builder(this).setTitle("Delete note").setMessage("Are you sure you want to delete " + noteTitle + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        //Deletes this note
                        noteDAO.deleteOneNote(id);

                        goToMainActivity();



                    }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do nothing
            }
        }).setIcon(android.R.drawable.ic_dialog_alert).show();

    }
}
