package quiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import quiz.dao.UserDAO;
import quiz.entities.User;

@Component
@Profile("dev")
public class TestData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserDAO userRepo;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        populateDB();
    }

    private void populateDB(){

        User userOne  = new User();
        userOne.setUsername("ada@kth.se");
        userOne.setPassword("qwerty");

        User userTwo  = new User();
        userTwo.setUsername("beda@kth.se");
        userTwo.setPassword("123456");

        userRepo.save(userOne);
        userRepo.save(userTwo);
    }
}
