package ru.test.libapp.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "Person" +
        "")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be Empty")
    @Size(min = 2, max = 30, message = "Name should be beetween 2 to 30 characters")
    private String fullName;

    @Column(name = "age")
    @Min(value = 0, message = "Age should be greater than 0")
    private int yearOfBirth;

    @OneToMany(mappedBy = "owner",fetch = FetchType.LAZY)
    private List<Book> bookList;

    public Person(String name, int age) {
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

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
