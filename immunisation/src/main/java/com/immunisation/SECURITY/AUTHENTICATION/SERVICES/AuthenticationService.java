package com.immunisation.SECURITY.AUTHENTICATION.SERVICES;
import com.immunisation.SECURITY.AUTHENTICATION.ENTITY.AuthenticationRequest;
import com.immunisation.SECURITY.AUTHENTICATION.ENTITY.AuthenticationResponse;
import com.immunisation.SECURITY.AUTHENTICATION.ENTITY.RegisterRequest;
import com.immunisation.SECURITY.CONFIG.JwtService;
import com.immunisation.immunisationcode.ENTITIES.Guardian;
import com.immunisation.immunisationcode.ENTITIES.Role;
import com.immunisation.immunisationcode.REPOSITORIES.GuardianRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final GuardianRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()

                )
        );
        var user  = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().
                token(jwtToken)
                .guardianId(user.getId())
                .build();

    }
    public AuthenticationResponse register(RegisterRequest request){
        var user = Guardian.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().
                token(jwtToken)
                .guardianId(user.getId())
                .build();

    }
}