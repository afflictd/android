package ru.mirea.puzinva.employeedb;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
@Database(entities = {Superhero.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "superhero_database";

    public abstract SuperheroDao superheroDao();

    // Singleton паттерн для получения экземпляра базы данных
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    DATABASE_NAME
                            )
                            .allowMainThreadQueries()  // разрешаем запросы в главном потоке (для простоты)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
