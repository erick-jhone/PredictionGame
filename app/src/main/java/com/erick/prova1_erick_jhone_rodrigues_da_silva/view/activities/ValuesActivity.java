package com.erick.prova1_erick_jhone_rodrigues_da_silva.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.erick.prova1_erick_jhone_rodrigues_da_silva.R;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.data.SignRepository;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.Sign;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.utils.CalculatorValueWordUtil;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.utils.navigation.NavigationKeys;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.utils.navigation.NavigationUtils;

import java.util.ArrayList;

public class ValuesActivity extends AppCompatActivity {

    private TextView textViewNameValue, textViewWordValue, textViewTotalValue;
    private ImageView imageView;
    private Button buttonSkip;
    private ArrayList<Sign> items;
    private SignRepository signRepository;
    private int itemIndice = 0;
    private CalculatorValueWordUtil calculatorValueWordUtil = new CalculatorValueWordUtil();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_values);
        getItems();
        initUIComponents();
        setSignValues(0);
        setupListeners();
    }

    private void initUIComponents() {
        textViewNameValue = findViewById(R.id.textViewValueName);
        textViewWordValue = findViewById(R.id.textViewValueWord);
        textViewTotalValue = findViewById(R.id.textViewTotalValue);
        imageView = findViewById(R.id.imageView);
        buttonSkip = findViewById(R.id.buttonSkip);
    }

    private void setupListeners() {
        buttonSkip.setOnClickListener(view -> {

            changeSignCurrentViewed();
            if (buttonSkip.getText().equals(getString(R.string.iniciar_jogo))) {
                NavigationUtils.navigate(ValuesActivity.this, NewGameActivity.class);
            }

        });
    }

    private void changeSignCurrentViewed() {
        if (itemIndice < items.size() - 1) {
            itemIndice++;
            setSignValues(itemIndice);
        } else {
            buttonSkip.setText(getString(R.string.iniciar_jogo));
        }
    }


    private void getItems() {
        setupRepository();
        items = signRepository.getMockedsSignItemList(this);
    }

    private void setupRepository() {
        signRepository = new SignRepository();
    }

    private void setSignValues(int itemIndice) {
        imageView.setImageResource(items.get(itemIndice).getImage());
        textViewNameValue.setText(getString(R.string.valor_nome) + calculatorValueWordUtil.calculateVoweis(items.get(itemIndice).getName()));
        textViewWordValue.setText(getString(R.string.valor_palavra) + calculatorValueWordUtil.calculateConsonants(items.get(itemIndice).getAssociateWord()));
        textViewTotalValue.setText(getString(R.string.total) + (calculatorValueWordUtil.calculateVoweis(items.get(itemIndice).getName()) + calculatorValueWordUtil.calculateConsonants(items.get(itemIndice).getAssociateWord())));
    }
}