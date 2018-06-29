package danihwsr.tournee.web;

import danihwsr.tournee.UserNotFoundException;
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
    @GetMapping(value = "/all")
    public List<User> getAll() {
        // when declaring class attributes as private
        //+ make sure to implement public getters for the attribute
        // return value is automatically parsed to json ({class.Attribute: value, ...})
        return this.userService.getAllUsers();
    }

    @GetMapping(value = "/{nickName}")
    public User getUserByNick(@PathVariable String nickName) throws UserNotFoundException {
        return this.userService.getUserByNickname(nickName);
    }

    @PostMapping(value = "/create")
    public User createUser(@RequestBody User user) throws Exception {
        return this.userService.createUser(user);
    }

    @DeleteMapping(value = "/delete/{userId}")
    public List<User> deleteUser(@PathVariable String userId) {
        return this.userService.deleteUser(userId);
    }

    @PutMapping(value = "/update/{userId}")
    public User updateUser(@PathVariable String userId, @RequestBody User user) throws Exception {
        return this.userService.updateUser(userId, user);
    }

}
