package ru.mirea.puzin.multiactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Lifecycle_Main";
    private EditText editText;
    public static final String EXTRA_STUDENT_DATA = "com.example.multiactivity.STUDENT_DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация компонентов (весь существующий функционал)
        editText = findViewById(R.id.editText);

        Log.d(TAG, "MainActivity: onCreate() вызван");

        // Показываем подсказку при запуске
        Toast.makeText(this, "Введите ваши данные и нажмите Отправить", Toast.LENGTH_LONG).show();
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

    // Обработчик нажатия на кнопку (весь существующий функционал)
    public void onClickNewActivity(View view) {
        Log.d(TAG, "MainActivity: onClickNewActivity() вызван");

        // Получаем текст из EditText
        String studentData = editText.getText().toString().trim();

        // Проверяем, введены ли данные
        if (studentData.isEmpty()) {
            // Если поле пустое, показываем предупреждение
            Toast.makeText(this, "Пожалуйста, введите ваши данные", Toast.LENGTH_SHORT).show();
            editText.setError("Это поле обязательно для заполнения");
            return;
        }

        // Форматируем данные с префиксом MIREA
        String formattedData = "MIREA - " + studentData;

        // Создаем Intent для запуска SecondActivity
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        // Передаем данные через Bundle
        intent.putExtra(EXTRA_STUDENT_DATA, formattedData);

        // Добавляем дополнительные данные для примера
        intent.putExtra("timestamp", System.currentTimeMillis());
        intent.putExtra("source", "MainActivity");

        // Запускаем вторую активность
        startActivity(intent);

        Log.d(TAG, "MainActivity: Отправлены данные - " + formattedData);
    }
}