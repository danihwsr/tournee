package danihwsr.tournee;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TourneeController {

    // urlValues
    @RequestMapping("/")
    public String homeUrl(@RequestParam(value = "name", defaultValue = "Mundo!") String name) {
        return "Hello request variable, " + name + "!";
    }

    // pathValues
    @RequestMapping("/{name}")
    public String homePath(@PathVariable String name) {
        return "Hello path variable, " + name + "!";
    }
}

