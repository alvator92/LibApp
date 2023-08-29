package ru.test.libapp.dao;

import org.springframework.stereotype.Component;
import ru.test.libapp.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Tom", 24,"Tom@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Bike",43,"Bike@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Mike",22,"Mike@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Katy",12,"Katy@mail.ru"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);

    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdate = show(id);
        personToBeUpdate.setName(updatedPerson.getName());
        personToBeUpdate.setAge(updatedPerson.getAge());
        personToBeUpdate.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
