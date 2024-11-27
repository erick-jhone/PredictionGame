package com.erick.prova1_erick_jhone_rodrigues_da_silva.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.erick.prova1_erick_jhone_rodrigues_da_silva.R;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.Guess;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.Player;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.utils.navigation.NavigationKeys;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.view.ResultDialog;

import java.util.ArrayList;

public class NumberPredictionActivity extends AppCompatActivity {

    private int totalAnimalValue;
    private int numberOfAtttemps = 0;
    private Player player;
    private ImageView heart1, heart2, heart3;
    private Button submitGuessButton;
    private EditText guessEditText;
    private ArrayList<Guess> guesses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_number_prediction);

        getExtras();
        initUIComponents();
        setupListeners();
    }

    private void getExtras() {
        Intent intent = getIntent();
        totalAnimalValue = intent.getIntExtra(NavigationKeys.TOTAL_ANIMAL_VALUE, 0);
        player = intent.getParcelableExtra(NavigationKeys.PLAYER);
    }

    private void initUIComponents() {
        guessEditText = findViewById(R.id.editTextText);
        heart1 = findViewById(R.id.heart1);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);
        submitGuessButton = findViewById(R.id.submitGuessButton);
    }

    private void setupListeners() {
        submitGuessButton.setOnClickListener(v -> checkGuess());
    }

    private void checkGuess() {
        String guessStr = guessEditText.getText().toString();
        try {
            int guess = Integer.parseInt(guessStr);
            Guess currentGuess = new Guess(guess, totalAnimalValue);
            guesses.add(currentGuess);
            currentGuessConditionalActions(currentGuess);
        } catch (NumberFormatException e) {
            Toast.makeText(this, getString(R.string.insira_um_numero_valido), Toast.LENGTH_SHORT).show();
        }
    }

    private void currentGuessConditionalActions(Guess currentGuess) {
        if (currentGuess.isResultCorrect()) {
            player.setScore(player.getScore() + 10);
            showResultDialog(true);
        } else {
            numberOfAtttemps++;
            updateHearts();
            if (numberOfAtttemps == 3) {
                showResultDialog(false);
            }
        }
    }

    private void showResultDialog(boolean isCorrectAnswer) {
        ResultDialog resultDialog = new ResultDialog(isCorrectAnswer);
        resultDialog.setConfirmDeleteListener(() -> navigateToReport());
        resultDialog.show(getSupportFragmentManager(), "result_dialog");
    }

    private void navigateToReport() {
        Intent intent = new Intent(NumberPredictionActivity.this, ReportActivity.class);
        intent.putParcelableArrayListExtra(NavigationKeys.GUESS_LIST, guesses);
        intent.putExtra(NavigationKeys.PLAYER, player);
        startActivity(intent);
    }

    private void updateHearts() {
        switch (numberOfAtttemps) {
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