package com.Online.Banking.Auth;

import com.Online.Banking.User.Role;
import com.Online.Banking.User.User;
import com.Online.Banking.User.userRepo;
import com.Online.Banking.config.JwtService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final userRepo repo;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public void register(RegisterResponse registerResponse){
       var user = User.builder()
               .firstname(registerResponse.getFirstName())
               .lastname(registerResponse.getLastName())
               .email(registerResponse.getEmail())
               .password(passwordEncoder.encode(registerResponse.getPassword()))
               .role(Role.USER)
               .build();
       repo.save(user);
    }

    public AuthResponse authenticate(AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserEmail(),
                        request.getPassword()
                )
        );
       var user = repo.findByEmail(request.getUserEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

}
