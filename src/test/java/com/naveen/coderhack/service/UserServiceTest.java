package com.naveen.coderhack.service;

import com.naveen.coderhack.constants.Constants;
import com.naveen.coderhack.entity.UserEntity;
import com.naveen.coderhack.exception.BusinessException;
import com.naveen.coderhack.model.Error;
import com.naveen.coderhack.model.User;
import com.naveen.coderhack.repository.UserRepository;
import com.naveen.coderhack.utils.BadgesUtility;
import com.naveen.coderhack.utils.CommonUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    private UserEntity userEntity;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample user data
        userEntity = new UserEntity("1", "JohnDoe", 50, List.of("MediumBadge"));
        user = CommonUtility.mapUserEntityToModel(userEntity);
    }

    @Test
    @DisplayName("Test successful user registration")
    void testUserRegistrationSuccess() {
        // Mock repository save behavior
        when(repository.save(any(UserEntity.class))).thenReturn(userEntity);

        // Call the service method
        User result = service.userRegistration("1", "JohnDoe");

        // Assertions
        assertNotNull(result);
        assertEquals("JohnDoe", result.getUsername());
        assertEquals(50, result.getScore());
        assertEquals(List.of("MediumBadge"), result.getBadges());
        verify(repository, times(1)).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("Test get all users success")
    void testGetAllUsersSuccess() {
        // Mock repository findAll behavior
        when(repository.findAll()).thenReturn(List.of(userEntity));

        // Call the service method
        List<User> result = service.getAllUsers();

        // Assertions
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("JohnDoe", result.get(0).getUsername());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test get user by ID - user found")
    void testGetUserByIdSuccess() {
        // Mock repository findById behavior
        when(repository.findById(anyString())).thenReturn(Optional.of(userEntity));

        // Call the service method
        User result = service.getUser("1");

        // Assertions
        assertNotNull(result);
        assertEquals("JohnDoe", result.getUsername());
        verify(repository, times(1)).findById("1");
    }

    @Test
    @DisplayName("Test get user by ID - user not found")
    void testGetUserByIdNotFound() {
        // Mock repository findById behavior to return empty
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        // Assertions for exception
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            service.getUser("2");
        });
        assertEquals(Error.USER_NOT_FOUND.name(), exception.getMessage());
        verify(repository, times(1)).findById("2");
    }

    @Test
    @DisplayName("Test delete user - user found")
    void testDeleteUserSuccess() {
        // Mock repository findById behavior
        when(repository.findById(anyString())).thenReturn(Optional.of(userEntity));
        doNothing().when(repository).delete(any(UserEntity.class));

        // Call the service method
        service.deleteUser("1");

        // Verify that delete was called
        verify(repository, times(1)).delete(any(UserEntity.class));
        verify(repository, times(1)).findById("1");
    }

    @Test
    @DisplayName("Test delete user - user not found")
    void testDeleteUserNotFound() {
        // Mock repository findById behavior to return empty
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        // Assertions for exception
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            service.deleteUser("2");
        });
        assertEquals(Error.USER_NOT_FOUND.name(), exception.getMessage());
        verify(repository, times(1)).findById("2");
    }

    @Test
    @DisplayName("Test update score for user - user found")
    void testUpdateUserScoreSuccess() {
        // Mock repository findById and save behavior
        when(repository.findById(anyString())).thenReturn(Optional.of(userEntity));
        when(repository.save(any(UserEntity.class))).thenReturn(userEntity);

        // Call the service method
        User result = service.updateScoreOfUser("1", 60);

        // Assertions
        assertNotNull(result);
        assertEquals(60, result.getScore());
        verify(repository, times(1)).findById("1");
        verify(repository, times(1)).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("Test update score for user - user not found")
    void testUpdateUserScoreNotFound() {
        // Mock repository findById behavior to return empty
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        // Assertions for exception
        BusinessException exception = assertThrows(BusinessException.class, () -> {
            service.updateScoreOfUser("2", 60);
        });
        assertEquals(Error.USER_NOT_FOUND.name(), exception.getMessage());
        verify(repository, times(1)).findById("2");
    }
}
