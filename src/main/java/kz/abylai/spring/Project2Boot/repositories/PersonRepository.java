package kz.abylai.spring.Project2Boot.repositories;


import kz.abylai.spring.Project2Boot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    Optional<Person> findByFullName(String fullName);
}
