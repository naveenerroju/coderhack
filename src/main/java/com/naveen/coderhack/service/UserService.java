package com.naveen.coderhack.service;

import com.naveen.coderhack.constants.Constants;
import com.naveen.coderhack.entity.UserEntity;
import com.naveen.coderhack.exception.BusinessException;
import com.naveen.coderhack.model.User;
import com.naveen.coderhack.repository.UserRepository;
import com.naveen.coderhack.utils.BadgesUtility;
import com.naveen.coderhack.utils.CommonUtility;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing users.
 */
@Service
public class UserService implements IUserService {

    private final UserRepository repository;

    /**
     * Constructs a new UserService with the given UserRepository.
     *
     * @param repository the UserRepository to use for data access
     */
    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    /**
     * Registers a new user with the given userId and userName.
     *
     * @param userId the ID of the user
     * @param userName the name of the user
     * @return the registered User
     */
    @Override
    public User userRegistration(String userId, String userName) {
        List<String> badges = List.of(BadgesUtility.getBadge(Constants.DEFAULT_SCORE));
        UserEntity response = repository.save(new UserEntity(userId, userName, Constants.DEFAULT_SCORE, badges));
        return CommonUtility.mapUserEntityToModel(response);
    }

    /**
     * Retrieves all users and sorts them by score in descending order.
     *
     * @return a list of all users sorted by score
     */
    @Override
    public List<com.naveen.coderhack.model.User> getAllUsers() {
        List<UserEntity> userEntityList = repository.findAll();

        List<User> users = new ArrayList<>();
        for (UserEntity userEntity: userEntityList){
            users.add(CommonUtility.mapUserEntityToModel(userEntity));
        }
        Collections.sort(users);
        return users;
    }

    /**
     * Retrieves a user by their userId.
     *
     * @param userId the ID of the user to retrieve
     * @return the User with the given userId
     * @throws BusinessException if the userId does not exist
     */
    @Override
    public com.naveen.coderhack.model.User getUser(String userId) {
        Optional<UserEntity> userEntity = repository.findById(userId);
        if(userEntity.isPresent()){
            return CommonUtility.mapUserEntityToModel(userEntity.get());
        } else {
            throw new BusinessException("UserId doesn't exist");
        }
    }

    /**
     * Deletes a user by their userId.
     *
     * @param userId the ID of the user to delete
     * @throws BusinessException if the userId does not exist
     */
    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> userEntity = repository.findById(userId);
        if(userEntity.isPresent()){
            repository.delete(userEntity.get());
        } else {
            throw new BusinessException("UserId doesn't exist");
        }
    }

    /**
     * Updates the score of a user with the given userId.
     *
     * @param userId the ID of the user to update
     * @param score the new score to set
     * @return the updated User
     * @throws BusinessException if the userId does not exist
     */
    @Override
    public com.naveen.coderhack.model.User updateScoreOfUser(String userId, int score) {
        Optional<UserEntity> userEntityOpt = repository.findById(userId);
        if(userEntityOpt.isPresent()){

            UserEntity entity = userEntityOpt.get();
            //updating score and badges accordingly
            entity.setScore(score);
            entity.setBadges(BadgesUtility.updateBadges(entity.getBadges(), score));

            UserEntity response = repository.save(entity);
            return CommonUtility.mapUserEntityToModel(response);
        } else {
            throw new BusinessException("UserId doesn't exist");
        }
    }
}
