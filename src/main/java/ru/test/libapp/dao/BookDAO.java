package ru.test.libapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.test.libapp.models.Book;
import ru.test.libapp.models.BookRowMapper;

import java.util.List;

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
}
