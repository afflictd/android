package ru.mirea.puzin.notificationapp;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "NotificationApp";
    private static final String CHANNEL_ID = "com.mirea.asd.notification.ANDROID";
    private static final String CHANNEL_NAME = "Student FIO Notification";
    private static final int PERMISSION_CODE = 200;
    private static final int NOTIFICATION_ID = 1;

    // Данные студента
    private static final String STUDENT_NAME = "ПУЗИН ВЯЧЕСЛАВ АЛЕКСЕЕВИЧ";
    private static final String GROUP_NUMBER = "БСБО-51-24";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate() вызван");

        // Проверка разрешения для Android 13+
        checkNotificationPermission();

        // Создание канала с ВЫСОКОЙ важностью для всплывающих уведомлений
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }
    }

    private void checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Разрешения получены");
            } else {
                Log.d(TAG, "Нет разрешений! Запрашиваем...");
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        PERMISSION_CODE
                );
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Удаляем старый канал, если есть, чтобы создать новый с правильными настройками
        if (notificationManager.getNotificationChannel(CHANNEL_ID) != null) {
            notificationManager.deleteNotificationChannel(CHANNEL_ID);
            Log.d(TAG, "Старый канал удален");
        }

        // Критически важно: IMPORTANCE_HIGH для всплывающих уведомлений
        int importance = NotificationManager.IMPORTANCE_HIGH;

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
        channel.setDescription("Канал для уведомлений студента " + STUDENT_NAME);

        // Включаем всплывающие уведомления
        channel.enableLights(true);
        channel.setLightColor(android.graphics.Color.RED);
        channel.enableVibration(true);
        channel.setVibrationPattern(new long[]{0, 500, 200, 500});

        // Разрешаем показ уведомлений на экране (heads-up notification)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            channel.setAllowBubbles(true);
        }

        notificationManager.createNotificationChannel(channel);
        Log.d(TAG, "Канал уведомлений создан с важностью HIGH");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Разрешение на уведомления получено");
                // После получения разрешения создаем канал заново
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    createNotificationChannel();
                }
            } else {
                Log.w(TAG, "Разрешение на уведомления отклонено");
            }
        }
    }

    public void onClickSendNotification(View view) {
        Log.d(TAG, "onClickSendNotification() вызван");

        // Проверяем разрешение для Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                Log.w(TAG, "Нет разрешения на показ уведомлений");
                checkNotificationPermission();
                return;
            }
        }

        sendNotification();
    }

    private void sendNotification() {
        try {
            // Создаем уведомление с ВЫСОКИМ приоритетом для всплывания
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("МИРЭА - Российский технологический университет")
                    .setContentText("Поздравляем, " + STUDENT_NAME + "!")
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)  // HIGH приоритет
                    .setAutoCancel(true)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Студент: " + STUDENT_NAME + "\n" +
                                    "Группа: " + GROUP_NUMBER + "\n\n" +
                                    "Это демонстрационное уведомление, которое должно " +
                                    "появиться на экране в виде всплывающего уведомления."))
                    .setContentInfo("МИРЭА")
                    .setSubText("Срочное уведомление")
                    .setWhen(System.currentTimeMillis())
                    // Важно: эти флаги помогают уведомлению появиться на экране
                    .setFullScreenIntent(null, false);

            // Отправляем уведомление
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(NOTIFICATION_ID, builder.build());

            Log.d(TAG, "Уведомление успешно отправлено с ID: " + NOTIFICATION_ID);

        } catch (SecurityException e) {
            Log.e(TAG, "Ошибка при отправке уведомления: " + e.getMessage());
        }
    }
}