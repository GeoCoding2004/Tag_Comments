package com.georgehabib.Tags_comments.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.georgehabib.Tags_comments.Exception.AuthenticationFailedException;
import com.georgehabib.Tags_comments.Exception.UsernameAlreadyExistsException;
import com.georgehabib.Tags_comments.Users.LoginRequest;
import com.georgehabib.Tags_comments.Users.RegisterRequest;
import com.georgehabib.Tags_comments.Users.Role;
import com.georgehabib.Tags_comments.Users.User;
import com.georgehabib.Tags_comments.Users.UserRepository;

import lombok.RequiredArgsConstructor;

@Service        
@RequiredArgsConstructor   
public class AuthService {

    private final UserRepository userRepository;   
    private final PasswordEncoder passwordEncoder;  
    private final JwtService jwtService;           


        public String register(RegisterRequest request) {


        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username is already in use");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());


        User user = User.builder()
                .username(request.getUsername())
                .password(encodedPassword)
                .role(Role.valueOf(request.getRole().toUpperCase())) 
                .build();


        userRepository.save(user);


        return "User registered successfully. You can now log in.";
    }


    public AuthResponse authenticate(LoginRequest request){  

        User user  = userRepository.findByUsername(request.getUsername())
        .orElseThrow(() -> new AuthenticationFailedException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new AuthenticationFailedException("Invalid password");
        }
        

        boolean passwordMatches = passwordEncoder.matches(request.getPassword(), user.getPassword());


        if (!passwordMatches) {
            throw new RuntimeException("Invalid credentials");
        }


        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }


    
}
