package quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import quiz.dao.QuizDAO;

@Controller
@RequestMapping("/")
public class MyController {

    private QuizDAO quizRepo;


    @Autowired
    public MyController(QuizDAO quizRepo) {
        this.quizRepo = quizRepo;


    }

    @GetMapping("/")
    public String home() {
        var quiz = quizRepo.findAll();
        return "index";
    }
}
