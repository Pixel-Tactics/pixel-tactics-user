package com.pixel_tactics.pixel_tactics_user.dto.statistics;

import lombok.Data;

// Currently only User Data, but will include Match Data
@Data
public class UpdateStatisticsDto {
    private String sessionId;
    private String username1;
    private String username2;
    private Boolean isUser1Win;
}
