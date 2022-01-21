package com.montagsmaler.backend.game.gamestatistics;

import com.montagsmaler.backend.userManagement.UserDetailServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class GameStatisticService {
    @Resource
    private GameStatisticRepository gameStatisticRepository;

    @Resource
    private UserDetailServiceImpl userDetailService;

    public List<GameStatisticEntity> getGameStatisticsForUser(String user) {
        UUID userID = userDetailService.getUserIDByName(user);
        return gameStatisticRepository.getByUserIDOrderByPlayerGameNumberAsc(userID);
    }
}
