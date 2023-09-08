package ru.test.libapp.controllers;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.test.libapp.dao.BookDAO;
import ru.test.libapp.dao.PersonDAO;
import ru.test.libapp.models.Book;
import ru.test.libapp.models.Person;
import ru.test.libapp.services.BookService;
import ru.test.libapp.services.PeopleService;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final BookService bookService;
    private final PeopleService peopleService;


    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO, BookService bookService, PeopleService peopleService) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id, Model model) {

        model.addAttribute("book", bookService.findOne(id));

//        Optional<Person> bookOwner = bookDAO.getBookOwner(id);
        Book book = bookService.findOne(id);
        Optional<Person> bookOwner = Optional.ofNullable(book.getOwner());

        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people", peopleService.findAll());

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book")Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book")@Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        bookService.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("book", bookService.findOne(id));
        return "books/edit";

    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/edit";

        bookService.update(id, book);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        Book book = bookService.findOne(id);
        book.setOwner(null);
        bookService.update(id, book);
//        Person person = peopleService.findPersonByBookList(book);

        return "redirect:/books/" + id;
    }

    @PostMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        Book book = bookService.findOne(id);
        Person person = peopleService.findOneWithList(selectedPerson.getId());
        book.setOwner(person);
        bookService.update(id, book);

        return "redirect:/books/" + id;
    }
}

