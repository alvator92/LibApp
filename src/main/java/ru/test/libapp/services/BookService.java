package ru.test.libapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.libapp.models.Book;
import ru.test.libapp.models.Person;
import ru.test.libapp.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BooksRepository booksRepository;

    @Autowired
    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findByBookTitle(String title) {
        return booksRepository.findByTitle(title);
    }

    public List<Book> findByBookAuthor(String author) {
        return booksRepository.findByAuthor(author);
    }

    public List<Book> findBookByOwner(Person owner) {
        return booksRepository.findByOwner(owner);
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findOne(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}
