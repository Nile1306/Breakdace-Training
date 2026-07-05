package de.htw_berlin.mytodolist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    private AppUserRepository userRepository;
    private AuthController authController;

    @BeforeEach
    void setUp() {
        userRepository = mock(AppUserRepository.class);
        authController = new AuthController(userRepository);
    }

    @Test
    void registerSavesNewUserWhenEmailIsFree() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("tommy");
        request.setEmail("tommy@example.com");
        request.setPassword("supersecret");

        when(userRepository.existsByEmail("tommy@example.com")).thenReturn(false);

        AuthResponse response = authController.register(request);

        assertEquals("tommy@example.com", response.getEmail());
        assertNotNull(response.getToken());
        verify(userRepository, times(1)).save(any(AppUser.class));
    }

    @Test
    void registerFailsWhenEmailAlreadyTaken() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("taken@example.com");
        request.setPassword("whatever");

        when(userRepository.existsByEmail("taken@example.com")).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> authController.register(request));
        verify(userRepository, never()).save(any());
    }

    @Test
    void loginWorksWithCorrectPassword() {
        String hash = new BCryptPasswordEncoder().encode("mypw123");
        AppUser user = new AppUser("tommy", "tommy@example.com", hash);

        when(userRepository.findByEmail("tommy@example.com")).thenReturn(Optional.of(user));

        LoginRequest request = new LoginRequest();
        request.setEmail("tommy@example.com");
        request.setPassword("mypw123");

        AuthResponse response = authController.login(request);

        assertEquals("tommy@example.com", response.getEmail());
    }

    @Test
    void loginFailsWithWrongPassword() {
        String hash = new BCryptPasswordEncoder().encode("correctpw");
        AppUser user = new AppUser("tommy", "tommy@example.com", hash);

        when(userRepository.findByEmail("tommy@example.com")).thenReturn(Optional.of(user));

        LoginRequest request = new LoginRequest();
        request.setEmail("tommy@example.com");
        request.setPassword("totallywrong");

        assertThrows(ResponseStatusException.class, () -> authController.login(request));
    }

    @Test
    void loginFailsWhenUserDoesNotExist() {
        when(userRepository.findByEmail("ghost@example.com")).thenReturn(Optional.empty());

        LoginRequest request = new LoginRequest();
        request.setEmail("ghost@example.com");
        request.setPassword("irrelevant");

        assertThrows(ResponseStatusException.class, () -> authController.login(request));
    }
}
