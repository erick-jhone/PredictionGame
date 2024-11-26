package com.erick.prova1_erick_jhone_rodrigues_da_silva.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.erick.prova1_erick_jhone_rodrigues_da_silva.R;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.Guess;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.Player;

import java.util.ArrayList;

public class NumberPredictionActivity extends AppCompatActivity {

    private int totalAnimalValue;
    private int attempts = 0;
    private Player player;
    private ImageView heart1, heart2, heart3;
    private EditText guessEditText;
    private ArrayList<Guess> guesses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_number_prediction);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            totalAnimalValue = extras.getInt("totalAnimalValue", 0);
            player = extras.getParcelable("player");
        }

        guessEditText = findViewById(R.id.editTextText);
        heart1 = findViewById(R.id.heart1);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);
        Button submitGuessButton = findViewById(R.id.submitGuessButton);

        submitGuessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });
    }

    private void checkGuess() {
        String guessStr = guessEditText.getText().toString();
        try {
            int guess = Integer.parseInt(guessStr);
            Guess guessObject = new Guess(guess, totalAnimalValue);
            guesses.add(guessObject);

            if (guessObject.isResultCorrect()) {
                Toast.makeText(this, "Correct! You guessed it!", Toast.LENGTH_SHORT).show();
                player.setScore(player.getScore() + 10);
                navigateToReport();
            } else {
                attempts++;
                Toast.makeText(this, "Wrong guess. Try again!", Toast.LENGTH_SHORT).show();
                updateHearts();
                if (attempts == 3) {
                    Toast.makeText(this, "You lost!", Toast.LENGTH_SHORT).show();
                    navigateToReport();
                }
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number.", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToReport() {
        Intent intent = new Intent(NumberPredictionActivity.this, ReportActivity.class);
        intent.putParcelableArrayListExtra("guessList", guesses);
        intent.putExtra("player", player);
        startActivity(intent);
    }

    private void updateHearts() {
        switch (attempts) {
            case 1:
                heart1.setImageResource(R.drawable.coracao_partido);
                break;
            case 2:
                heart2.setImageResource(R.drawable.coracao_partido);
                break;
            case 3:
                heart3.setImageResource(R.drawable.coracao_partido);
                break;
            default:
                break;
        }
    }
}