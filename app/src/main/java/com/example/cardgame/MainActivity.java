package com.example.cardgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_KEY_SCORE = "EXTRA_KEY_SCORE";
    public static final int PLAYER1_REQUEST_CODE= 1;
    public static final int PLAYER2_REQUEST_CODE= 2;
    private final int MAX_ROUNDS = 26;
    private ImageView main_IMG_play;
    private ImageView main_IMG_unfolded1, main_IMG_unfolded2;
    private TextView main_LBL_score1, main_LBL_score2;
    private int leftCardNum, rightCardNum;
    private int[][] images;
    private int counterRounds=0;
    private int rightPlayerScore = 0, leftPlayerScore = 0;
    private Random rand = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_IMG_unfolded1 = findViewById(R.id.main_IMG_unfolded1);
        main_IMG_unfolded2 = findViewById(R.id.main_IMG_unfolded2);
        main_LBL_score1 = findViewById(R.id.main_LBL_score1);
        main_LBL_score2 = findViewById(R.id.main_LBL_score2);
        main_IMG_play = findViewById(R.id.main_IMG_play);

        main_IMG_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
                if(counterRounds>=MAX_ROUNDS){
                    winner();
                }
            }
        });
    }

    private void startGame() {

        images = new int[][]{{R.drawable.img_card_2d, R.drawable.img_card_2t, R.drawable.img_card_2l, R.drawable.img_card_2h},
                {R.drawable.img_card_3d, R.drawable.img_card_3t, R.drawable.img_card_3l, R.drawable.img_card_3h},
                {R.drawable.img_card_4d, R.drawable.img_card_4t, R.drawable.img_card_4l, R.drawable.img_card_4h},
                {R.drawable.img_card_5d, R.drawable.img_card_5t, R.drawable.img_card_5l, R.drawable.img_card_5h},
                {R.drawable.img_card_6d, R.drawable.img_card_6t, R.drawable.img_card_6l, R.drawable.img_card_6h},
                {R.drawable.img_card_7d, R.drawable.img_card_7t, R.drawable.img_card_7l, R.drawable.img_card_7h},
                {R.drawable.img_card_8d, R.drawable.img_card_8t, R.drawable.img_card_8l, R.drawable.img_card_8h},
                {R.drawable.img_card_9d, R.drawable.img_card_9t, R.drawable.img_card_9l, R.drawable.img_card_9h},
                {R.drawable.img_card_10d, R.drawable.img_card_10t, R.drawable.img_card_10l, R.drawable.img_card_10h},
                {R.drawable.img_card_11d, R.drawable.img_card_11t, R.drawable.img_card_11l, R.drawable.img_card_11h},
                {R.drawable.img_card_12d, R.drawable.img_card_12t, R.drawable.img_card_12l, R.drawable.img_card_12h},
                {R.drawable.img_card_13d, R.drawable.img_card_13t, R.drawable.img_card_13l, R.drawable.img_card_13h},
                {R.drawable.img_card_14d, R.drawable.img_card_14t, R.drawable.img_card_14l, R.drawable.img_card_14h}};

        leftCardNum = rand.nextInt(13); // todo check arr length and if it can choose 0 value
        rightCardNum = rand.nextInt(13);

        int leftCardShape = rand.nextInt(3); // todo check arr length and if it can choose 0 value
        int rightCardShape = rand.nextInt(3);

        main_IMG_unfolded1.setImageResource(images[rightCardNum][rightCardShape]);
        main_IMG_unfolded2.setImageResource(images[leftCardNum][leftCardShape]);

        checkWhoWins(leftCardNum, rightCardNum);
        counterRounds++;

    }
        private void winner () {
            if (leftPlayerScore > rightPlayerScore) {
                openWinnerMessagePlayer2();
            } else {
                openWinnerMessagePlayer1();
            }

        }

        private void openWinnerMessagePlayer1 (){
            Intent myIntent = new Intent(this, Activity_Winner1.class);
        myIntent.putExtra(EXTRA_KEY_SCORE, rightPlayerScore);
        startActivityForResult(myIntent,PLAYER1_REQUEST_CODE);
        }
        private void openWinnerMessagePlayer2 (){
            Intent myIntent = new Intent(this, Activity_Winner2.class);
            myIntent.putExtra(EXTRA_KEY_SCORE,leftPlayerScore);
            startActivityForResult(myIntent,PLAYER2_REQUEST_CODE);
        }
        private void checkWhoWins ( int leftCard, int rightCard ){
            if (rightCard > leftCard ) {
                rightPlayerScore++;
                main_LBL_score1.setText(String.valueOf(rightPlayerScore));
            }
            else if (rightCard < leftCard ) {
                leftPlayerScore++;
                main_LBL_score2.setText(String.valueOf(leftPlayerScore));

            } else {
                rightPlayerScore++;
                main_LBL_score1.setText(String.valueOf(rightPlayerScore));
                leftPlayerScore++;
                main_LBL_score2.setText(String.valueOf(leftPlayerScore));
            }

        }

    private void updateScorePlayerRight(int score){
        rightPlayerScore=score;
        main_LBL_score1.setText(String.valueOf(rightPlayerScore));
    }
        private void updateScorePlayerLeft(int score){
            leftPlayerScore=score;
            main_LBL_score2.setText(String.valueOf(leftPlayerScore));
        }

        @Override
        protected void onStart () {
            Log.d("pttt", "onStart");
            super.onStart();

        }

        @Override
        protected void onResume () {
            Log.d("pttt", "onResume");
            super.onResume();
        }

        @Override
        protected void onPause () {
            Log.d("pttt", "onPause");
            super.onPause();
        }

        @Override
        protected void onStop () {
            Log.d("pttt", "onStop");
            super.onStop();

        }

        @Override
        protected void onDestroy () {
            Log.d("pttt", "onDestroy");
            super.onDestroy();
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && (requestCode==PLAYER1_REQUEST_CODE || requestCode==PLAYER2_REQUEST_CODE)){
            counterRounds=0;
            updateScorePlayerRight(0);
            updateScorePlayerLeft(0);
        }
    }
}
