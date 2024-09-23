package com.habituau.HabitUAU_WEB.service;
import java.util.List;

import com.habituau.HabitUAU_WEB.model.entity.*;

public interface ProgressReportService {

    ProgressReport generateWeeklyProgressReport(Long cpf);

    ProgressReport generateMonthlyProgressReport(Long cpf);

    ProgressReport getChallengeHistory(Long cpf);
}
