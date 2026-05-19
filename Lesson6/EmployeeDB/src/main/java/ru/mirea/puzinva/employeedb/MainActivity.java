package ru.mirea.puzinva.employeedb;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txtResult;
    private SuperheroDao superheroDao;

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

        txtResult = findViewById(R.id.txtResult);

        // Получение DAO из базы данных
        AppDatabase db = App.getInstance().getDatabase();
        superheroDao = db.superheroDao();

        // Инициализация кнопок
        Button btnAddHeroes = findViewById(R.id.btnAddHeroes);
        Button btnShowAll = findViewById(R.id.btnShowAll);
        Button btnShowHeroes = findViewById(R.id.btnShowHeroes);
        Button btnShowVillains = findViewById(R.id.btnShowVillains);
        Button btnDeleteAll = findViewById(R.id.btnDeleteAll);

        btnAddHeroes.setOnClickListener(v -> addTestHeroes());
        btnShowAll.setOnClickListener(v -> showAllHeroes());
        btnShowHeroes.setOnClickListener(v -> showOnlyHeroes());
        btnShowVillains.setOnClickListener(v -> showOnlyVillains());
        btnDeleteAll.setOnClickListener(v -> deleteAllHeroes());
    }

    // Добавление тестовых супер-героев
    private void addTestHeroes() {
        new Thread(() -> {
            // Очищаем таблицу перед добавлением (опционально)
            // superheroDao.deleteAll();

            // Создаём супер-героев
            Superhero[] heroes = {
                    new Superhero("Супермен", "Летать, лазерные глаза", 95, false),
                    new Superhero("Бэтмен", "Богатство, интеллект", 85, false),
                    new Superhero("Человек-паук", "Лазать по стенам, паутина", 80, false),
                    new Superhero("Железный человек", "Броня, интеллект", 90, false),
                    new Superhero("Тор", "Молнии, молот", 98, false),
                    new Superhero("Локи", "Иллюзии, магия", 75, true),
                    new Superhero("Джокер", "Хаос, интеллект", 70, true),
                    new Superhero("Магнето", "Контроль магнетизма", 88, true),
                    new Superhero("Танос", "Сила, выносливость", 99, true)
            };

            for (Superhero hero : heroes) {
                superheroDao.insert(hero);
            }

            runOnUiThread(() -> txtResult.setText("✅ Добавлено " + heroes.length + " супер-героев!\n"));
        }).start();
    }

    // Показать всех героев (и героев, и злодеев)
    private void showAllHeroes() {
        new Thread(() -> {
            List<Superhero> heroes = superheroDao.getAll();
            runOnUiThread(() -> displayHeroes(heroes, "Все персонажи"));
        }).start();
    }

    // Показать только героев
    private void showOnlyHeroes() {
        new Thread(() -> {
            List<Superhero> heroes = superheroDao.getOnlyHeroes();
            runOnUiThread(() -> displayHeroes(heroes, "Герои"));
        }).start();
    }

    // Показать только злодеев
    private void showOnlyVillains() {
        new Thread(() -> {
            List<Superhero> villains = superheroDao.getOnlyVillains();
            runOnUiThread(() -> displayHeroes(villains, "Злодеи"));
        }).start();
    }

    // Удалить всех героев
    private void deleteAllHeroes() {
        new Thread(() -> {
            superheroDao.deleteAll();
            runOnUiThread(() -> txtResult.setText("🗑️ Все записи удалены!\nКоличество записей: " + superheroDao.getCount()));
        }).start();
    }

    // Отображение списка героев в TextView
    private void displayHeroes(List<Superhero> heroes, String title) {
        if (heroes == null || heroes.isEmpty()) {
            txtResult.setText(title + ":\n📭 Нет записей в базе данных");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(title).append(" (").append(heroes.size()).append("):\n");
        sb.append("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        for (Superhero hero : heroes) {
            String type = hero.isVillain() ? "👿 Злодей" : "🦸 Герой";
            sb.append(String.format("ID: %d | %s\n", hero.getId(), hero.getName()))
                    .append(String.format("   Сила: %d | Тип: %s\n", hero.getStrengthLevel(), type))
                    .append(String.format("   Суперспособность: %s\n", hero.getSuperpower()))
                    .append("──────────────────────────────────────────────\n");
        }

        txtResult.setText(sb.toString());
    }
}