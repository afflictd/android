package ru.mirea.puzin.toastapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ToastApp";

    // Данные студента
    private static final String STUDENT_NAME = "14";
    private static final String GROUP_NUMBER = "БСБО-51-24";

    private EditText editTextInput;
    private TextView counterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация компонентов
        editTextInput = findViewById(R.id.editTextInput);
        counterTextView = findViewById(R.id.counterTextView);

        Log.d(TAG, "MainActivity: onCreate() вызван");

        // Добавляем слушатель для实时 подсчета символов
        editTextInput.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Не используется
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Обновляем счетчик символов в реальном времени
                int length = s.length();
                counterTextView.setText("Символов: " + length);
                Log.d(TAG, "Текст изменен, символов: " + length);
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {
                // Не используется
            }
        });

        // Показываем приветственное сообщение
        Toast.makeText(this, "Приложение ToastApp запущено", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity: onStart() вызван");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity: onResume() вызван");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity: onPause() вызван");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity: onStop() вызван");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "MainActivity: onRestart() вызван");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity: onDestroy() вызван");
    }

    /**
     * Обработчик нажатия на кнопку подсчета символов
     */
    public void onCountClick(View view) {
        Log.d(TAG, "onCountClick() вызван");

        // Получаем текст из EditText
        String inputText = editTextInput.getText().toString();

        // Подсчитываем количество символов
        int charCount = inputText.length();

        Log.d(TAG, "Введенный текст: \"" + inputText + "\"");
        Log.d(TAG, "Количество символов: " + charCount);

        // Формируем сообщение для Toast
        String toastMessage = "СТУДЕНТ " + STUDENT_NAME +
                " ГРУППА " + GROUP_NUMBER +
                " Количество символов - " + charCount;

        // Отображаем Toast сообщение
        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();

        Log.d(TAG, "Отображено Toast сообщение: " + toastMessage);

        // Дополнительно: если текст пустой, показываем предупреждение
        if (charCount == 0) {
            Toast.makeText(this, "Внимание: поле ввода пустое!", Toast.LENGTH_SHORT).show();
        }
    }
}