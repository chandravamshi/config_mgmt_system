package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.security.JwtTokenUtil;
import com.example.userservice.service.UserService;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/users")
@CrossOrigin // Allow CORS requests
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    /*
     * @PostMapping("/register")
     * public ResponseEntity<User> registerUser(@RequestBody User user) {
     * User createdUser = userService.registerUser(user);
     * return ResponseEntity.ok(createdUser);
     * }
     */

    /**
     * Endpoint for user registration.
     *
     * @param user the user object containing username, password, and role
     * @return ResponseEntity with user details or error message
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        // Check if the user already exists
        if (userService.userExists(user.getUsername())) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Username already exists.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        User registeredUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    // Endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        // If authentication is successful, generate the JWT token
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Cast principal to UserDetails, not AuthRequest
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // final String token = jwtTokenUtil.generateToken((AuthRequest)
        // authentication.getPrincipal());
        final String token = jwtTokenUtil.generateToken(userDetails); // Pass UserDetails to generateToken

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
