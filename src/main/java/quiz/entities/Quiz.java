package quiz.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "quiz")
    private List<Result> results;

    private String subject;

    @ManyToMany
    @JoinTable(
            name = "selectors",
            joinColumns = @JoinColumn(name = "quizId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "questionId", referencedColumnName = "id")
    )
    private List<Question> questions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
