package danihwsr.tournee;

import danihwsr.tournee.web.User;
import danihwsr.tournee.web.UserService;

import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {FongoConfiguration.class, UserService.class})
@ActiveProfiles("test")
public class UserServiceFongoTests {

    @Rule
    public MongoDbRule mongo = MongoDbRule.MongoDbRuleBuilder.newMongoDbRule().defaultSpringMongoDb("fongo-test");

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserService service;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void createUserShouldEncodePassword() throws Exception {
        String nick = "dooniel";
        String password = "ThisShouldBePersistedAsAnEncodedString";

        User user = User.builder()
                .nickname(nick)
                .password(password)
                .build();

        this.service.createUser(user);

        User u = this.service.getUserByNickname(nick);

        String message = String.format("before: %s, after: %s", password, u.getPassword());

        logger.info(message);

        assertThat(u.getPassword(), not(password));

    }
}
