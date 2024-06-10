package com.climateconfort.web_server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.climateconfort.web_server.domain.role.model.Role;
import com.climateconfort.web_server.domain.role.model.Role.RoleType;
import com.climateconfort.web_server.domain.role.repository.RoleRepository;
import com.climateconfort.web_server.domain.user.dto.UserDto;
import com.climateconfort.web_server.domain.user.model.User;
import com.climateconfort.web_server.domain.user.repository.UserRepository;
import com.climateconfort.web_server.domain.user.service.impl.UserServiceImpl;

class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockrRoleRepository;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    void testSaveUser() {
        UserDto userDto = new UserDto();
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");
        userDto.setRoleType(RoleType.User);

        when(mockrRoleRepository.findByType(RoleType.User)).thenReturn(Optional.empty());
        when(mockUserRepository.save(any(User.class))).thenReturn(new User());
        when(mockPasswordEncoder.encode("password")).thenReturn("encodedPassword");

        userService.saveUser(userDto);

        verify(mockrRoleRepository, times(1)).findByType(RoleType.User);

        verify(mockPasswordEncoder, times(1)).encode("password");
    }

    @Test
    void testFindUserDtoByEmailNotFound() {
        when(mockUserRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        Optional<UserDto> result = userService.findUserDtoByEmail("test@example.com");
        assertFalse(result.isPresent());
        verify(mockUserRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testFindAllUsers() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setEmail("user1@example.com");
        Role role1 = new Role();
        role1.setType(RoleType.User);
        user1.setRole(role1);
        userList.add(user1);

        User user2 = new User();
        user2.setEmail("user2@example.com");
        Role role2 = new Role();
        role2.setType(RoleType.Administrator);
        user2.setRole(role2);
        userList.add(user2);

        when(mockUserRepository.findAll()).thenReturn(userList);

        List<UserDto> result = userService.findAllUsers();

        assertEquals(2, result.size());
        assertEquals("user1@example.com", result.get(0).getEmail());
        assertEquals(RoleType.User, result.get(0).getRoleType());
        assertEquals("user2@example.com", result.get(1).getEmail());
        assertEquals(RoleType.Administrator, result.get(1).getRoleType());

        verify(mockUserRepository, times(1)).findAll();
    }

    @Test
    void testFindUserByEmailFound() {
        User user = new User();
        user.setEmail("test@example.com");

        when(mockUserRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findUserByEmail("test@example.com");

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());

        verify(mockUserRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testFindUserByEmailNotFound() {
        when(mockUserRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        Optional<User> result = userService.findUserByEmail("test@example.com");
        assertFalse(result.isPresent());
        verify(mockUserRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);
        verify(mockUserRepository, times(1)).deleteById(1L);
    }

}
