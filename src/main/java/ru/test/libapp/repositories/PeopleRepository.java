package ru.test.libapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.libapp.models.Book;
import ru.test.libapp.models.Person;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    List<Person> findByFullName(String name);

    List<Person> findByFullNameOrderByYearOfBirth(String name);

    List<Person> findByFullNameStartingWith(String startingWith);

    Person findByBookList(Book book);


}
