package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    TextView welcome;

    public static ArrayList<Note> notes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // display welcome message
        welcome = findViewById(R.id.welcomeNote);
        Intent intent = getIntent();
        String user = intent.getStringExtra("message");
        //Log.i("Info", str);
        welcome.setText("Welcome " + user + "!");

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", context.MODE_PRIVATE,null);
        DBHelper database = new DBHelper(sqLiteDatabase);

        // initiate the notes class variable
        notes = database.readNotes(user);

        ArrayList<String> displayNotes = new ArrayList<>();
        // get the content of the user's notes
        for (Note note: notes) {
            displayNotes.add(String.format("Title:%s\nDate:%s", note.getTitle(), note.getDate()));
        }

        // use listview view to display notes on screens
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.notesListView);
        listView.setAdapter(adapter);

        // add onItemClickListener for ListView item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Initialise intent ot take user to third activity (NoteActivity)
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                intent.putExtra("noteid", position);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()) {
            case R.id.logout:
                Intent activity1 = new Intent(this, MainActivity.class);
                SharedPreferences LoginInfo = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
                LoginInfo.edit().remove(MainActivity.usernameKey).apply();
                startActivity(activity1);
                return true;

            case R.id.addnote:
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                startActivity(intent);
                return true;



             default: return super.onOptionsItemSelected(item);

        }
    }

}


