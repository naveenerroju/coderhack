package com.naveen.coderhack.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naveen.coderhack.model.RegisterUser;
import com.naveen.coderhack.model.User;
import com.naveen.coderhack.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user1;
    private User user2;
    private RegisterUser registerUser;

    @BeforeEach
    void setUp() {
        user1 = new User("1", "JohnDoe", 20, List.of("CoderHac"));
        user2 = new User("2", "JaneDoe", 80, List.of("CoderHac", "Code Master"));
        registerUser = new RegisterUser("3", "NewUser");
    }

    @Test
    @DisplayName("GET /users - Success")
    void testGetUsersSuccess() throws Exception {
        List<User> userList = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value("1"))
                .andExpect(jsonPath("$[0].username").value("JohnDoe"))
                .andExpect(jsonPath("$[1].userId").value("2"))
                .andExpect(jsonPath("$[1].username").value("JaneDoe"));
    }

    @Test
    @DisplayName("GET /users/{userId} - Success")
    void testGetUserByIdSuccess() throws Exception {
        when(userService.getUser(anyString())).thenReturn(user1);

        mockMvc.perform(get("/users/{userId}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("1"))
                .andExpect(jsonPath("$.username").value("JohnDoe"));
    }

    @Test
    @DisplayName("POST /users - Success")
    void testRegisterUserSuccess() throws Exception {
        when(userService.userRegistration(anyString(), anyString())).thenReturn(user1);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value("1"))
                .andExpect(jsonPath("$.username").value("JohnDoe"));
    }

    @Test
    @DisplayName("PUT /users/{userId} - Success")
    void testUpdateUserScoreSuccess() throws Exception {
        user1.setScore(150);
        when(userService.updateScoreOfUser(anyString(), any(Integer.class))).thenReturn(user1);

        mockMvc.perform(put("/users/{userId}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("150"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("1"))
                .andExpect(jsonPath("$.score").value(150));
    }

    @Test
    @DisplayName("DELETE /users/{userId} - Success")
    void testDeleteUserSuccess() throws Exception {
        doNothing().when(userService).deleteUser(anyString());

        mockMvc.perform(delete("/users/{userId}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
