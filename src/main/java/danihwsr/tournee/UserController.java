package danihwsr.tournee;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List; // interface
import java.util.ArrayList; // implements List
import java.util.stream.Stream;

// due to springframework.boot.autoconfigure, this controller will be automatically registered
@RestController
// main route of this controller
@RequestMapping(value = "/users")
public class UserController {

    private List<User> users;

    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // subroute: get /users/all
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll() {
        // when declaring class attributes as private
        //+ make sure to implement public getters for the attribute
        // return value is automatically parsed to json ({class.Attribute: value, ...})
        users = this.userRepository.findAll();

        return users;
    }

    // get /users/{nickName}
    @RequestMapping(value = "/{nickName}", method = RequestMethod.GET)
    public User getUserByNick(@PathVariable String nickName) {

        for (User user : users) {
            if (user.getNickName().equals(nickName)) {
                return user;
            }
        }

        return new User();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    // try to parse the json object in the request body to a User instance
    public List<User> createUser(@RequestBody User user) throws UserAlreadyExistsException {
        return this.userService.createUser(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public List<User> deleteUser(@RequestBody User user) {
        int i = 0;
        for (User u: users) {
            if (u.getNickName().equals(user.getNickName())) {
                users.remove(i);
                break;
            }
            i++;
        }

        return users;
    }

}
