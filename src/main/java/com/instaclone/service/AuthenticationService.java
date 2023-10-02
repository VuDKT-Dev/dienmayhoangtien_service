package com.instaclone.service;

import com.instaclone.common.Constants;
import com.instaclone.domain.Role;
import com.instaclone.domain.UserCustom;
import com.instaclone.exception.BusinessException;
import com.instaclone.repository.RoleRepository;
import com.instaclone.repository.UserCustomRepository;
import com.instaclone.security.auth.AuthenticationRequest;
import com.instaclone.security.auth.AuthenticationResponse;
import com.instaclone.security.auth.ChangePasswordRequest;
import com.instaclone.security.auth.RegisterRequest;
import com.instaclone.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserCustomRepository userCustomRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request){
        UserCustom user = new UserCustom();
        Role role = roleRepository.findByRoleName("USER").orElseThrow(() -> new BusinessException("Role not found"));
        if(userCustomRepository.findByEmail(request.getEmail()).isPresent()){
            throw new BusinessException("Email address already!!!");
        }
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(Constants.STATUS_INACTIVE_INT);
        user.setCreatedAt(new Date());
        user.setRoles(roleSet);
        user.setActive(Constants.STATUS_INACTIVE_INT);
        user.setActiveCode(genarateCode());
        userCustomRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setUsername(user.getUser());
        response.setToken(jwtToken);
        return response;
    };
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserCustom user =
                userCustomRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException(
                        "User not found"));
        if (user.getStatus() != Constants.STATUS_ACTIVE_INT){
            throw new BusinessException(404, "User not active");
        }
        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setUsername(user.getUser());
        response.setToken(jwtToken);
        return response;
    };





    public boolean activeAccount(String activeCode) {
        UserCustom user = userCustomRepository.findByActiveCode(activeCode).orElseThrow(() -> new BusinessException(
                "Wrong active code!!!"));
        user.setActiveCode(null);
        user.setStatus(Constants.STATUS_ACTIVE_INT);
        user.setActive(Constants.STATUS_ACTIVE_INT);
        userCustomRepository.save(user);
        return true;
    }

    public String genarateCode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public Boolean forgotPassword(String email) {
        UserCustom user =
                userCustomRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                        "User not found"));
        user.setForgotPassword(genarateCode());
        userCustomRepository.save(user);
        return true;
    }

    public AuthenticationResponse validForgotCode(String forgotCode) {
       UserCustom user =
               userCustomRepository.findByForgotPassword(forgotCode).orElseThrow(() -> new BusinessException("User " +
               "not found!!!"));
        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setUsername(user.getUser());
        response.setToken(jwtToken);
        return response;
    }

    public Boolean changePassword(ChangePasswordRequest request, Principal principal) {
        UserCustom user =
                userCustomRepository.findByEmail(principal.getName()).orElseThrow(() -> new BusinessException("Email " +
                        "not found!!!"));
        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new BusinessException("Confirm Password not same!!!");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userCustomRepository.save(user);
        return true;
    }

}
