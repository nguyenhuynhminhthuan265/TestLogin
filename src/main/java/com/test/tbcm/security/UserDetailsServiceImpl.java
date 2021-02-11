package com.test.tbcm.security;


import com.test.tbus.model.entity.User;
import com.test.tbus.service.impl.UserServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceImpl userServiceImpl;


    @SneakyThrows
    public UserDetails loadUserByUsername(String email) {

        Optional<User> user = userServiceImpl.findByEmail(email);
        if (!user.isPresent()) {
            return null;
        }
        System.out.println("===================>>>>>>>>>>>>>>>>>>>> loadUserByUsername");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.get().getRole().getName()));
        CustomUserDetails customUserDetails = new CustomUserDetails(user.get().getEmail(), "", authorities);
        return customUserDetails;
    }

}
