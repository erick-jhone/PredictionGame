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

    public interface OnDialogConfirmListener {
        void onConfirm();
    }

    public MyDialogList(ArrayList<String> animals, OnDialogConfirmListener listener) {
        this.animals = animals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] animalsArray = animals.toArray(new String[0]);

        return builder.setTitle(getString(R.string.esses_foram_os_animais_sorteados))
                .setItems(animalsArray, null)
                .setPositiveButton(R.string.jogar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onConfirm();
                        }
                    }
                })
                .create();
    }
}