package quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


   @GetMapping("/login")
    public String login(){
        return "login";
   }

   @GetMapping("/")
    public String home(Authentication auth, Model model) {
        Integer principalId = ((User)auth.getPrincipal()).getId();
        model.addAttribute("user", userRepo.findById(principalId).get()); //might be null and crash -> .orElseThrow
        model.addAttribute("quizzes", quizRepo.findAll());
        return "index";
    }

    @GetMapping("/quiz")
    public String quiz(Model model,
                       @RequestParam Integer id) {
        return "quiz";

    }
}
