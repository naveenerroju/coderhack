package com.naveen.coderhack.utils;

import com.naveen.coderhack.entity.UserEntity;
import com.naveen.coderhack.model.User;
import org.modelmapper.ModelMapper;

public class CommonUtility {

    private CommonUtility(){

    }

    public static String getBadge(int score){
        if(score>=0 && score <30){
            return "Code Ninja";
        } else if (score>=30 && score<60){
            return "Code Champ";
        } else {
            return "Code Master";
        }
    }

    public static User mapUserEntityToModel(UserEntity entity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, User.class);
    }
}
