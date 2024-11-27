package com.erick.prova1_erick_jhone_rodrigues_da_silva.utils.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class NavigationUtils {


    public static void navigate(Context context, Class<?> destination) {
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
    }

    public static void navigate(Context context, Class<?> destination, String key, String value) {
        Intent intent = new Intent(context, destination);
        intent.putExtra(key, value);
        context.startActivity(intent);
    }

    public static void navigateAndPop(Context context, Class<?> destination) {
        Intent intent = new Intent(context, destination);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    public static void navigateAndPop(Context context, Class<?> destination, String key, String value) {
        Intent intent = new Intent(context, destination);
        intent.putExtra(key, value);
        context.startActivity(intent);
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    public static void navigateReorderToFront(Context context, Class<?> destination) {
        Intent intent = new Intent(context, destination);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }
}


