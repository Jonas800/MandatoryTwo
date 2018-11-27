package mandatory.two.repository;

import mandatory.two.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by Matthias Skou 27/11/2018
 */

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    ArrayList<Teacher> findAll();
}
