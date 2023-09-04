package ru.test.libapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.test.libapp.models.Book;
import ru.test.libapp.models.BookRowMapper;
import ru.test.libapp.models.Person;
import ru.test.libapp.models.PersonRowMapper;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM BOOK", new BookRowMapper());
    }

    public Book show(int id) {
            return jdbcTemplate.query("SELECT * FROM Book where book_id=?", new Object[]{id},
                new BookRowMapper()).stream().findAny().orElse(null);
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO BOOK (book, author, age) VALUES (?,?,?)",
                book.getTitle(), book.getAuthor(), book.getAge());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE BOOK SET book=?, author=?, age=? WHERE book_id=?",
                book.getTitle(), book.getAuthor(), book.getAge(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM BOOK WHERE book_id=?", id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("select p.* from book b " +
                "join person p on b.person_id = p.person_id " +
                "where b.book_id = ?", new Object[]{id}, new PersonRowMapper()).stream().findAny();
    }
}
