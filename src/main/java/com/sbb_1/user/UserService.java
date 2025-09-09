package com.sbb_1.user;

import com.sbb_1.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String name, String email, String password) {
        SiteUser user = new SiteUser();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        this.userRepository.save(user);
        return user;
    }

    //로그인한 사용자명 얻기
    public SiteUser getUser(String username){
        Optional<SiteUser> siteUser = this.userRepository.findByusername(username);

        if(siteUser.isPresent()){
            return siteUser.get();
        }
        else{
            throw new DataNotFoundException("siteuser not found");
        }
    }

}
