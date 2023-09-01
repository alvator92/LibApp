package ru.test.libapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import ru.test.libapp.models.Person;
import ru.test.libapp.models.PersonRowMapper;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public List<Person> index() {
        return jdbcTemplate.query("Select * from person", new PersonRowMapper());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE person_id=?", new Object[]{id},
                new PersonRowMapper()).stream().findAny().orElse(null);
    }

    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE name=?", new Object[]{name},
                new PersonRowMapper()).stream().findAny();
    }

    public void createPerson(Person person) {
        jdbcTemplate.update("INSERT INTO PERSON (name, age) VALUES(?,?)", person.getName(), person.getAge());

    }

    public void updatePerson(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE PERSON SET name=?, age=? WHERE person_id=?",
                updatePerson.getName(), updatePerson.getAge(), updatePerson.getId());
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM PERSON WHERE person_id=?", id);
    }
}
