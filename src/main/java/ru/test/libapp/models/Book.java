package ru.test.libapp.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int book_id;
    private int person_id;

    @NotEmpty(message = "У книги должно быть название")
    @Size(min = 2, max = 30, message = "Название должно быть в пределах от 2 до 30 символов")
    private String book;

    @NotEmpty(message = "У книги Должен быть автор")
    @Size(min = 2, max = 30, message = "Имя автора должно быть")
    private String author;

    @Min(value = 1900)
    private String age;

    public Book(int book_id, int person_id, String book, String author, String age) {
        this.book_id = book_id;
        this.person_id = person_id;
        this.book = book;
        this.author = author;
        this.age = age;
    }

    public Book() {
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
