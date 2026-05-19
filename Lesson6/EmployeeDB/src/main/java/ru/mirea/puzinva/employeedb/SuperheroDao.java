package ru.mirea.puzinva.employeedb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface SuperheroDao {

    // Вставка одного героя
    @Insert
    void insert(Superhero superhero);

    // Вставка нескольких героев
    @Insert
    void insertAll(Superhero... superheroes);

    // Обновление данных героя
    @Update
    void update(Superhero superhero);

    // Удаление героя
    @Delete
    void delete(Superhero superhero);

    // Получение всех героев
    @Query("SELECT * FROM superheroes")
    List<Superhero> getAll();

    // Получение героя по ID
    @Query("SELECT * FROM superheroes WHERE id = :id")
    Superhero getById(long id);

    // Получение всех героев с уровнем силы выше заданного
    @Query("SELECT * FROM superheroes WHERE strengthLevel > :minStrength")
    List<Superhero> getByMinStrength(int minStrength);

    // Получение только героев (не злодеев)
    @Query("SELECT * FROM superheroes WHERE isVillain = 0")
    List<Superhero> getOnlyHeroes();

    // Получение только злодеев
    @Query("SELECT * FROM superheroes WHERE isVillain = 1")
    List<Superhero> getOnlyVillains();

    // Получение количества записей в таблице
    @Query("SELECT COUNT(*) FROM superheroes")
    int getCount();

    // Удаление всех записей
    @Query("DELETE FROM superheroes")
    void deleteAll();
}
