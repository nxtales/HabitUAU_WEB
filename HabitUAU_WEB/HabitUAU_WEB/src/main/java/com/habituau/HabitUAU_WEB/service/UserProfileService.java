package com.habituau.HabitUAU_WEB.service;
import java.util.List;

import com.habituau.HabitUAU_WEB.model.entity.*;

public interface UserProfileService {

    void updateProfile(Long cpf, String preferencias, String metas);

    Cliente getProfile(Long cpf);

    String getUserHealthGoals(Long cpf);
}
