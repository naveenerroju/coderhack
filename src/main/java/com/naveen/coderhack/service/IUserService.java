package com.naveen.coderhack.service;

import com.naveen.coderhack.model.User;

import java.util.List;

public interface IUserService {
    User userRegistration(String userId, String userName);
    List<User> getAllUsers();
    User getUser(String userId);
    User deleteUser(String userId);
    User updateScoreOfUser(String userId, int score);
}
