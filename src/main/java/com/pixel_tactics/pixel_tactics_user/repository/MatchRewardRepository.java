package com.pixel_tactics.pixel_tactics_user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pixel_tactics.pixel_tactics_user.entities.MatchHistory;
import com.pixel_tactics.pixel_tactics_user.entities.MatchReward;
import java.util.Optional;
import com.pixel_tactics.pixel_tactics_user.entities.User;



public interface MatchRewardRepository extends JpaRepository<MatchReward, String> {
    Optional<MatchReward> findFirstByMatchHistoryAndUser(MatchHistory matchHistory, User user);
}
