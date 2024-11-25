package com.erick.prova1_erick_jhone_rodrigues_da_silva.view.activities;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.erick.prova1_erick_jhone_rodrigues_da_silva.R;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.utils.ToolbarUtil;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Toolbar toolbar = findViewById(R.id.appToolbar);
        ToolbarUtil.setupToolbar(this, toolbar, "", false);
        TableLayout tableLayout = findViewById(R.id.tableLayout);


        int guessValue = getIntent().getIntExtra("guessValue", 0);
        int attemptValue = getIntent().getIntExtra("attemptValue", 0);
        boolean isCorrect = getIntent().getBooleanExtra("isCorrect", false);

        TableRow tableRow = new TableRow(this);

        TextView tvGuess = new TextView(this);
        tvGuess.setTextSize(24);
        tvGuess.setText(Integer.toString(guessValue));
        tableRow.addView(tvGuess);

        TextView tvAttempt = new TextView(this);
        tvAttempt.setTextSize(24);
        tvAttempt.setText(Integer.toString(attemptValue));
        tableRow.addView(tvAttempt);

        ImageView ivResult = new ImageView(this);
        if (isCorrect) {
            ivResult.setImageResource(R.drawable.correto);
        } else {
            ivResult.setImageResource(R.drawable.incorreto);
        }

        TableRow.LayoutParams params = new TableRow.LayoutParams(
                44,
                44);
        ivResult.setLayoutParams(params);

        tableRow.addView(ivResult);

        tableLayout.addView(tableRow);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

}