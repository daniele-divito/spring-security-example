package secexample.service;

import secexample.db.entities.ApplicationUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface ApplicationUserService extends UserDetailsService {

    Optional<ApplicationUser> findUserByUsername(String s);

    void validatePassword(String password);

    void deleteUser(String email);

    ApplicationUser incrementLoginAttempts(ApplicationUser applicationUser);

    void resetLoginAttempts(ApplicationUser applicationUser);

    void lockUser(ApplicationUser applicationUser);
}
