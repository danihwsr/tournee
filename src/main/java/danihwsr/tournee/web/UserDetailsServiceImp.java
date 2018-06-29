package danihwsr.tournee.web;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        if ( !this.userRepository.getByNickname( name ).isPresent() ) {
            throw new UsernameNotFoundException(name);
        }

        User user = this.userRepository.getByNickname(name).get();
        return new UserDetailsImp(user);
    }

}
