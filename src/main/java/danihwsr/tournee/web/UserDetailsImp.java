package danihwsr.tournee.web;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImp implements UserDetails {
    private User user;

    public UserDetailsImp(User user) {
        this.user = user;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {

         return Arrays.stream(this.user.getRoles())
            .map( role -> new SimpleGrantedAuthority( role.toString()) )
            .collect( Collectors.toList() );

    }

    @Override
    public boolean isEnabled() {
        return !this.user.isDisabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.user.isDisabled();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.user.isDisabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.user.isDisabled();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getNickname();
    }

}
