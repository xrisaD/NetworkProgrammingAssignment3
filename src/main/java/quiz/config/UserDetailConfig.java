package quiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import quiz.dao.UserDAO;
import quiz.entities.User;

import java.util.Optional;

@Service
public class UserDetailConfig implements UserDetailsService {

    @Autowired
    UserDAO userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> tempUser  = userRepo.findByUsername(username);
        if (tempUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return tempUser.get();
    }
}
