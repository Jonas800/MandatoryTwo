package mandatory.two.repository;

import mandatory.two.model.Teacher;
import mandatory.two.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
