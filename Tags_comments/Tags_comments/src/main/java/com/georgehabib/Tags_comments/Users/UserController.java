package com.georgehabib.Tags_comments.Users;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> getCurrentUser(Authentication authentication) {
        Map<String, String> response = new HashMap<>();
        response.put("username", authentication.getName()); // Gets username from Spring Security context
        return ResponseEntity.ok(response);
    }
}
