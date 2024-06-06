package com.pixel_tactics.pixel_tactics_user.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.pixel_tactics.pixel_tactics_user.dto.statistics.UpdateStatisticsDto;
import com.pixel_tactics.pixel_tactics_user.service.StatisticsService;

@Component
public class MatchResultConsumer {
    private final StatisticsService statisticsService;
    
    public MatchResultConsumer(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
    
    @RabbitListener(queues = "matches.statistics")
    public void receiveMessage(UpdateStatisticsDto payload) {
        statisticsService.updateStatistics(payload);
    }
}
