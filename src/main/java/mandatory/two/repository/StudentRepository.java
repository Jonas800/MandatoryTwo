package mandatory.two.repository;


import mandatory.two.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by Matthias Skou 27/11/2018
 */

public interface StudentRepository extends CrudRepository<Student, Long> {
    ArrayList<Student> findAll();
}
