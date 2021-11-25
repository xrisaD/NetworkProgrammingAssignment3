package quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import quiz.dao.QuestionDAO;
import quiz.dao.QuizDAO;
import quiz.dao.ResultDAO;
import quiz.dao.UserDAO;
import quiz.entities.User;

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

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("user", userRepo.findById(1).get());
        return "index";
    }
}
