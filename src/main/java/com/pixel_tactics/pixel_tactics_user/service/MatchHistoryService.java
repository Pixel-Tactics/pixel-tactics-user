package com.pixel_tactics.pixel_tactics_user.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pixel_tactics.pixel_tactics_user.entities.MatchHistory;
import com.pixel_tactics.pixel_tactics_user.entities.MatchReward;
import com.pixel_tactics.pixel_tactics_user.entities.User;
import com.pixel_tactics.pixel_tactics_user.repository.MatchHistoryRepository;
import com.pixel_tactics.pixel_tactics_user.repository.MatchRewardRepository;

@Service
public class MatchHistoryService {
    private final MatchHistoryRepository historyRepository;
    private final MatchRewardRepository rewardRepository;
    
    public MatchHistoryService(
        MatchHistoryRepository historyRepository,
        MatchRewardRepository rewardRepository
    ) {
        this.historyRepository = historyRepository;
        this.rewardRepository = rewardRepository;
    }
    
    public List<MatchHistory> getAllHistories(User user) {
        return historyRepository.findAllByWinnerOrLoserOrderByTime(user, user);
    }
    
    public MatchHistory getHistory(User user, String matchId) {
        Optional<MatchHistory> historyOpt = historyRepository.findById(matchId);
        if (!historyOpt.isPresent()) {
            throw new NoSuchElementException("Match history does not exist");
        }
        MatchHistory history = historyOpt.get();
        String curUsername = user.getUsername();
        String winnerUsername = history.getWinner().getUsername();
        String loserUsername = history.getLoser().getUsername();
        if (!winnerUsername.equals(curUsername) && !loserUsername.equals(curUsername)) {
            throw new NoSuchElementException("Match history does not exist");
        }
        return history;
    }
    
    public MatchReward getReward(User user, String matchId) {
        MatchHistory history = getHistory(user, matchId);
        Optional<MatchReward> rewardOpt = rewardRepository.findFirstByMatchHistoryAndUser(history, user);
        if (!rewardOpt.isPresent()) {
            return null;
        }
        return rewardOpt.get();
    }
}
