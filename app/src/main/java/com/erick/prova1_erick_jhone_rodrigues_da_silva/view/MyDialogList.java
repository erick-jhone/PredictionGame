package com.erick.prova1_erick_jhone_rodrigues_da_silva.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.erick.prova1_erick_jhone_rodrigues_da_silva.R;

import java.util.ArrayList;

public class MyDialogList extends DialogFragment {

    private ArrayList<String> animals;
    private OnDialogConfirmListener listener;

    // Interface for callback
    public interface OnDialogConfirmListener {
        void onConfirm();
    }

    // Constructor to receive the list and listener
    public MyDialogList(ArrayList<String> animals, OnDialogConfirmListener listener) {
        this.animals = animals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Convert ArrayList to array
        String[] animalsArray = animals.toArray(new String[0]);

        return builder.setTitle("Esses foram os animais sorteados")
                .setItems(animalsArray, null) // Display list of items, null because no item click action required
                .setPositiveButton("Jogar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onConfirm(); // Trigger the callback on confirm button press
                        }
                    }
                })
                .create();
    }
}