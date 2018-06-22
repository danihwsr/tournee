package danihwsr.tournee.web;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImp implements UserDetails {
    private User user;

    public UserDetailsImp(User user) {
        this.user = user;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> ga = new ArrayList<>();

        for ( Roles r : this.user.getRoles() ) {
            ga.add(new SimpleGrantedAuthority( r.toString() ) );
        }

        return ga;
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
