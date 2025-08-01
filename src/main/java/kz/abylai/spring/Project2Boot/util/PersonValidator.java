package kz.abylai.spring.Project2Boot.util;


import kz.abylai.spring.Project2Boot.models.Person;
import kz.abylai.spring.Project2Boot.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(personService.getPerson(person.getFullName()).isPresent()){
            errors.rejectValue("fullName", "", "Такое имя уже существует в нашей базе");
        }
    }
}
