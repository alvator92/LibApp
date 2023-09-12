package ru.test.libapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.libapp.models.Book;
import ru.test.libapp.models.Person;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByOwner(Person owner);

    List<Book> findByTitleStartingWith(String startingWith);
}
