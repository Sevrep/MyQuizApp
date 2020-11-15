package com.sevrep.myquizapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String SCORE_KEY = "SCORE";
    private final String INDEX_KEY = "INDEX";
    private final String REMAINING_KEY = "REMAINING";

    private TextView tv_question, tv_remaining;
    private ProgressBar mProgressBar;
    private int mQuestionIndex;
    private int mQuizQuestion;
    private int mUserScore;
    QuizModel[] mQuestionsCollection = new QuizModel[] {
            new QuizModel(R.string.q1, true),
            new QuizModel(R.string.q2, true),
            new QuizModel(R.string.q3, true),
            new QuizModel(R.string.q4, true),
            new QuizModel(R.string.q5, true),
            new QuizModel(R.string.q6, true),
            new QuizModel(R.string.q7, true),
            new QuizModel(R.string.q8, true),
            new QuizModel(R.string.q9, true),
            new QuizModel(R.string.q10, true)
    };
    final int USER_PROGRESS = (int) Math.ceil(100.0 / mQuestionsCollection.length);

    String scoreText = "Score: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_remaining = findViewById(R.id.tv_remaining);

        if (savedInstanceState != null) {
            mUserScore = savedInstanceState.getInt(SCORE_KEY);
            mQuestionIndex = savedInstanceState.getInt(INDEX_KEY);
            tv_remaining.setText(savedInstanceState.getString(REMAINING_KEY));
        } else {
            mUserScore = 0;
            mQuestionIndex = 0;
        }

        Toast.makeText(this, "onCreate method is called.", Toast.LENGTH_SHORT).show();

        Button bt_true = findViewById(R.id.bt_true);
        bt_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateAnswer(true);
                updateQuestion();
            }
        });

        Button bt_false = findViewById(R.id.bt_false);
        bt_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateAnswer(false);
                updateQuestion();
            }
        });

        tv_question = findViewById(R.id.tv_question);
        QuizModel quizModel = mQuestionsCollection[mQuestionIndex];
        mQuizQuestion = quizModel.getQuestion();
        tv_question.setText(mQuizQuestion);

        mProgressBar = findViewById(R.id.pb_quizprogressbar);
    }

    private void updateQuestion() {
        mQuestionIndex = (mQuestionIndex + 1) % 10;
        if(mQuestionIndex == 0) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setCancelable(false);
            adb.setTitle("The quiz is finished.");
            adb.setMessage("Your score is " + mUserScore);
            adb.setPositiveButton("Finish the quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            adb.show();
        } else {
            mQuizQuestion = mQuestionsCollection[mQuestionIndex].getQuestion();
            tv_question.setText(mQuizQuestion);
        }
        mProgressBar.incrementProgressBy(USER_PROGRESS);
        scoreText = "Score: " + mUserScore;
        tv_remaining.setText(scoreText);
    }

    private void evaluateAnswer(boolean userGuess) {
        boolean currentQA = mQuestionsCollection[mQuestionIndex].isAnswer();
        if (currentQA == userGuess) {
            Toast.makeText(this, R.string.correct_toast_message, Toast.LENGTH_SHORT).show();
            mUserScore++;
        } else {
            Toast.makeText(this, R.string.incorrect_toast_message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart method is called.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume method is called.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause method is called.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop method is called.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy method is called.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(SCORE_KEY, mUserScore);
        outState.putInt(INDEX_KEY, mQuestionIndex);
        outState.putString(REMAINING_KEY, scoreText);

        Toast.makeText(this, "onSaveInstanceState method is called.", Toast.LENGTH_SHORT).show();
    }
}