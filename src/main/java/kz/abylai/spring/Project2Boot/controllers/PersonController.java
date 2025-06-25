package kz.abylai.spring.Project2Boot.controllers;

import jakarta.validation.Valid;
import kz.abylai.spring.Project2Boot.models.Book;
import kz.abylai.spring.Project2Boot.models.Person;
import kz.abylai.spring.Project2Boot.services.BookService;
import kz.abylai.spring.Project2Boot.services.PersonService;
import kz.abylai.spring.Project2Boot.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final PersonValidator personValidator;
    private final BookService bookService;

    @Autowired
    public PersonController(PersonService personService, PersonValidator personValidator, BookService bookService) {
        this.personService = personService ;
        this.personValidator = personValidator;
        this.bookService = bookService;
    }

    @GetMapping()
    public String showPeople(Model model) {
        model.addAttribute("people", personService.getPeople());
        return "people/showAllPeople";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        Person person = personService.getPerson(id);
        List<Book> books = bookService.getBooksWithExpireCheck(person.getBooks());
        if (books == null) books = Collections.emptyList(); // ← подстраховка

        model.addAttribute("person", person);
        model.addAttribute("books", books);
        return "people/showPerson";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) throws SQLException {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        personService.createPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("person", personService.getPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        personService.updatePerson(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        personService.deletePerson(id);
        return "redirect:/people";
    }

}
