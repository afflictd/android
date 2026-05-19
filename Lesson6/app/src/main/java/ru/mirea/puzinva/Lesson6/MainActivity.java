package ru.mirea.puzinva.Lesson6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editGroup, editNumber, editMovie;
    private Button btnSave;
    private SharedPreferences sharedPref;

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

        editGroup = findViewById(R.id.editGroup);
        editNumber = findViewById(R.id.editNumber);
        editMovie = findViewById(R.id.editMovie);
        btnSave = findViewById(R.id.btnSave);

        sharedPref = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);

        loadSavedData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("GROUP", editGroup.getText().toString());
        editor.putInt("NUMBER", Integer.parseInt(editNumber.getText().toString()));
        editor.putString("MOVIE", editMovie.getText().toString());
        editor.apply();
    }

    private void loadSavedData() {
        String group = sharedPref.getString("GROUP", "");
        int number = sharedPref.getInt("NUMBER", 0);
        String movie = sharedPref.getString("MOVIE", "");

        editGroup.setText(group);
        editNumber.setText(String.valueOf(number));
        editMovie.setText(movie);
    }
}