package com.example.registration;

import com.example.appuser.AppUser;
import com.example.appuser.AppUserRole;
import com.example.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    public String register(RegistrationRequest request) {
        boolean isValidEmail =
                emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalArgumentException(
                    String.format("Invalid email: %s", request.getEmail()));
        }
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER));
    }
}
