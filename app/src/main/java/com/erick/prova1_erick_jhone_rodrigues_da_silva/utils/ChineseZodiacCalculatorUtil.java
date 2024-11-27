package com.erick.prova1_erick_jhone_rodrigues_da_silva.utils;

import android.content.Context;

import com.erick.prova1_erick_jhone_rodrigues_da_silva.R;

public class ChineseZodiacCalculatorUtil {

    private Context context;

    public ChineseZodiacCalculatorUtil() {

    }

    public static String getZodiacAnimal(Context context, String date) {
        String yearString = date.split("/")[2];
        int year = Integer.parseInt(yearString);

        int animalIndex = (year - 4) % 12;

        String[] animalNames = context.getResources().getStringArray(R.array.animal_names);

        return animalNames[animalIndex];
    }
}