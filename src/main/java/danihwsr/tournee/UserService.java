package danihwsr.tournee;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> createUser(User user) throws UserAlreadyExistsException {
        if (this.userRepository.exist(user.getNickName(), user.geteMail()) != null) {
            String msg = String.format("User '%s' with the eMail '%s' already exists.", user.getNickName(), user.geteMail());
            throw new UserAlreadyExistsException(msg);
        }

        this.userRepository.save(user);

        return this.userRepository.findAll();
    }
}
