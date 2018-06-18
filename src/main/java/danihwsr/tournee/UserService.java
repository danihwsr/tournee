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
            throw new UserAlreadyExistsException(user);
        }

        this.userRepository.save(user);

        return this.userRepository.findAll();
    }
}
