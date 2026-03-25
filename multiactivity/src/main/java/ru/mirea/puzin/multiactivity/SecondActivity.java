package ru.mirea.puzin.multiactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "Lifecycle_Second";
    private TextView receivedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Инициализация TextView
        receivedTextView = findViewById(R.id.receivedTextView);

        Log.d(TAG, "SecondActivity: onCreate() вызван");

        // Получаем переданные данные из Intent (весь существующий функционал)
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            // Получаем данные по ключу
            String studentData = extras.getString(MainActivity.EXTRA_STUDENT_DATA);

            // Получаем дополнительные данные
            long timestamp = extras.getLong("timestamp", 0);
            String source = extras.getString("source", "неизвестно");

            // Отображаем полученные данные
            if (studentData != null && !studentData.isEmpty()) {
                receivedTextView.setText(studentData);

                // Логируем полученные данные
                Log.d(TAG, "SecondActivity: Получены данные - " + studentData);
                Log.d(TAG, "SecondActivity: Временная метка - " + timestamp);
                Log.d(TAG, "SecondActivity: Источник - " + source);

                // Устанавливаем заголовок активности
                setTitle("Полученные данные");
            } else {
                receivedTextView.setText("Данные не были получены");
                Log.w(TAG, "SecondActivity: Данные не получены или пустые");
            }
        } else {
            receivedTextView.setText("Ошибка: данные не переданы");
            Log.e(TAG, "SecondActivity: Bundle равен null");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "SecondActivity: onStart() вызван");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "SecondActivity: onResume() вызван");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "SecondActivity: onPause() вызван");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "SecondActivity: onStop() вызван");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "SecondActivity: onRestart() вызван");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "SecondActivity: onDestroy() вызван");
    }
}