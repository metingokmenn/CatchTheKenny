package com.metingokmen.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timeText;
    TextView scoreText;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imgArray;
    int score;
    int lastScore;
    Handler handler;
    Runnable runnable;
    GridLayout gridLayout;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);




        imgArray = new ImageView[] {imageView,imageView2,imageView3,imageView4,
                imageView5,imageView6,imageView7,imageView8,imageView9};
        hideImages();


        sharedPreferences = getSharedPreferences("com.metingokmen.catchthekenny",MODE_PRIVATE);
        sharedPreferences.edit().putInt("highScore",lastScore).apply();
        sharedPreferences.edit().putInt("score",score).apply();



        CountDownTimer cdt = new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("Time: " + (l/1000));
            }

            @Override
            public void onFinish() {
                timeText.setText("Time off");
                handler.removeCallbacks(runnable);
                for (ImageView image: imgArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart ?");
                alert.setMessage("Do you want to continue to game ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //restart
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Game Over",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity2.class);

                        intent.putExtra("score",score);
                        startActivity(intent);


                    }
                });
                alert.show();

            }
        };
        cdt.start();
        score = 0;


    }
    public void increaseScore(View view){
        score++;
        scoreText.setText("Score: " + score);

    }

    public void hideImages(){
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image: imgArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random r = new Random();
                int i = r.nextInt(9);
                imgArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable ,500); //(this,1000) olabilirdi.
            }
        };

        handler.post(runnable);


    }
}