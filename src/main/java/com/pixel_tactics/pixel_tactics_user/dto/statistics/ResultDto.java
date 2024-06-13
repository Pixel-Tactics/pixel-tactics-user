package com.pixel_tactics.pixel_tactics_user.dto.statistics;

import java.util.List;

import com.pixel_tactics.pixel_tactics_user.entities.MatchReward;
import com.pixel_tactics.pixel_tactics_user.entities.User;

import lombok.Data;

@Data
public class ResultDto {
    private List<User> batchUsers;
    private List<MatchReward> batchRewards;
}
