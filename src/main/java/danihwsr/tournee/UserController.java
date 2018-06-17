package danihwsr.tournee;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List; // interface
import java.util.ArrayList; // implements List

// due to springframework.boot.autoconfigure, this controller will be automatically registered
@RestController
// main route of this controller
@RequestMapping(value = "/users")
public class UserController {

    private List<User> users;

    public UserController() {
        users = new ArrayList<>();

        users.add(new User("daniel", "knauser", "dooniel", "dooniel@web.de"));
        users.add(new User("max", "mustermann", "xXx_max_xXx", "max@gmail.com"));
        users.add(new User("max", "musterfrau", "69_max_69", "maxi@hotmail.de"));
        users.add(new User("peter", "meier", "the_meiernator", "meier@peter.org"));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll() {
        // when declaring class attributes as private
        //+ make sure to implement public getters for the attribute
        // return value is automatically parsed to json ({class.Attribute: value, ...})
        return users;
    }

}
