package com.erick.prova1_erick_jhone_rodrigues_da_silva.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.erick.prova1_erick_jhone_rodrigues_da_silva.R;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.data.ItemListRepository;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.ItemList;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.Player;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.utils.CalculatorValueUtil;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.view.MyDialogList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewGameActivity extends AppCompatActivity implements MyDialogList.OnDialogConfirmListener {

    private Button buttonConfirmQuantityItemsDrawn;
    private EditText editTextQuantityItemsDrawn, editTextBirthDate, editTextNamePlayer;
    private CalculatorValueUtil calculateValueUtil = new CalculatorValueUtil();
    private int totalAnimalValue = 0;
    private ArrayList<ItemList> animals = new ArrayList<>();
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_game);

        initUIComponents();

        Intent intent = getIntent();
        player = intent.getParcelableExtra("player");

        if (player != null) {
            setTitle("New Game for " + player.getName());
            editTextNamePlayer.setVisibility(View.GONE);
            editTextBirthDate.setVisibility(View.GONE);
        }
    }

    private void initUIComponents() {
        buttonConfirmQuantityItemsDrawn = findViewById(R.id.buttonConfirmQuantityItemsDrawn);
        editTextQuantityItemsDrawn = findViewById(R.id.editTextQuantityItemsDrawn);
        editTextBirthDate = findViewById(R.id.editTextBirthDate);
        editTextNamePlayer = findViewById(R.id.editTextNamePlayer);

        buttonConfirmQuantityItemsDrawn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawAnimals(editTextQuantityItemsDrawn.getText().toString());
            }
        });
    }

    public void drawAnimals(String quantity) {
        animals.clear();
        totalAnimalValue = 0;

        int quantityAnimalsForDraw = Integer.parseInt(quantity);
        Random random = new Random();
        List<ItemList> allItems = ItemListRepository.getMockedItemList(this);

        while (animals.size() < quantityAnimalsForDraw) {
            int randomIndex = random.nextInt(allItems.size());
            ItemList randomAnimal = allItems.get(randomIndex);
            if (!animals.contains(randomAnimal)) {
                animals.add(randomAnimal);
            }
        }

        ArrayList<String> animalNames = new ArrayList<>();
        for (ItemList animal : animals) {
            animalNames.add(animal.getName());
            totalAnimalValue += calculateValueUtil.calculateVogais(animal.getName()) +
                    calculateValueUtil.calculateConsoantes(animal.getAssociateWord());
        }

        MyDialogList dialog = new MyDialogList(animalNames, this);
        dialog.show(getSupportFragmentManager(), "AnimalDialog");
    }

    @Override
    public void onConfirm() {
        if (player == null) {
            player = new Player(
                    editTextNamePlayer.getText().toString(),
                    editTextBirthDate.getText().toString(),
                    0
            );
        } else {
            player = new Player(player.getName(), player.getBirthDate(), player.getScore());
        }

        Intent intent = new Intent(this, NumberPredictionActivity.class);
        intent.putExtra("player", player);
        intent.putExtra("totalAnimalValue", totalAnimalValue);

        startActivity(intent);
    }
}