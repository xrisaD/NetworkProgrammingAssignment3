package quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import quiz.dao.QuestionDAO;
import quiz.dao.QuizDAO;
import quiz.dao.ResultDAO;
import quiz.dao.UserDAO;
import quiz.entities.Question;
import quiz.entities.Quiz;
import quiz.entities.Result;
import quiz.entities.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MyController {

    private QuestionDAO questionRepo;
    private ResultDAO resultRepo;
    private QuizDAO quizRepo;
    private UserDAO userRepo;

    @Autowired
    public MyController(QuizDAO quizRepo, UserDAO userRepo, ResultDAO resultRepo, QuestionDAO questionRepo) {
        this.quizRepo = quizRepo;
        this.userRepo = userRepo;
        this.resultRepo = resultRepo;
        this.questionRepo = questionRepo;
    }


   @GetMapping("/login")
    public String login(){
        return "login";
   }

   @GetMapping("/")
    public String home(Authentication auth, Model model) {
        Integer principalId = ((User)auth.getPrincipal()).getId();
        User user = userRepo.findById(principalId).get();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("results", resultRepo.findByUser(user));
        model.addAttribute("quizzes", quizRepo.findAll());
        return "index";
    }

    @GetMapping("/quiz")
    public String quiz(Model model,
                       @RequestParam Integer id) {
        model.addAttribute("quiz", quizRepo.findById(id).get());
        return "quiz";

    }

    @PostMapping("/quiz_submit")
    public String quizEvaluation(Authentication auth, Model model,
                                 @RequestParam("quizId") Integer quizId,
                                 @RequestParam("answers") List<String> answers){
        Integer principalId = ((User)auth.getPrincipal()).getId();
        User user = userRepo.findById(principalId).get();
        // get the correct answers
        Optional<Quiz> optionalQuiz = quizRepo.findById(quizId);
        if (optionalQuiz.isPresent()) {
            Quiz q = optionalQuiz.get();

            // compute the score
            int score = 0;
            for (Question question: q.getQuestions()) {
                int questionId = question.getId();
                String[] correctAnswers = question.getAnswer().split("/");

                for (int i=0; i < correctAnswers.length; i++) {
                    if (Objects.equals(correctAnswers[i], "1") && answers.contains(questionId + "-" + i)) {
                        score += 1;
                    } else if (Objects.equals(correctAnswers[i], "0") && answers.contains(questionId + "-" + i)) {
                        score -= 1;
                    } else if (Objects.equals(correctAnswers[i], "1") && !answers.contains(questionId + "-" + i)) {
                        score -= 1;
                    } else if (Objects.equals(correctAnswers[i], "0") && !answers.contains(questionId + "-" + i)) {
                        score += 1;
                    }
                }
            }
            // save it
            resultRepo.save(new Result(score, user, q));

            // show the result
            model.addAttribute("subject", q.getSubject());
            model.addAttribute("score", score);

            return "result";
        } else {
            return "index";
        }
    }
}
