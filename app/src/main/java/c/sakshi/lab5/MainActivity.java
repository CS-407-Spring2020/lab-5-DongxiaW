package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static String usernameKey = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences checklogin = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);

        if(!checklogin.getString(usernameKey,"").equals("")){
            String user = checklogin.getString(usernameKey,"");
            gotonote(user);
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    public void onButtonClick(View view) {
        EditText password = (EditText) findViewById(R.id.password);
        EditText username = (EditText) findViewById(R.id.username);

        String user = username.getText().toString();

        SharedPreferences LoginInfo = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        LoginInfo.edit().putString("username", user).apply();
        gotonote(user);

    }

    public void gotonote(String s) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("message", s);
        startActivity(intent);
    }

}
