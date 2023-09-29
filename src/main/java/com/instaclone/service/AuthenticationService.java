package com.instaclone.service;

import com.instaclone.domain.Role;
import com.instaclone.domain.UserCustom;
import com.instaclone.repository.RoleRepository;
import com.instaclone.repository.UserCustomRepository;
import com.instaclone.security.auth.AuthenticationRequest;
import com.instaclone.security.auth.AuthenticationResponse;
import com.instaclone.security.auth.RegisterRequest;
import com.instaclone.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
        Role role = roleRepository.findByRoleName("USER").orElseThrow(() -> new RuntimeException("Role not found"));
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(1);
        user.setCreatedAt(new Date());
        user.setRoles(roleSet);
        userCustomRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setUsername(user.getUsername());
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
        if (user.getStatus() != 1){
            throw new RuntimeException("User not active");
        }
        String jwtToken = jwtService.generateToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setUsername(user.getUserCustomName());
        response.setToken(jwtToken);
        return response;
    };
}
