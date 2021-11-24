package quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import quiz.dao.QuizDAO;
import quiz.dao.UserDAO;
import quiz.entities.User;

@Controller
@RequestMapping("/")
public class MyController {

    private QuizDAO quizRepo;
    private UserDAO userRepo;

    @Autowired
    public MyController(QuizDAO quizRepo, UserDAO userRepo) {
        this.quizRepo = quizRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("user", userRepo.findById(1).get());
        return "index";
    }
}
