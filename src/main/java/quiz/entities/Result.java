package quiz.entities;

import javax.persistence.*;

@Entity
@Table(name="results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer score;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quizId")
    private Quiz quiz;

    public Result(Integer score, User user, Quiz quiz) {
        this.score = score;
        this.user = user;
        this.quiz = quiz;
    }

    public Result() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", score=" + score +
                ", user=" + user +
                ", quiz=" + quiz +
                '}';
    }
}
