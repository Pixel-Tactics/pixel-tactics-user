package com.pixel_tactics.pixel_tactics_user.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pixel_tactics.pixel_tactics_user.dto.statistics.ResultDto;
import com.pixel_tactics.pixel_tactics_user.dto.statistics.UpdateStatisticsDto;
import com.pixel_tactics.pixel_tactics_user.entities.MatchHistory;
import com.pixel_tactics.pixel_tactics_user.entities.MatchReward;
import com.pixel_tactics.pixel_tactics_user.entities.User;
import com.pixel_tactics.pixel_tactics_user.exceptions.InvalidInputException;
import com.pixel_tactics.pixel_tactics_user.repository.MatchHistoryRepository;
import com.pixel_tactics.pixel_tactics_user.repository.MatchRewardRepository;
import com.pixel_tactics.pixel_tactics_user.repository.UserRepository;
import com.pixel_tactics.pixel_tactics_user.utils.MathUtils;

@Service
public class StatisticsService {
    private final UserRepository userRepository;
    private final MatchHistoryRepository historyRepository;
    private final MatchRewardRepository rewardRepository;
    
    private static final Integer RATING_CHANGE = 30;
    private static final Integer MIN_RATING = 1;
    private static final Integer MAX_RATING = 4000;
    
    public StatisticsService(
        UserRepository userRepository,
        MatchHistoryRepository historyRepository,
        MatchRewardRepository rewardRepository
    ) {
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
        this.rewardRepository = rewardRepository;
    }
    
    public ResultDto updateStatistics(UpdateStatisticsDto data) {
        Optional<User> userOpt1 = userRepository.findByUsername(data.getUsername1());
        Optional<User> userOpt2 = userRepository.findByUsername(data.getUsername2());
        if (!userOpt1.isPresent() || !userOpt2.isPresent()) {
            throw new InvalidInputException("User not found");
        }
        
        User user1 = userOpt1.get();
        User user2 = userOpt2.get();
        
        MatchHistory history = createHistory(data.getMatchId(), user1, user2);
        historyRepository.save(history);
        
        updateUser(user1, data.getIsUser1Win());
        updateUser(user2, !data.getIsUser1Win());
        
        MatchReward reward1 = createReward(
            history,
            user1,
            RATING_CHANGE,
            (data.getIsUser1Win().booleanValue() ? 1 : -1) * RATING_CHANGE
        );
        MatchReward reward2 = createReward(
            history,
            user2,
            RATING_CHANGE,
            (!data.getIsUser1Win().booleanValue() ? 1 : -1) * RATING_CHANGE
        );
        
        ArrayList<User> batchUsers = new ArrayList<>();
        batchUsers.add(user1);
        batchUsers.add(user2);
        ArrayList<MatchReward> batchRewards = new ArrayList<>();
        batchRewards.add(reward1);
        batchRewards.add(reward2);
        
        ResultDto dto = new ResultDto();
        dto.setBatchRewards(batchRewards);
        dto.setBatchUsers(batchUsers);
        return dto;
    }
    
    @Transactional
    public void batchSave(List<User> batchUsers, List<MatchReward> batchRewards) {
        userRepository.saveAll(batchUsers);
        rewardRepository.saveAll(batchRewards);
    }
    
    private MatchHistory createHistory(String matchId, User winner, User loser) {
        return MatchHistory.builder()
                    .id(matchId)
                    .winner(winner)
                    .loser(loser)
                    .time(LocalDateTime.now())
                    .build();
    }
    
    private MatchReward createReward(MatchHistory history, User user, int exp, int rating) {
        String rewardId = UUID.randomUUID().toString();
        return MatchReward.builder()
                    .id(rewardId)
                    .matchHistory(history)
                    .user(user)
                    .experience(exp)
                    .rating(rating)
                    .build();
    }
    
    private void updateUser(User user, boolean isWinning) {
        changeRating(user, isWinning);
        increaseExperience(user, RATING_CHANGE);
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
