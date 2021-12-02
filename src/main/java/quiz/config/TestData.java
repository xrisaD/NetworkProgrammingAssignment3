package quiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import quiz.dao.QuestionDAO;
import quiz.dao.QuizDAO;
import quiz.dao.ResultDAO;
import quiz.dao.UserDAO;
import quiz.entities.Question;
import quiz.entities.Quiz;
import quiz.entities.Result;
import quiz.entities.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Profile("dev")
public class TestData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserDAO userRepo;
    @Autowired
    private QuestionDAO questionRepo;
    @Autowired
    private QuizDAO quizRepo;
    @Autowired
    private ResultDAO resultRepo;

    private java.util.List<Question> List;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        populateDB();
    }

    private void populateDB(){

        //Users
        User userOne  = new User();
        userOne.setUsername("ada@kth.se");
        userOne.setPassword("qwerty");

        User userTwo  = new User();
        userTwo.setUsername("beda@kth.se");
        userTwo.setPassword("123456");

        userRepo.save(userOne);
        userRepo.save(userTwo);

        //Questions
        Question questionOne = new Question();
        questionOne.setText("Which planets are larger than earth?");
        questionOne.setOptions("Mercury/Mars/Saturn");
        questionOne.setAnswer("0/0/1");

        Question questionTwo = new Question();
        questionTwo.setText("Which planets are farther away from the sun than earth?");
        questionTwo.setOptions("Mercury/Mars/Saturn");
        questionTwo.setAnswer("0/1/1");

        Question questionThree = new Question();
        questionThree.setText("Which planets have rings?");
        questionThree.setOptions("Mercury/Mars/Saturn");
        questionThree.setAnswer("0/0/1");

        questionRepo.save(questionOne);
        questionRepo.save(questionTwo);
        questionRepo.save(questionThree);

        //Quizzes
        Quiz quizOne = new Quiz();
        ArrayList<Question> questions = new ArrayList();
        questions.addAll(Arrays.asList(questionOne, questionTwo, questionThree));
        quizOne.setSubject("Astrology");
        quizOne.setQuestions(questions);

        quizRepo.save(quizOne);

        //Results
        Result resultOne = new Result(1, userOne, quizOne);
        Result resultTwo = new Result(10, userOne, quizOne);
        resultRepo.save(resultOne);
        resultRepo.save(resultTwo);

    }
}
