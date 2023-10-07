package com.munsal.java21demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.munsal.java21demo.domain.entity.CustomerEntity;
import com.munsal.java21demo.domain.jsonView.ViewRole;
import com.munsal.java21demo.domain.model.CustomerDto;
import com.munsal.java21demo.domain.request.LoginRequest;
import com.munsal.java21demo.domain.response.JwtResponse;
import com.munsal.java21demo.service.CustomerService;
import com.munsal.java21demo.util.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping(AuthenticationController.BASE_PATH)
public class AuthenticationController {
    public static final String BASE_PATH = "auth";
    private static final String TAG = "Authentication Controllers";
    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;
    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;
    @PostMapping("/signIn")
    @Operation(tags = TAG, summary = "Authentication for Customer")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        CustomerEntity customerByUsername = customerService.getCustomerByUsername(loginRequest.getUsername());

        return ResponseEntity.ok(JwtResponse.builder()
                        .token(jwt)
                        .type("Bearer")
                        .id(customerByUsername.getId())
                        .username(customerByUsername.getUsername())
                        .email(customerByUsername.getEmail())
                        .roles(List.of("USER"))
                .build());
    }

    @PostMapping("/signUp")
    @Operation(tags = TAG, summary = "Add a new customer")
    @JsonView({ViewRole.AddRequest.class})
    public void add(@RequestBody @Valid CustomerDto customerDto) {
        customerDto.setPassword(encoder.encode(customerDto.getPassword()));
        customerService.addCustomer(customerDto);
    }
}
