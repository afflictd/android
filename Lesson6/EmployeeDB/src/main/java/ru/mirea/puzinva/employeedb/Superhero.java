package ru.mirea.puzinva.employeedb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "superheroes")
public class Superhero {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String superpower;
    private int strengthLevel;
    private boolean isVillain;

    public Superhero() {
    }

    public Superhero(String name, String superpower, int strengthLevel, boolean isVillain) {
        this.name = name;
        this.superpower = superpower;
        this.strengthLevel = strengthLevel;
        this.isVillain = isVillain;
    }

    // Геттеры и сеттеры
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }

    public int getStrengthLevel() {
        return strengthLevel;
    }

    public void setStrengthLevel(int strengthLevel) {
        this.strengthLevel = strengthLevel;
    }

    public boolean isVillain() {
        return isVillain;
    }

    public void setVillain(boolean villain) {
        isVillain = villain;
    }

    @Override
    public String toString() {
        return "Superhero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", superpower='" + superpower + '\'' +
                ", strengthLevel=" + strengthLevel +
                ", isVillain=" + isVillain +
                '}';
    }
}