package com.habituau.HabitUAU_WEB.service;

import java.util.List;

import com.habituau.HabitUAU_WEB.model.entity.UserRanking;

public interface RankingService {

    List<UserRanking> getCommunityRankings();

    int getUserRankingPosition(Long cpf);

    List<UserRanking> getTopRankings(int limit);
}
