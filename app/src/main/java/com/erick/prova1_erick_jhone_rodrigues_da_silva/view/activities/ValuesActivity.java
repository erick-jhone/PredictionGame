package com.erick.prova1_erick_jhone_rodrigues_da_silva.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.erick.prova1_erick_jhone_rodrigues_da_silva.R;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.data.ItemListRepository;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.ItemList;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.utils.CalculatorValueUtil;

import java.util.ArrayList;
import java.util.List;

public class ValuesActivity extends AppCompatActivity {

    private TextView textViewNameValue, textViewWordValue, textViewTotalValue;
    private ImageView imageView;
    private Button buttonSkip;
    private ArrayList<ItemList> items;
    private ItemListRepository itemListRepository;
    private int itemIndice = 0;
    private CalculatorValueUtil calculatorValueUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_values);
        calculatorValueUtil = new CalculatorValueUtil();
        getItems();
        initUIComponents();
        setValues(0);

        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemIndice < items.size() - 1){
                    itemIndice++;
                    setValues(itemIndice);
                } else {
                    buttonSkip.setText("Iniciar jogo");

                }

                if(buttonSkip.getText().equals("Iniciar jogo")){
                    Intent intent = new Intent(ValuesActivity.this, NewGameActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initUIComponents() {
        textViewNameValue = findViewById(R.id.textViewValueName);
        textViewWordValue = findViewById(R.id.textViewValueWord);
        textViewTotalValue = findViewById(R.id.textViewTotalValue);
        imageView = findViewById(R.id.imageView);
        buttonSkip = findViewById(R.id.buttonSkip);
    }

    private void getItems() {
        setupRepository();
        items = itemListRepository.getMockedItemList(this);
    }

    private void setupRepository() {
        itemListRepository = new ItemListRepository();
    }

    private void setValues(int itemIndice) {
        imageView.setImageResource(items.get(itemIndice).getImage());
        textViewNameValue.setText("Valor nome: " + calculatorValueUtil.calculateVogais(items.get(itemIndice).getName()));
        textViewWordValue.setText("Valor palavra: " + calculatorValueUtil.calculateConsoantes(items.get(itemIndice).getAssociateWord()));
        textViewTotalValue.setText("Total: " + (calculatorValueUtil.calculateVogais(items.get(itemIndice).getName()) + calculatorValueUtil.calculateConsoantes(items.get(itemIndice).getAssociateWord())));
    }

}