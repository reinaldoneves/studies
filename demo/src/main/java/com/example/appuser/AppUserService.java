package com.example.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCriptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository.findByEmail(
                appUser.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException(String.format("Email %s already exists", appUser.getEmail()));
        }

        String encodedPassword =
                bCriptPasswordEncoder.encode(appUser.getPassword());

        appUserRepository.save(appUser);
        //TODO: Send confirmation token to user email
        return "it works";
    }
}
