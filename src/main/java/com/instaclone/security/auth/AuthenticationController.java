package com.instaclone.security.auth;

import com.instaclone.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
    }

    @PostMapping("/{activeCode}")
    public ResponseEntity<String> activeAccount(@PathVariable String activeCode){
        if(authenticationService.activeAccount(activeCode)){
            return new ResponseEntity<>("User active successfull", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong!!!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/forgot-password")
    public ResponseEntity<Boolean> forgotPassword(@RequestParam("email") String email){
        return new ResponseEntity<>(authenticationService.forgotPassword(email), HttpStatus.OK);
    }

    @PostMapping("/forgot-password/{forgotCode}")
    public ResponseEntity<AuthenticationResponse> validForgotCode(@PathVariable String forgotCode){
        return new ResponseEntity<>(authenticationService.validForgotCode(forgotCode), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request, Principal principal){
        if(authenticationService.changePassword(request, principal)){
            return new ResponseEntity<>("Password change successfull", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong!!!", HttpStatus.BAD_REQUEST);
    }

}
