package ru.mirea.puzinva.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class FileWorkFragment extends Fragment {

    private EditText etInput, etKey;
    private TextView tvOutput;
    private Button btnEncrypt, btnDecrypt;
    private FloatingActionButton fabNew;
    private SecretKey secretKey;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filework, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etInput = view.findViewById(R.id.etInput);
        etKey = view.findViewById(R.id.etKey);
        tvOutput = view.findViewById(R.id.tvOutput);
        btnEncrypt = view.findViewById(R.id.btnEncrypt);
        btnDecrypt = view.findViewById(R.id.btnDecrypt);
        fabNew = view.findViewById(R.id.fabNew);

        generateNewKey();

        btnEncrypt.setOnClickListener(v -> encryptText());
        btnDecrypt.setOnClickListener(v -> decryptText());
        fabNew.setOnClickListener(v -> showNewEntryDialog());
    }

    private void generateNewKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            secretKey = keyGen.generateKey();
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            etKey.setText(encodedKey);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Ошибка генерации ключа", Toast.LENGTH_SHORT).show();
        }
    }

    private void encryptText() {
        String input = etInput.getText().toString();
        if (input.isEmpty()) {
            Toast.makeText(getContext(), "Введите текст для шифрования", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            byte[] keyBytes = Base64.getDecoder().decode(etKey.getText().toString());
            secretKey = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            tvOutput.setText("Зашифровано:\n" + encryptedText);
            Toast.makeText(getContext(), "Текст зашифрован", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Ошибка шифрования: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void decryptText() {
        String encryptedText = tvOutput.getText().toString();
        if (encryptedText.isEmpty() || encryptedText.equals("Зашифрованный текст появится здесь")) {
            Toast.makeText(getContext(), "Нет зашифрованного текста", Toast.LENGTH_SHORT).show();
            return;
        }

        String encoded = encryptedText.replace("Зашифровано:\n", "");

        try {
            byte[] keyBytes = Base64.getDecoder().decode(etKey.getText().toString());
            secretKey = new SecretKeySpec(keyBytes, "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encoded));
            String decryptedText = new String(decryptedBytes);
            tvOutput.setText("Расшифровано:\n" + decryptedText);
            Toast.makeText(getContext(), "Текст расшифрован", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Ошибка расшифровки: неверный ключ", Toast.LENGTH_SHORT).show();
        }
    }

    private void showNewEntryDialog() {
        NewEntryDialog dialog = new NewEntryDialog();
        dialog.show(getParentFragmentManager(), "NewEntryDialog");
    }
}