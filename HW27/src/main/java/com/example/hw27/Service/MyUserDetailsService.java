package com.example.hw27.Service;

import com.example.hw27.ApiException.ApiException;
import com.example.hw27.Model.MyUser;
import com.example.hw27.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final MyUserRepository myUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser=myUserRepository.findMyUserByUsername( username);
        if(myUser==null){
            throw new ApiException("Wrong Username or password");
        }

        return myUser;
    }
}
