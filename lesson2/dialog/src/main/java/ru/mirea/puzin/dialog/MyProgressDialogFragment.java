package ru.mirea.puzin.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;

public class MyProgressDialogFragment extends DialogFragment {

    private ProgressDialog progressDialog;
    private Handler handler;
    private Runnable runnable;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Создаем ProgressDialog
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Загрузка данных");
        progressDialog.setMessage("Пожалуйста, подождите...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        return progressDialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Имитируем процесс загрузки
        handler = new Handler();
        runnable = new Runnable() {
            int progress = 0;

            @Override
            public void run() {
                if (progress <= 100) {
                    progressDialog.setProgress(progress);
                    progress += 10;
                    handler.postDelayed(this, 500);
                } else {
                    // Завершаем загрузку
                    dismiss();
                    Toast.makeText(getActivity(), "Загрузка завершена!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        handler.post(runnable);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Останавливаем обновление прогресса
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }
}