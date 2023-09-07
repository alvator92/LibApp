package ru.test.libapp.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.test.libapp.models.Book;
import ru.test.libapp.models.BookRowMapper;
import ru.test.libapp.models.Person;
import ru.test.libapp.models.PersonRowMapper;
import org.hibernate.SessionFactory;


import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SessionFactory sessionFactory;


    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate, SessionFactory sessionFactory) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        // Тут и будет обычный Hibernate код
        return session.createQuery("select p from Person p", Person.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
    }

    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE name=?", new Object[]{name},
                new PersonRowMapper()).stream().findAny();
    }
    @Transactional
    public void createPerson(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);

    }

    @Transactional
    public void updatePerson(int id, Person updatePerson) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, id);

        person.setFullName(updatePerson.getFullName());
        person.setYearOfBirth(updatePerson.getYearOfBirth());
    }
    @Transactional
    public void deletePerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }

    @Transactional
    public List<Book> getBooksByPersonId(int id) {
        Session session = sessionFactory.getCurrentSession();

        Person person = session.get(Person.class, id);

        Hibernate.initialize(person.getBookList());

        return person.getBookList();

    }
}
