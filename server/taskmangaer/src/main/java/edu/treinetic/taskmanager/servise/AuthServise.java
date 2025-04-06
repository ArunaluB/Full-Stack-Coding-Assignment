package edu.treinetic.taskmanager.servise;

import edu.treinetic.taskmanager.document.User;
import edu.treinetic.taskmanager.dto.AuthenticationResponse;
import edu.treinetic.taskmanager.dto.RegisterRequest;

public interface AuthServise {
     AuthenticationResponse register(RegisterRequest request);
     AuthenticationResponse authenticate(String username, String password);
     User getUserByUsername(String username);
}