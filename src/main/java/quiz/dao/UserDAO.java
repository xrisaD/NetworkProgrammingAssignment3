package quiz.dao;

import org.springframework.data.repository.CrudRepository;
import quiz.entities.User;

import java.util.Optional;

public interface UserDAO extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
