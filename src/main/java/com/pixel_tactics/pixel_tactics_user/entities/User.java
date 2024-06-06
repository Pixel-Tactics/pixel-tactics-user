package com.pixel_tactics.pixel_tactics_user.entities;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "users")
@Entity
public class User implements UserDetails {
    @Id
    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private Integer level = 1;
    
    @Column(nullable = false)
    private Integer experience = 0;
    
    @Column(nullable = false)
    private Integer rating = 1000;
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    
    public void setExperience(int experience) {
        this.experience = experience;
        updateLevel();
    }
    
    private void updateLevel() {
        int newLevel = 1;
        int cur = 50;
        int tempExp = this.experience;
        while (tempExp >= cur) {
            tempExp -= cur;
            newLevel += 1;
            cur += 50;
        }
        this.level = newLevel;
    }
}
