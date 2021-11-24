package quiz.dao;

import org.springframework.data.repository.CrudRepository;
import quiz.entities.Result;

public interface ResultDAO extends CrudRepository<Result, Integer> {
}
