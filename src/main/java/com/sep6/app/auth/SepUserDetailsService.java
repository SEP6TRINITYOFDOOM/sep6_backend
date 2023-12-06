package com.sep6.app.auth;

import com.sep6.app.repository.user.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SepUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.sep6.app.model.User dbUser = userRepository.findByUsername(username);

        if(dbUser == null){
            throw new UsernameNotFoundException(username);
        }

        return User.withUsername(dbUser.getUsername())
                .password(dbUser.getPassword())
                .roles("USER")
                .build();
    }
}
