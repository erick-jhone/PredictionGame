package com.erick.prova1_erick_jhone_rodrigues_da_silva.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.ItemList;

public class MyDialog extends DialogFragment {

    public ItemList itemList;
    public MyDialog(ItemList itemList){
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setTitle("Descrição")
                .setMessage("Nome: " + itemList.getName() + "\nPalavra associada: " + itemList.getAssociateWord())
                .setCancelable(true);

        AlertDialog dialog = builder.create();
        return dialog;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Toast.makeText(getActivity().getApplicationContext(), "Fechando o AlertDialog!", Toast.LENGTH_SHORT).show();
    }


}
