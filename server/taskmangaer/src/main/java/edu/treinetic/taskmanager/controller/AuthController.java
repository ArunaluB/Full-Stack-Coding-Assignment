package edu.treinetic.taskmanager.controller;
import edu.treinetic.taskmanager.dto.AuthenticationRequest;
import edu.treinetic.taskmanager.dto.AuthenticationResponse;
import edu.treinetic.taskmanager.dto.RegisterRequest;
import edu.treinetic.taskmanager.servise.AuthServise;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServise authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request.getUsername(), request.getPassword()));
    }

}