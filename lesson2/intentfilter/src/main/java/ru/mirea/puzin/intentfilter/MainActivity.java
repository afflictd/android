package ru.mirea.puzin.intentfilter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "IntentFilterDemo";

    // Данные студента
    private static final String STUDENT_NAME = "ПУЗИН ВЯЧЕСЛАВ АЛЕКСЕЕВИЧ";
    private static final String UNIVERSITY = "МИРЭА - Российский технологический университет";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "MainActivity: onCreate() вызван");
        Toast.makeText(this, "Приложение IntentFilter запущено", Toast.LENGTH_SHORT).show();
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
     * Обработчик нажатия для открытия веб-браузера
     */
    public void onOpenBrowserClick(View view) {
        Log.d(TAG, "onOpenBrowserClick() вызван");

        // Способ 1: Использование ACTION_VIEW с URI
        try {
            Uri address = Uri.parse("https://www.mirea.ru/");
            Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);

            // Добавляем флаги для принудительного открытия в браузере
            openLinkIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(openLinkIntent);
            Log.d(TAG, "Открыт браузер с сайтом: https://www.mirea.ru/");
            Toast.makeText(this, "Открытие веб-браузера...", Toast.LENGTH_SHORT).show();

        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "ActivityNotFoundException: " + e.getMessage());

            // Способ 2: Попытка открыть через специальный Intent для браузера
            try {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mirea.ru/"));
                browserIntent.setPackage("com.android.chrome"); // Попытка открыть именно в Chrome
                startActivity(browserIntent);
                Log.d(TAG, "Открыт Chrome через setPackage");

            } catch (ActivityNotFoundException e2) {
                Log.e(TAG, "Chrome не найден, пробуем другие браузеры");

                // Способ 3: Открыть через системный chooser
                try {
                    Intent chooserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mirea.ru/"));
                    startActivity(Intent.createChooser(chooserIntent, "Выберите браузер"));
                    Log.d(TAG, "Открыт системный chooser для выбора браузера");

                } catch (Exception e3) {
                    Log.e(TAG, "Ошибка при открытии ссылки: " + e3.getMessage());
                    Toast.makeText(this, "Не найдено приложение для открытия ссылки.\nПожалуйста, установите браузер", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Общая ошибка: " + e.getMessage());
            Toast.makeText(this, "Ошибка при открытии браузера: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Обработчик нажатия для передачи данных в другое приложение
     */
    public void onShareClick(View view) {
        Log.d(TAG, "onShareClick() вызван");

        try {
            // Создаем неявный Intent для отправки текста
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");

            // Добавляем данные для отправки
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, UNIVERSITY);

            // Формируем текст с ФИО студента и университетом
            String shareText = "Студент: " + STUDENT_NAME + "\n" +
                    "Университет: " + UNIVERSITY + "\n";

            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);

            // Создаем диалог выбора приложения для отправки
            Intent chooser = Intent.createChooser(shareIntent, "МОИ ФИО");

            startActivity(chooser);
            Log.d(TAG, "Открыт диалог выбора приложения для отправки данных");
            Log.d(TAG, "Отправляемые данные: " + shareText);
            Toast.makeText(this, "Выберите приложение для отправки", Toast.LENGTH_SHORT).show();

        } catch (ActivityNotFoundException e) {
            Log.e(TAG, "ActivityNotFoundException при отправке: " + e.getMessage());
            Toast.makeText(this, "Не найдено приложение для отправки текста", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.e(TAG, "Ошибка при отправке данных: " + e.getMessage());
            Toast.makeText(this, "Ошибка при отправке данных", Toast.LENGTH_SHORT).show();
        }
    }
}