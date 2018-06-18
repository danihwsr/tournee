package danihwsr.tournee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "user already exists")
public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(User user) {
        super(UserAlreadyExistsException.generateMessage(user));
    }

    private static String generateMessage(User user) {
        String msg = "A user with the name '%s' and the email '%s' already exists";
        return msg.format(user.getNickName(), user.geteMail());
    }
}
