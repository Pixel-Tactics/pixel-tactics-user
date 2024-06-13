package com.pixel_tactics.pixel_tactics_user.dto.histories;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetHistoryDto {
    @NotBlank(message="match id is required")
    private String matchId;
}
