package ru.mirea.puzinva.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private EditText etName, etAge, etEmail;
    private RadioGroup rgGender;
    private Button btnSave;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "user_profile";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etName = view.findViewById(R.id.etName);
        etAge = view.findViewById(R.id.etAge);
        etEmail = view.findViewById(R.id.etEmail);
        rgGender = view.findViewById(R.id.rgGender);
        btnSave = view.findViewById(R.id.btnSave);

        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        loadProfileData();
        btnSave.setOnClickListener(v -> saveProfileData());
    }

    private void loadProfileData() {
        String name = sharedPreferences.getString("name", "");
        int age = sharedPreferences.getInt("age", 0);
        String email = sharedPreferences.getString("email", "");
        int gender = sharedPreferences.getInt("gender", -1);

        etName.setText(name);
        if (age > 0) etAge.setText(String.valueOf(age));
        etEmail.setText(email);
        if (gender != -1) {
            if (gender == 0) {
                rgGender.check(R.id.rbMale);
            } else {
                rgGender.check(R.id.rbFemale);
            }
        }
    }

    private void saveProfileData() {
        String name = etName.getText().toString().trim();
        String ageStr = etAge.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        int selectedGenderId = rgGender.getCheckedRadioButtonId();

        if (name.isEmpty()) {
            Toast.makeText(getContext(), "Введите имя", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("email", email);

        if (!ageStr.isEmpty()) {
            editor.putInt("age", Integer.parseInt(ageStr));
        }

        if (selectedGenderId == R.id.rbMale) {
            editor.putInt("gender", 0);
        } else if (selectedGenderId == R.id.rbFemale) {
            editor.putInt("gender", 1);
        }

        editor.apply();
        Toast.makeText(getContext(), "Профиль сохранён", Toast.LENGTH_SHORT).show();
    }
}