package danihwsr.tournee.web;

import danihwsr.tournee.UserAlreadyExistsException;
import danihwsr.tournee.UserNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List; // interface
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

// due to springframework.boot.autoconfigure, this controller will be automatically registered
@RestController
// main route of this controller
@RequestMapping(value = "/users")
public class UserController {

    private List<User> users;
    private UserService userService;
    private Validator v = Validation.buildDefaultValidatorFactory().getValidator();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // subroute: get /users/all
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll() {
        // when declaring class attributes as private
        //+ make sure to implement public getters for the attribute
        // return value is automatically parsed to json ({class.Attribute: value, ...})
        return this.userService.getAllUsers();
    }

    // get /users/{nickName}
    @RequestMapping(value = "/{nickName}", method = RequestMethod.GET)
    public User getUserByNick(@PathVariable String nickName) throws UserNotFoundException {
        return this.userService.getUserByNickname(nickName);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    // try to parse the json object in the request body to a User instance
    public List<User> createUser(@RequestBody User user) throws Exception {
        return this.userService.createUser(user);
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
    public List<User> deleteUser(@PathVariable String userId) {
        return this.userService.deleteUser(userId);
    }

    @RequestMapping(value = "/update/{userId}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable String userId, @RequestBody User user) throws Exception {
        return this.userService.updateUser(userId, user);
    }

}
