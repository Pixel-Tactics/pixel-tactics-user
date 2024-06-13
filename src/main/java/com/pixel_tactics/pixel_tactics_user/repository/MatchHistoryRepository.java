package com.pixel_tactics.pixel_tactics_user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pixel_tactics.pixel_tactics_user.entities.MatchHistory;
import com.pixel_tactics.pixel_tactics_user.entities.User;

public interface MatchHistoryRepository extends JpaRepository<MatchHistory, String> {
    List<MatchHistory> findAllByWinnerOrLoserOrderByTime(User winner, User loser);
}
