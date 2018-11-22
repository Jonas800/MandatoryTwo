package mandatory.two.repository;

import mandatory.two.model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Matthias Skou 20/11/2018
 */

public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findAll();
}
