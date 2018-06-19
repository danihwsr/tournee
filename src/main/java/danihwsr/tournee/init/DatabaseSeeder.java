package danihwsr.tournee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
// every object that implements command line runner is executed after the spring app has started (beans were created)
// order command line runners with @Order
public class DatabaseSeeder implements CommandLineRunner {

    private UserRepository userRepository;

    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        User u1 = new User(
                "daniel", "knauser", "dooniel", "dooniel@web.de"
        );
        User u2 = new User(
                "max", "mustermann", "xXx_max_xXx", "max@gmail.com"
        );
        User u3 = new User(
                "max", "musterfrau", "69_max_69", "maxi@hotmail.de"
        );
        User u4 = new User(
                "peter", "meier", "the_meiernator", "meier@peter.org"
        );

        // delete all documents in collection 'users'
        this.userRepository.deleteAll();

        // save to database
        List<User> users = Arrays.asList(u1, u2, u3, u4);
        this.userRepository.saveAll(users);

    }

}
