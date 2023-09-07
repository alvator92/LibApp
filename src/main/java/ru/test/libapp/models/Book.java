package ru.test.libapp.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book")
    @NotEmpty(message = "У книги должно быть название")
    @Size(min = 2, max = 100, message = "Название должно быть в пределах от 2 до 100 символов")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "У книги Должен быть автор")
    @Size(min = 2, max = 100, message = "Имя автора должно быть")
    private String author;

    @Column(name = "age")
    @Min(value = 1500)
    private int age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Book(String book, String author, int age) {
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
