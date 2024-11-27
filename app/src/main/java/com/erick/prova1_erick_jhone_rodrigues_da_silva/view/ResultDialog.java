package com.erick.prova1_erick_jhone_rodrigues_da_silva.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.erick.prova1_erick_jhone_rodrigues_da_silva.R;

public class ResultDialog extends DialogFragment {

    public interface onConfirm {
        void onConfirmDelete();
    }

    private onConfirm listener;

    private boolean isCorrect;

    public ResultDialog (boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public void setConfirmDeleteListener(onConfirm listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.result_dialog, null);

        Button buttonConfirm = view.findViewById(R.id.buttonConfirm);
        TextView textViewTitle = view.findViewById(R.id.textViewTitle);
        ImageView imageResult = view.findViewById(R.id.imageResult);

        if (isCorrect) {
            textViewTitle.setText("Parabéns, você acertou!");
            imageResult.setImageResource(R.drawable.trofeu);
        } else {
            textViewTitle.setText("Você excedeu as tentativas!");
            imageResult.setImageResource(R.drawable.game_over);
        }

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onConfirmDelete();
                }
                dismiss();
            }
        });

        builder.setView(view);

        return builder.create();
    }
}
