package quiz.dao;

import org.springframework.data.repository.CrudRepository;
import quiz.entities.Question;

public interface QuestionDAO extends CrudRepository<Question, Integer> {
}
