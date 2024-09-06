package com.naveen.coderhack.service;

import com.naveen.coderhack.entity.UserEntity;
import com.naveen.coderhack.exception.BusinessException;
import com.naveen.coderhack.model.User;
import com.naveen.coderhack.repository.UserRepository;
import com.naveen.coderhack.utils.CommonUtility;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public User userRegistration(String userId, String userName) {
        String[] badges = new String[]{CommonUtility.getBadge(0)};
        UserEntity response = repository.save(new UserEntity(userId, userName, 0, badges));
        return CommonUtility.mapUserEntityToModel(response);
    }

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

    @Override
    public com.naveen.coderhack.model.User getUser(String userId) {
        Optional<UserEntity> userEntity = repository.findById(userId);
        if(userEntity.isPresent()){
            return CommonUtility.mapUserEntityToModel(userEntity.get());
        } else {
            throw new BusinessException("UserId doesn't exist");
        }
    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> userEntity = repository.findById(userId);
        if(userEntity.isPresent()){
            repository.delete(userEntity.get());
        } else {
            throw new BusinessException("UserId doesn't exist");
        }
    }

    @Override
    public com.naveen.coderhack.model.User updateScoreOfUser(String userId, int score) {
        Optional<UserEntity> userEntityOpt = repository.findById(userId);
        if(userEntityOpt.isPresent()){
            UserEntity entity = userEntityOpt.get();
            entity.setScore(score);
            UserEntity response = repository.save(entity);
            return CommonUtility.mapUserEntityToModel(response);
        } else {
            throw new BusinessException("UserId doesn't exist");
        }
    }
}
