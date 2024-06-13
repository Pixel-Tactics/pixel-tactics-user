package com.pixel_tactics.pixel_tactics_user.dto.statistics;

import com.pixel_tactics.pixel_tactics_user.entities.MatchReward;

import lombok.Data;

@Data
public class RewardDto {
    private boolean isProcessed;
    private String matchId;
    private String username;
    private int experience;
    private int rating;
    
    public RewardDto(MatchReward reward) {
        this.isProcessed = true;
        this.matchId = reward.getMatchHistory().getId();
        this.username = reward.getUser().getUsername();
        this.experience = reward.getExperience();
        this.rating = reward.getRating();
    }
    
    public RewardDto() {
        this.isProcessed = false;
    }
}
