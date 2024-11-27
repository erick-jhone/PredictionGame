package com.erick.prova1_erick_jhone_rodrigues_da_silva.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.erick.prova1_erick_jhone_rodrigues_da_silva.R;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.data.SignRepository;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.Sign;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.Player;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.utils.CalculatorValueWordUtil;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.utils.navigation.NavigationKeys;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.view.MyDialogList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewGameActivity extends AppCompatActivity implements MyDialogList.OnDialogConfirmListener {

    private Button buttonConfirmQuantityItemsDrawn;
    private EditText editTextQuantityItemsDrawn, editTextBirthDate, editTextNamePlayer;
    private CalculatorValueWordUtil calculateValueUtil = new CalculatorValueWordUtil();
    private int totalAnimalValue = 0;
    private ArrayList<Sign> animals = new ArrayList<>();
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_game);

        initUIComponents();
        getExtras();
        userLoggedInVerify();
    }

    private void initUIComponents() {
        buttonConfirmQuantityItemsDrawn = findViewById(R.id.buttonConfirmQuantityItemsDrawn);
        editTextQuantityItemsDrawn = findViewById(R.id.editTextQuantityItemsDrawn);
        editTextBirthDate = findViewById(R.id.editTextBirthDate);
        editTextNamePlayer = findViewById(R.id.editTextNamePlayer);
        setupListeners();
    }

    private void setupListeners() {
        buttonConfirmQuantityItemsDrawn.setOnClickListener(view -> drawAnimals(editTextQuantityItemsDrawn.getText().toString()));
    }

    private void userLoggedInVerify() {
        if (player != null) {
            editTextNamePlayer.setVisibility(View.GONE);
            editTextBirthDate.setVisibility(View.GONE);
        }
    }

    private void getExtras() {
        Intent intent = getIntent();
        player = intent.getParcelableExtra(NavigationKeys.PLAYER);
    }

    public void drawAnimals(String quantity) {
        animals.clear();
        totalAnimalValue = 0;

        int quantityAnimalsForDraw = Integer.parseInt(quantity);
        Random random = new Random();
        List<Sign> allItems = SignRepository.getMockedsSignItemList(this);

        while (animals.size() < quantityAnimalsForDraw) {
            int randomIndex = random.nextInt(allItems.size());
            Sign randomAnimal = allItems.get(randomIndex);
            if (!animals.contains(randomAnimal)) {
                animals.add(randomAnimal);
            }
        }

        ArrayList<String> animalNames = new ArrayList<>();
        for (Sign animal : animals) {
            animalNames.add(animal.getName());
            totalAnimalValue += calculateValueUtil.calculateVoweis(animal.getName()) +
                    calculateValueUtil.calculateConsonants(animal.getAssociateWord());
        }

        showDrawedAnimalsDialog(animalNames);
    }

    private void showDrawedAnimalsDialog(ArrayList<String> animalNames) {
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
        }
        navigateToPredictionScreen();
    }

    private void navigateToPredictionScreen() {
        Intent intent = new Intent(this, NumberPredictionActivity.class);
        intent.putExtra(NavigationKeys.PLAYER, player);
        intent.putExtra(NavigationKeys.TOTAL_ANIMAL_VALUE, totalAnimalValue);
        startActivity(intent);
    }
}