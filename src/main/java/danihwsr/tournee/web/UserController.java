package danihwsr.tournee.web;

import danihwsr.tournee.UserNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// main route of this controller
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // subroute: get /users/all
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        // when declaring class attributes as private
        //+ make sure to implement public getters for the attribute
        // return value is automatically parsed to json ({class.Attribute: value, ...})
        return this.userService.getAllUsers();
    }

    @GetMapping(value = "/{nickName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByNick(@PathVariable String nickName) throws UserNotFoundException {
        return this.userService.getUserByNickname(nickName);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user) throws Exception {
        return this.userService.createUser(user);
    }

    @DeleteMapping(value = "/delete/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User deleteUser(@PathVariable String userId) throws UserNotFoundException {
        return this.userService.deleteUser(userId);
    }

    @PutMapping(value = "/update/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@PathVariable String userId, @RequestBody User user) throws Exception {
        return this.userService.updateUser(userId, user);
    }

}
