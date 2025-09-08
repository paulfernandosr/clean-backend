package com.paulfernandosr.account.application.usecase;

import com.paulfernandosr.account.application.model.GetMovementsQuery;
import com.paulfernandosr.account.application.model.MovementInfoView;

import java.util.List;

public interface GetMovementsUseCase {
    List<MovementInfoView> execute(GetMovementsQuery getMovementsQuery);
}
