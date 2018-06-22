package danihwsr.tournee.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        if ( !this.userRepository.getByNickname( name ).isPresent() ) {
            throw new UsernameNotFoundException(name);
        }

        User user = this.userRepository.getByNickname(name).get();
        return new UserDetailsImp(user);
    }

}
