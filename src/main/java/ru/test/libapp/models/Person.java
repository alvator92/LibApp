package ru.test.libapp.models;

import javax.validation.constraints.*;

public class Person {

    private int id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2, max = 100, message = "Некорректная длина имени")
    private String fullName;

    @Min(value = 0, message = "Возраст не может быть меньше 1")
    private int yearOfBirth;

    public Person(int id, String name, int age) {
        this.id = id;
        this.fullName = name;
        this.yearOfBirth = age;
    }

    public Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
