package com.metingokmen.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText textUsername;
    EditText textPassword;
    String password;
    String username;
    TextView textView;
    int lastScore;
    int score;
    ArrayList<Integer> scoreList;
    Runnable runnable;
    Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        scoreList = new ArrayList<Integer>();
        textView = findViewById(R.id.textView);
        sharedPreferences = getSharedPreferences("com.metingokmen.catchthekenny",MODE_PRIVATE);
        Intent intent = getIntent();
        textUsername = findViewById(R.id.textName);
        textPassword = findViewById(R.id.textPassword);






        scoreList.add(intent.getIntExtra("score",0));
        if(intent.getIntExtra("score",0) > lastScore){
            textView.setText("Last score: " + intent.getIntExtra("score",0));
        }
        else{
            textView.setText("Last score: " + lastScore);
        }

        textView.setText("Last score: " + intent.getIntExtra("score",0));





        username = sharedPreferences.getString("username","");
        password = sharedPreferences.getString("password","");

    }

    public void login(View view){
        Intent intent = new Intent(MainActivity2.this,MainActivity.class);
        String enteredUsername = textUsername.getText().toString();
        String enteredPassword = textPassword.getText().toString();
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity2.this);
        alert.setTitle("Error!");
        alert.setMessage("Invalid input");
        if(textUsername.getText().toString().matches("") || textPassword.getText().toString().matches("")){
            alert.show();

        }
        else{
            if(enteredUsername.matches(username) && enteredPassword.matches(password)){
                Toast.makeText(MainActivity2.this, "Login successful", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            else{
                Toast.makeText(MainActivity2.this,"Username or password is incorrect",Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void signUp(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity2.this);
        alert.setTitle("Error!");
        alert.setMessage("Invalid input");

        if(!((textUsername.getText().toString().matches("")) || (textPassword.getText().toString().matches("")))){
            Toast.makeText(MainActivity2.this,"Sign up succesful" , Toast.LENGTH_SHORT).show();
            sharedPreferences.edit().putString("username",textUsername.getText().toString()).apply();
            sharedPreferences.edit().putString("password",textPassword.getText().toString()).apply();
            username = sharedPreferences.getString("username","");
            password = sharedPreferences.getString("password","");

        }
        else{
            alert.show();
        }



    }
}