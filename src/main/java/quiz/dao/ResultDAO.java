package quiz.dao;

import org.springframework.data.repository.CrudRepository;
import quiz.entities.Result;
import quiz.entities.User;

import java.util.List;

public interface ResultDAO extends CrudRepository<Result, Integer> {

    List<Result> findByUser(User user);
}
