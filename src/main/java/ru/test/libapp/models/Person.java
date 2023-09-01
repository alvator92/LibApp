package ru.test.libapp.models;

import javax.validation.constraints.*;

public class Person {

    private int id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(min = 2, max = 30, message = "Некорректная длина имени")
    private String name;

    @Min(value = 0, message = "Возраст не может быть меньше 1")
    private int age;

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
