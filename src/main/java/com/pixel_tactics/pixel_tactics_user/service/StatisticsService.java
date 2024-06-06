package com.pixel_tactics.pixel_tactics_user.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pixel_tactics.pixel_tactics_user.dto.statistics.UpdateStatisticsDto;
import com.pixel_tactics.pixel_tactics_user.entities.User;
import com.pixel_tactics.pixel_tactics_user.exceptions.InvalidInputException;
import com.pixel_tactics.pixel_tactics_user.repository.UserRepository;
import com.pixel_tactics.pixel_tactics_user.utils.MathUtils;

@Service
public class StatisticsService {
    private final UserRepository userRepository;
    private static final Integer RATING_CHANGE = 30;
    private static final Integer MIN_RATING = 1;
    private static final Integer MAX_RATING = 4000;
    
    public StatisticsService(
        UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }
    
    public void updateStatistics(UpdateStatisticsDto data) {
        Optional<User> userOpt1 = userRepository.findByUsername(data.getUsername1());
        Optional<User> userOpt2 = userRepository.findByUsername(data.getUsername2());
        if (!userOpt1.isPresent() || !userOpt2.isPresent()) {
            throw new InvalidInputException("User not found");
        }
        
        User user1 = updateUser(userOpt1.get(), data.getIsUser1Win());
        User user2 = updateUser(userOpt2.get(), !data.getIsUser1Win());
        
        ArrayList<User> batch = new ArrayList<>();
        batch.add(user1);
        batch.add(user2);
        userRepository.saveAll(batch);
    }
    
    private User updateUser(User user, boolean isWinning) {
        changeRating(user, isWinning);
        increaseExperience(user, RATING_CHANGE);
        return user;
    }
    
    private void changeRating(User user, boolean isWinning) {
        int change = (isWinning ? 1 : -1) * RATING_CHANGE;
        user.setRating(MathUtils.clamp(user.getRating() + change, MIN_RATING, MAX_RATING));
    }
    
    private void increaseExperience(User user, int expAdd) {
        if (expAdd < 0) {
            throw new IllegalArgumentException("Experience cannot be decreased");
        }
        int newExp = user.getExperience() + expAdd;
        user.setExperience(newExp);
    }
}
