package com.mercado.stock.controller;

import com.mercado.stock.security.CustomUserDetails;
import com.mercado.stock.security.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // permite conexión desde Angular
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request, HttpServletResponse response) {
        String email = request.get("email");
        String password = request.get("password");

        authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        String username = null;
        if (userDetails instanceof CustomUserDetails) {
            username = ((CustomUserDetails) userDetails).getNombreUsuario();
        } else {
            // Manejar el caso donde userDetails no es CustomUserDetails si es posible
            username = userDetails.getUsername(); // Usar el username por defecto de UserDetails si CustomUserDetails no se aplica
        }
        
        String token = jwtUtil.generateToken(email, username);

        return Map.of("token", token);
    }
}
