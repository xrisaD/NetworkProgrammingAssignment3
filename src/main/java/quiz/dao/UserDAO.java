package quiz.dao;

import org.springframework.data.repository.CrudRepository;
import quiz.entities.User;

public interface UserDAO extends CrudRepository<User, Integer> {
}
