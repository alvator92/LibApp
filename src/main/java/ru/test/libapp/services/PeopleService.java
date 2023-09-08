package ru.test.libapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.libapp.models.Book;
import ru.test.libapp.models.Person;
import ru.test.libapp.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person personToBeUpdate) {
        personToBeUpdate.setId(id);
        peopleRepository.save(personToBeUpdate);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void removeBookFromPerson(Book book) {
        Person owner = peopleRepository.findByBookList(book);
        owner.getBookList().remove(book);
        book.setOwner(null);
    }
}
