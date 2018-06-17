package danihwsr.tournee;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @Controller + @ResponseBody
// return value of a function that is mapped via RestController is the response body
// or in other words it returns an object not a view
@RestController
public class TourneeController {

    // default http method is GET
    // urlValues
    @RequestMapping(value = "/")
    public String homeUrl(@RequestParam(value = "name", defaultValue = "Mundo!") String name) {
        return "Hello request variable, " + name + "!";
    }

    // pathValues
    @RequestMapping(value = "/{name}")
    public String homePath(@PathVariable String name) {
        return "Hello path variable, " + name + "!";
    }
}

