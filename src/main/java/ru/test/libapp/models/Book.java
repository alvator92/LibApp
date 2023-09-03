package ru.test.libapp.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "У книги должно быть название")
    @Size(min = 2, max = 100, message = "Название должно быть в пределах от 2 до 100 символов")
    private String title;

    @NotEmpty(message = "У книги Должен быть автор")
    @Size(min = 2, max = 100, message = "Имя автора должно быть")
    private String author;

    @Min(value = 1500)
    private int age;

    public Book(int book_id, String book, String author, int age) {
        this.id = book_id;
        this.title = book;
        this.author = author;
        this.age = age;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
