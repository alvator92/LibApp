package ru.test.libapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.test.libapp.dao.PersonDAO;
import ru.test.libapp.models.Person;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDao;

    @Autowired
    public PersonValidator(PersonDAO personDao) {
        this.personDao = personDao;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        // посмотерть есть ли такой же человек в БД
            if(personDao.show(person.getFullName()).isPresent()) {
                errors.rejectValue("name", "", "Такое имя уже есть в Базе");
            }
    }
}
