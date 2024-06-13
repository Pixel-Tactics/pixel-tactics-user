package com.pixel_tactics.pixel_tactics_user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pixel_tactics.pixel_tactics_user.dto.statistics.RewardDto;
import com.pixel_tactics.pixel_tactics_user.entities.MatchHistory;
import com.pixel_tactics.pixel_tactics_user.entities.MatchReward;
import com.pixel_tactics.pixel_tactics_user.entities.User;
import com.pixel_tactics.pixel_tactics_user.service.MatchHistoryService;

@RestController
@RequestMapping("match-histories")
public class MatchHistoryController {
    private final MatchHistoryService service;
    
    public MatchHistoryController(
        MatchHistoryService service
    ) {
        this.service = service;
    }
    
    @GetMapping("/")
    public ResponseEntity<List<MatchHistory>> getAllHistories() {
        User user = getCurrentUser();
        return ResponseEntity.ok(service.getAllHistories(user));
    }
    
    @GetMapping("/{matchId}")
    public ResponseEntity<MatchHistory> getHistory(@PathVariable String matchId) {
        User user = getCurrentUser();
        return ResponseEntity.ok(service.getHistory(user, matchId));
    }
    
    @GetMapping("/{matchId}/poll")
    public ResponseEntity<RewardDto> pollRewardProcess(@PathVariable String matchId) {
        User user = getCurrentUser();
        MatchReward reward = service.getReward(user, matchId);
        if (reward == null) {
            return ResponseEntity.status(202).body(new RewardDto());
        }
        return ResponseEntity.ok(new RewardDto(reward));
    }
    
    private User getCurrentUser() {
        return (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
    }
}
