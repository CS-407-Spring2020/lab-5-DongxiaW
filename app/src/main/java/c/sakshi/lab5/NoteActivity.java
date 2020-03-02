package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {

    int noteid = -1;
    EditText note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        // get edit text view
        note = findViewById(R.id.notes);
        // get intent from activity 2
        Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid",-1);
        Log.i("Info","noteid point2");
        if (noteid != -1){
            Note note2 = Main2Activity.notes.get(noteid);
            String noteContent = note2.getContent();
            note.setText(noteContent);
        }

    }



    public void saveMethod(View view){
        Log.i("Info","check point1");
        note = findViewById(R.id.notes);
        String newNote = note.getText().toString();

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", context.MODE_PRIVATE,null);
        DBHelper database = new DBHelper(sqLiteDatabase);

        SharedPreferences checklogin = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = checklogin.getString(MainActivity.usernameKey,"");

        // Save information to database
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) { // add new note
            title = "NOTE_" + (Main2Activity.notes.size() + 1);
            database.saveNotes(username,title, newNote, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            database.updateNote(title, date, newNote, username);
        }

        Log.i("Info","check point2");
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("message", username);
        startActivity(intent);
    }
}
