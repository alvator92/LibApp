package ru.test.libapp.dao;

import org.springframework.stereotype.Component;
import ru.test.libapp.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/lib_db";
    private static final String USERNAME = "superuser";
    private static final String PASSWORD = "qwe123_";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "select * from Person;";
            ResultSet rs = statement.executeQuery(SQL);
            System.out.println("rs выполнен");

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setAge(rs.getInt("age"));
                person.setEmail(rs.getString("email"));
                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public Person show(int id) {
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save(Person person) {
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
        try {
            Statement statement = connection.createStatement();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Person updatedPerson) {
//        Person personToBeUpdate = show(id);
//        personToBeUpdate.setName(updatedPerson.getName());
//        personToBeUpdate.setAge(updatedPerson.getAge());
//        personToBeUpdate.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
//        people.removeIf(p -> p.getId() == id);
    }
}
