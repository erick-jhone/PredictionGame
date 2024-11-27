package com.erick.prova1_erick_jhone_rodrigues_da_silva.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.erick.prova1_erick_jhone_rodrigues_da_silva.R;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.utils.navigation.NavigationUtils;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.view.adapter.SignItemAdapter;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.data.SignRepository;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.Sign;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.view.MyDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements   AdapterView.OnItemClickListener {

    private ListView listViewOptions;
    private Button buttonSkip;
    private SignItemAdapter adapter;
    private ArrayList<Sign> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initUIComponents();
    }

    private void initUIComponents() {
        buttonSkip = findViewById(R.id.button);
        setupListeners();

        listViewOptions = findViewById(R.id.listViewOptions);
        makeAdapter();
        listViewOptions.setAdapter(adapter);
    }

    private void setupListeners() {
        buttonSkip.setOnClickListener(view -> NavigationUtils.navigate(MainActivity.this, ValuesActivity.class));
    }

    private void makeAdapter() {
        if(items == null){
            items = SignRepository.getMockedsSignItemList(this);
        }
        adapter = new SignItemAdapter(this, items);
        listViewOptions.setAdapter(adapter);
        listViewOptions.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Sign item = (Sign) adapterView.getItemAtPosition(i);
        openDialog(item);
    }

    public void openDialog(Sign item){
        MyDialog dialog = new MyDialog(item, this);
        dialog.show(getSupportFragmentManager(), "MyDialog");
    }
}