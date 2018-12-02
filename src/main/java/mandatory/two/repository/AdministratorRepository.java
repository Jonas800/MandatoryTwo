package mandatory.two.repository;

import mandatory.two.model.Administrator;
import mandatory.two.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministratorRepository extends CrudRepository<Administrator, Long> {
    List<Administrator> findAll();
}
