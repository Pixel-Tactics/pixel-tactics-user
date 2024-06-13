package com.pixel_tactics.pixel_tactics_user.entities;

import java.time.LocalDateTime;

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
@Table(name = "match_histories")
@Entity
public class MatchHistory {
    @Id
    @Column(nullable = false)
    private String id;
    
    @ManyToOne
    @JoinColumn(name="winner_id")
    private User winner;
    
    @ManyToOne
    @JoinColumn(name="loser_id")
    private User loser;
    
    @Column(nullable = false)
    private LocalDateTime time;
}
