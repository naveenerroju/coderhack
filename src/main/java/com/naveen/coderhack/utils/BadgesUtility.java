package com.naveen.coderhack.utils;

import com.naveen.coderhack.constants.Constants;

import java.util.List;

public class BadgesUtility {

    private BadgesUtility(){}


    public static String getBadge(int score){
        if(score>=0 && score <30){
            return Constants.LOW_BADGE;
        } else if (score>=30 && score<60){
            return Constants.MEDIUM_BADGE;
        } else {
            return Constants.HIGH_BADGE;
        }
    }

    public static List<String> updateBadges(List<String> badges, int score){
        String respectiveBadge = getBadge(score);
        if(!badges.contains(respectiveBadge) && badges.size()<3){
            badges.add(respectiveBadge);
        }
        return badges;
    }

}
