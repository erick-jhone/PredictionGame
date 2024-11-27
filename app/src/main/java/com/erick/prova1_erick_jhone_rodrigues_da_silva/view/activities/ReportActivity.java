package com.erick.prova1_erick_jhone_rodrigues_da_silva.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.erick.prova1_erick_jhone_rodrigues_da_silva.R;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.Guess;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.Player;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.utils.ChineseZodiacCalculatorUtil;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.utils.ToolbarUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ReportActivity extends AppCompatActivity {

    private ArrayList<Guess> guessList;
    private Player player;
    private Set<String> currentScoreSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Toolbar toolbar = findViewById(R.id.appToolbar);
        ToolbarUtil.setupToolbar(this, toolbar, "", false);
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        Intent intent = getIntent();
        guessList = intent.getParcelableArrayListExtra("guessList");
        player = intent.getParcelableExtra("player");

        currentScoreSet = new HashSet<>();

        if (guessList != null) {
            for (Guess guess : guessList) {
                currentScoreSet.add(formatSetEntry(player.getName(), guess.getInputedAttemp(), player.getScore()));
                addGuessToTable(guess, tableLayout, guessList.indexOf(guess) + 1);
            }
        }
    }

    private void addGuessToTable(Guess guess, TableLayout tableLayout, int attemptNumber) {
        TableRow tableRow = new TableRow(this);

        TextView tvAttemptNumber = new TextView(this);
        tvAttemptNumber.setTextSize(24);
        tvAttemptNumber.setText("  " + String.format("%d°", attemptNumber));
        tvAttemptNumber.setTextColor(0xFFD3D3D3); // Cinza claro
        tableRow.addView(tvAttemptNumber);

        TextView tvGuess = new TextView(this);
        tvGuess.setTextSize(24);
        tvGuess.setText("  " + Integer.toString(guess.getInputedAttemp()));
        tvGuess.setTextColor(0xFFD3D3D3); // Cinza claro
        tableRow.addView(tvGuess);

        TextView tvAttempt = new TextView(this);
        tvAttempt.setTextSize(24);
        tvAttempt.setText("  " + Integer.toString(guess.getCorrectValue()));
        tvAttempt.setTextColor(0xFFD3D3D3); // Cinza claro
        tableRow.addView(tvAttempt);

        ImageView ivResult = new ImageView(this);
        if (guess.isResultCorrect()) {
            ivResult.setImageResource(R.drawable.correto);
        } else {
            ivResult.setImageResource(R.drawable.incorreto);
        }

        TableRow.LayoutParams params = new TableRow.LayoutParams(44, 44);
        ivResult.setLayoutParams(params);

        tableRow.addView(ivResult);
        tableLayout.addView(tableRow);
    }

    private String formatSetEntry(String name, int guess, int score) {
        return "\n" + name + " | " + guess + " | " + score + " pontos \n";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void showNewGameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja iniciar um novo jogo?")
                .setPositiveButton("Sim", (dialog, id) -> newGameWithSamePlayerDialog())
                .setNegativeButton("Não", (dialog, id) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void startNewGame(boolean continueWithSamePlayer) {
        Intent intent = new Intent(ReportActivity.this, NewGameActivity.class);
        if (continueWithSamePlayer) {
            intent.putExtra("player", player);
        }
        startActivity(intent);
    }

    private void newGameWithSamePlayerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Continuar com o jogador logado?")
                .setPositiveButton("Sim", (dialog, id) -> startNewGame(true))
                .setNegativeButton("Não", (dialog, id) -> startNewGame(false));

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void callScoreDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(currentScoreSet.toString())
                .setPositiveButton("Fechar", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void callAnimalPlayerDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Seu animal no zodíaco chinês é: " + ChineseZodiacCalculatorUtil.getZodiacAnimal(this, player.getBirthDate()))
                .setPositiveButton("Fechar", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.score){
            callScoreDialog();
            return true;
        }

        if(item.getItemId() == R.id.your_animal){
            callAnimalPlayerDialog();
            return true;
        }

        if (item.getItemId() == R.id.new_game) {
            showNewGameDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);    }
}