package kz.abylai.spring.Project2Boot.repositories;

import kz.abylai.spring.Project2Boot.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByNameStartingWithIgnoreCase(String prefix);
}
