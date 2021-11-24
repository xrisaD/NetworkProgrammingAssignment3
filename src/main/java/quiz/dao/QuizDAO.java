package quiz.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quiz.entities.Quiz;

@Repository
public interface QuizDAO extends CrudRepository<Quiz, Integer> {
}
