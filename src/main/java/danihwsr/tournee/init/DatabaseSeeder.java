package danihwsr.tournee.init;

import danihwsr.tournee.web.Roles;
import danihwsr.tournee.web.UserRepository;
import danihwsr.tournee.web.User;
import danihwsr.tournee.web.Roles.*;
import danihwsr.tournee.web.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Profile("!test")
// every object that implements command line runner is executed after the spring app has started (beans were created)
// order command line runners with @Order
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... strings) throws Exception {
        User u1 = User.builder()
                .firstname("daniel")
                .lastname("knauser")
                .nickname("dooniel")
                .mail("dooniel@web.de")
                .password("admin")
                .age(28)
                .roles( new Roles[]{Roles.ROLE_USER, Roles.ROLE_ADMIN} )
                .build();

        User u2 = User.builder()
                .firstname("max")
                .lastname("mustermann")
                .nickname("xXx_max_xXx")
                .mail("max@gmail.com")
                .password("ibims")
                .age(32)
                .build();

        User u3 = User.builder()
                .firstname("max")
                .lastname("musterfrau")
                .nickname("69_max_69")
                .mail("maxi@hotmail.de")
                .password("ib1ms")
                .age(18)
                .disabled(true)
                .build();

        User u4 = User.builder()
                .firstname("peter")
                .lastname("meier")
                .nickname("the_meiernator")
                .mail("meier@peter.org")
                .password("user")
                .age(56)
                .build();

        // delete all documents in collection 'users'
        this.userRepository.deleteAll();

        // save to database
        List<User> users = Arrays.asList(u1, u2, u3, u4);
        for (User u : users ) {
            this.service.createUser(u);
        }
    }

}
