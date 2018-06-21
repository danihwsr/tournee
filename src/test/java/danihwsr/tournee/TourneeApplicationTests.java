package danihwsr.tournee;
import danihwsr.tournee.web.User;
import danihwsr.tournee.web.UserController;

import danihwsr.tournee.web.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class TourneeApplicationTests {

    @Autowired
    private MockMvc mock;

    @MockBean
    private UserService service;

    @Test
    public void getAllUsersShouldReturnUsersFromService() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        users.add(new User());

        when(service.getAllUsers()).thenReturn(users);

        this.mock.perform(get("/users/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));

    }

    @Test
    public void getUserByNicknameShouldReturnUserFromService() throws Exception {
        String nick = "dooniel";
        User user = User.builder()
                .nickname(nick)
                .build();

        when(service.getUserByNickname( nick )).thenReturn(user);

        this.mock.perform(get("/users/" + nick ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickname", equalToIgnoringCase(nick)));

    }

}