package com.naveen.coderhack.utils;

import com.naveen.coderhack.constants.Constants;

import java.util.ArrayList;
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

    /**
     * returns the list is mutable by creating a new ArrayList if it's immutable
     * @param badges list of badges
     * @param score score integer
     * @return mutable list of updated badges
     */
    public static List<String> updateBadges(List<String> badges, int score) {
        List<String> mutableBadges = new ArrayList<>(badges);

        String respectiveBadge = getBadge(score);
        if (!mutableBadges.contains(respectiveBadge) && mutableBadges.size() < 3) {
            mutableBadges.add(respectiveBadge);
        }
        return mutableBadges;
    }

}
