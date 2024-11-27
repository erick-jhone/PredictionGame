package com.erick.prova1_erick_jhone_rodrigues_da_silva.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.erick.prova1_erick_jhone_rodrigues_da_silva.R;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.Sign;

public class MyDialog extends DialogFragment {

    public Sign sign;
    private Context context;
    public MyDialog(Sign sign, Context context){
        this.sign = sign;
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setTitle(context.getString(R.string.descricao))
                .setMessage(context.getString(R.string.nome) + sign.getName() + "\n" + getString(R.string.palavra_associada)+": " + sign.getAssociateWord())
                .setCancelable(true);

        AlertDialog dialog = builder.create();
        return dialog;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}
