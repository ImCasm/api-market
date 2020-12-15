package com.casm.apimarket.web.controller;

import com.casm.apimarket.domain.dto.AuthRequest;
import com.casm.apimarket.domain.dto.AuthResponse;
import com.casm.apimarket.domain.service.ApiUserDetailService;
import com.casm.apimarket.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final ApiUserDetailService apiUserDetailService;
    private final JWTUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, ApiUserDetailService apiUserDetailService, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.apiUserDetailService = apiUserDetailService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping()
    public ResponseEntity<AuthResponse> createToken(@RequestBody AuthRequest request) {

        try {

            // Autenticar con los datos del usuario
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // Cargar los datos del usuario a spring security
            UserDetails userDetails = apiUserDetailService.loadUserByUsername(request.getUsername());

            // Crear Json Web Token (JSON)
            String jwt = jwtUtil.generateToken(userDetails);

            return new ResponseEntity<>(new AuthResponse(jwt), HttpStatus.OK);

        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }
}
