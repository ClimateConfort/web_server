package com.climateconfort.web_server;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.climateconfort.web_server.domain.role.model.Role;
import com.climateconfort.web_server.domain.role.model.Role.RoleType;
import com.climateconfort.web_server.domain.user.model.User;
import com.climateconfort.web_server.domain.user.repository.UserRepository;
import com.climateconfort.web_server.security.CustomUserDetailsService;

@ExtendWith(MockitoExtension.class)
class UserDetailServiceTest {
       @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        Role rol = new Role();
        rol.setType(RoleType.User);
        user.setRole(rol); // Ajusta esto según tu clase Role
    }

    @Test
    void testLoadUserByUsername_UserFound() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsService.loadUserByUsername("test@example.com");

        assertEquals(user.getEmail(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        when(userRepository.findByEmail("invalid@example.com")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("invalid@example.com");
        });
    }
}
