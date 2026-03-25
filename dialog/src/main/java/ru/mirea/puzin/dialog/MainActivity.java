package ru.mirea.puzin.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity
        implements MyTimeDialogFragment.TimePickerListener,
        MyDateDialogFragment.DatePickerListener {

    private TextView resultTextView;
    private static final String TAG = "DialogApp";

    private static final String STUDENT_NAME = "ПУЗИН ВЯЧЕСЛАВ АЛЕКСЕЕВИЧ";
    private static final String GROUP_NUMBER = "БСБО-51-24";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickShowDialog(View view) {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }
    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }
    public void showTimePickerDialog(View view) {
        MyTimeDialogFragment timeDialog = new MyTimeDialogFragment();
        timeDialog.show(getSupportFragmentManager(), "timePicker");
    }

    /**
     * Показывает DatePickerDialog
     */
    public void showDatePickerDialog(View view) {
        MyDateDialogFragment dateDialog = new MyDateDialogFragment();
        dateDialog.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     * Показывает ProgressDialog
     */
    public void showProgressDialog(View view) {
        MyProgressDialogFragment progressDialog = new MyProgressDialogFragment();
        progressDialog.show(getSupportFragmentManager(), "progressDialog");
    }

    /**
     * Показывает Snackbar
     */
    public void showSnackbar(View view) {
        Snackbar snackbar = Snackbar.make(view,
                        "Студент: " + STUDENT_NAME + ", Группа: " + GROUP_NUMBER,
                        Snackbar.LENGTH_LONG)
                .setAction("ОК", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Вы нажали ОК", Toast.LENGTH_SHORT).show();
                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_dark));

        snackbar.show();
    }

    /**
     * Приветственный Snackbar при запуске
     */
    private void showWelcomeSnackbar() {
        View view = findViewById(android.R.id.content);
        Snackbar.make(view,
                        "Добро пожаловать! Выберите диалоговое окно для демонстрации",
                        Snackbar.LENGTH_LONG)
                .show();
    }

    /**
     * Реализация метода интерфейса TimePickerListener
     */
    @Override
    public void onTimeSet(int hour, int minute) {
        String timeString = String.format("Выбранное время: %02d:%02d", hour, minute);
        resultTextView.setText(timeString);

        // Показываем Snackbar с результатом
        View view = findViewById(android.R.id.content);
        Snackbar.make(view,
                        "Студент " + STUDENT_NAME + " выбрал время: " + timeString,
                        Snackbar.LENGTH_LONG)
                .show();

        Toast.makeText(this, "Время установлено: " + timeString, Toast.LENGTH_SHORT).show();
    }

    /**
     * Реализация метода интерфейса DatePickerListener
     */
    @Override
    public void onDateSet(int year, int month, int day) {
        // Месяцы в DatePicker от 0 до 11, поэтому добавляем 1
        String dateString = String.format("Выбранная дата: %02d.%02d.%d", day, month + 1, year);
        resultTextView.setText(dateString);

        // Показываем Snackbar с результатом
        View view = findViewById(android.R.id.content);
        Snackbar.make(view,
                        "Студент " + STUDENT_NAME + " выбрал дату: " + dateString,
                        Snackbar.LENGTH_LONG)
                .show();

        Toast.makeText(this, "Дата установлена: " + dateString, Toast.LENGTH_SHORT).show();
    }
}