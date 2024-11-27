package com.erick.prova1_erick_jhone_rodrigues_da_silva.data;

import android.content.Context;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.R;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.ItemList;

import java.util.ArrayList;

public class ItemListRepository {

    public static ArrayList<ItemList> getMockedItemList(Context context) {
        ArrayList<ItemList> itemList = new ArrayList<>();

        String[] animalNames = context.getResources().getStringArray(R.array.animal_names);
        String[] animalAttributes = context.getResources().getStringArray(R.array.animal_attributes);

        int[] images = {R.drawable.rato, R.drawable.bufalo, R.drawable.tigre, R.drawable.coelho,
                R.drawable.dragao, R.drawable.serpente, R.drawable.cavalo, R.drawable.cabra,
                R.drawable.macaco, R.drawable.galo, R.drawable.cao, R.drawable.javali};

        for (int i = 0; i < animalNames.length; i++) {
            itemList.add(new ItemList(animalNames[i], animalAttributes[i], images[i]));
        }

        return itemList;
    }
}