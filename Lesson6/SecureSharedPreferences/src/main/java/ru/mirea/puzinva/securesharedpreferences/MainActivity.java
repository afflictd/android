package ru.mirea.puzinva.securesharedpreferences;

import android.content.SharedPreferences;
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
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    private EditText editPoetName;
    private TextView txtPoetName;
    private Button btnSave;
    private SharedPreferences secureSharedPreferences;
    private String mainKeyAlias;

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

        editPoetName = findViewById(R.id.editPoetName);
        txtPoetName = findViewById(R.id.txtPoetName);
        btnSave = findViewById(R.id.btnSave);

        try {
            mainKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }

        try {
            secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secure_poet_prefs",
                    mainKeyAlias,
                    getBaseContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            loadSavedPoetName();

        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }

        btnSave.setOnClickListener(v -> {
            savePoetName();
        });
    }

    private void savePoetName() {
        String poetName = editPoetName.getText().toString().trim();
        if (poetName.isEmpty()) {
            Toast.makeText(this, "Введите имя поэта", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            secureSharedPreferences.edit()
                    .putString("FAVORITE_POET", poetName)
                    .apply();
            Toast.makeText(this, "Сохранено с шифрованием", Toast.LENGTH_SHORT).show();
            loadSavedPoetName();
        } catch (Exception e) {
            Toast.makeText(this, "Ошибка сохранения: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadSavedPoetName() {
        if (secureSharedPreferences != null) {
            String savedName = secureSharedPreferences.getString("FAVORITE_POET", "Не задано");
            txtPoetName.setText("Любимый поэт: " + savedName);
        }
    }
}