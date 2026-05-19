package ru.mirea.puzinva.mireaproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NewEntryDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        View view = getLayoutInflater().inflate(R.layout.dialog_new_entry, null);

        EditText etTitle = view.findViewById(R.id.etTitle);
        EditText etContent = view.findViewById(R.id.etContent);
        Button btnSave = view.findViewById(R.id.btnSave);

        builder.setView(view)
                .setTitle("Новая запись");

        AlertDialog dialog = builder.create();

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Запись '" + title + "' сохранена", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        return dialog;
    }
}