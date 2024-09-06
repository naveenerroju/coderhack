package com.naveen.coderhack.service;

import com.naveen.coderhack.exception.BusinessException;
import com.naveen.coderhack.model.User;

import java.util.List;

/**
 * Service interface for managing users.
 */
public interface IUserService {
    /**
     * Registers a new user with the given userId and userName.
     *
     * @param userId the ID of the user
     * @param userName the name of the user
     * @return the registered User
     */
    User userRegistration(String userId, String userName);
    /**
     * Retrieves all users and sorts them by score in descending order.
     *
     * @return a list of all users sorted by score
     */
    List<User> getAllUsers();
    /**
     * Retrieves a user by their userId.
     *
     * @param userId the ID of the user to retrieve
     * @return the User with the given userId
     * @throws BusinessException if the userId does not exist
     */
    User getUser(String userId) throws BusinessException;
    /**
     * Deletes a user by their userId.
     *
     * @param userId the ID of the user to delete
     * @throws BusinessException if the userId does not exist
     */
    void deleteUser(String userId) throws BusinessException;
    /**
     * Updates the score of a user with the given userId.
     *
     * @param userId the ID of the user to update
     * @param score the new score to set
     * @return the updated User
     * @throws BusinessException if the userId does not exist
     */
    User updateScoreOfUser(String userId, int score) throws BusinessException;
}
