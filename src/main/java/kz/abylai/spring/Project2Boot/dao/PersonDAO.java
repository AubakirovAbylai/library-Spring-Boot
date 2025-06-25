package kz.abylai.spring.Project2Boot.dao;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@Component
public class PersonDAO {


}
