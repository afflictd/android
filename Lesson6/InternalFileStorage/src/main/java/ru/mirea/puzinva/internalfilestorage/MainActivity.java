package ru.mirea.puzinva.internalfilestorage;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText editHistoricalDate, editDescription;
    private Button btnSave;
    private TextView txtFileContent;
    private String fileName = "historical_event.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        editHistoricalDate = findViewById(R.id.editHistoricalDate);
        editDescription = findViewById(R.id.editDescription);
        btnSave = findViewById(R.id.btnSave);
        txtFileContent = findViewById(R.id.txtFileContent);

        loadFileContent();

        btnSave.setOnClickListener(v -> saveToFile());
    }

    private void saveToFile() {
        String date = editHistoricalDate.getText().toString().trim();
        String description = editDescription.getText().toString().trim();

        if (date.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Заполните оба поля", Toast.LENGTH_SHORT).show();
            return;
        }

        String content = "Памятная дата: " + date + "\nОписание: " + description;

        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
            Toast.makeText(this, "Файл сохранён: " + fileName, Toast.LENGTH_SHORT).show();
            loadFileContent();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка записи: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadFileContent() {
        FileInputStream inputStream = null;
        try {
            inputStream = openFileInput(fileName);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            String text = new String(bytes);
            txtFileContent.setText(text);
        } catch (IOException e) {
            txtFileContent.setText("Файл пока не создан. Введите данные и нажмите 'Сохранить'");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}