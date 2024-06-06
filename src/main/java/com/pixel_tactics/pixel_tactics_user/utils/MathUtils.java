package com.pixel_tactics.pixel_tactics_user.utils;

public class MathUtils {
    private MathUtils() {}
    
    public static int clamp(int value, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be not greater than max");
        }
        
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        } else {
            return value;
        }
    }
}
