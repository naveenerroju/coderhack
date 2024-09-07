package com.naveen.coderhack.utils;

import com.naveen.coderhack.entity.UserEntity;
import com.naveen.coderhack.model.User;
import org.modelmapper.ModelMapper;

public class CommonUtility {

    private CommonUtility(){

    }

    public static User mapUserEntityToModel(UserEntity entity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, User.class);
    }
}
