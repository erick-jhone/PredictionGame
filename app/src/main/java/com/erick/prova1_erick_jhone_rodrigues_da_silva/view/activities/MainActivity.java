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
import com.erick.prova1_erick_jhone_rodrigues_da_silva.view.adapter.ItemListAdapter;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.data.ItemListRepository;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.ItemList;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.view.MyDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements   AdapterView.OnItemClickListener {

    private ListView listViewOptions;
    private Button buttonSkip;
    private ItemListAdapter adapter;
    private ArrayList<ItemList> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        buttonSkip = findViewById(R.id.button);
        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ValuesActivity.class));
            }
        });

        listViewOptions = findViewById(R.id.listViewOptions);
        makeAdapter();
        listViewOptions.setAdapter(adapter);
    }

    private void makeAdapter() {
        if(items == null){
            items = ItemListRepository.getMockedItemList(this);
        }
        adapter = new ItemListAdapter(this, items);
        listViewOptions.setAdapter(adapter);
        listViewOptions.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ItemList item = (ItemList) adapterView.getItemAtPosition(i);
        openDialog(item);
    }

    public void openDialog(ItemList item){
        MyDialog dialog = new MyDialog(item);
        dialog.show(getSupportFragmentManager(), "MyDialog");
    }



}