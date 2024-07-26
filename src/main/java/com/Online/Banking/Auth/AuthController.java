package com.Online.Banking.Auth;

import com.Online.Banking.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public void register(
            @RequestBody RegisterResponse registerResponse
    ){
        authService.register(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ){
        return  ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            jwtService.blacklistToken(token);
            SecurityContextHolder.clearContext();
            return ResponseEntity.ok("Logged out successfully");
        }
        return ResponseEntity.badRequest().body("Invalid token");
    }

}
