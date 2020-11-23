package com.example.cardgame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Winner1 extends AppCompatActivity {


    private TextView winner1_LBL_score;
    private ImageView winner1_IMG_close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner1);

        winner1_LBL_score = findViewById(R.id.winner1_LBL_score);
        winner1_IMG_close = findViewById(R.id.winner1_IMG_close);


        winner1_IMG_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });



        int scoreFromPreviousActivity = getIntent().getIntExtra(MainActivity.EXTRA_KEY_SCORE, 0);

        winner1_LBL_score.setText("" + scoreFromPreviousActivity);

    }

    private void closeActivity() {
        setResult(RESULT_OK,new Intent());
        finish();
    }


    @Override
    protected void onStart() {
        Log.d("pttt", "onStart");
        super.onStart();

    }

    @Override
    protected void onResume() {
        Log.d("pttt", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("pttt", "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("pttt", "onStop");
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        Log.d("pttt", "onDestroy");
        super.onDestroy();
    }




}
