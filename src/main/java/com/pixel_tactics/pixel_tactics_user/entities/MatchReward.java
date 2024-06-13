package com.pixel_tactics.pixel_tactics_user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "match_rewards")
@Entity
public class MatchReward {
    @Id
    @Column(nullable = false)
    private String id;
    
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name="match_history_id", nullable = false)
    private MatchHistory matchHistory;
    
    @Column(nullable = false)
    private int experience;
    
    @Column(nullable = false)
    private int rating;
}
