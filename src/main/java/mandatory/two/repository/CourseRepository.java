package mandatory.two.repository;

import mandatory.two.model.Course;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Matthias Skou 20/11/2018
 */

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>, JpaSpecificationExecutor {
    List<Course> findAll();
    List<Course> findAll(Specification specification);
}
