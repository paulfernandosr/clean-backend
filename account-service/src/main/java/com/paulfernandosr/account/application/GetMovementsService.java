package com.paulfernandosr.account.application;

import com.paulfernandosr.account.application.mapper.MovementInfoMapper;
import com.paulfernandosr.account.application.model.GetMovementsQuery;
import com.paulfernandosr.account.application.model.MovementInfoView;
import com.paulfernandosr.account.application.provider.CustomerManager;
import com.paulfernandosr.account.application.provider.MovementManager;
import com.paulfernandosr.account.application.usecase.GetMovementsUseCase;
import com.paulfernandosr.account.domain.*;
import com.paulfernandosr.account.domain.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMovementsService implements GetMovementsUseCase {
    private final MovementManager movementManager;
    private final CustomerManager customerManager;
    private final MovementInfoMapper movementInfoMapper;

    @Override
    public List<MovementInfoView> execute(GetMovementsQuery getMovementsQuery) {
        CustomerIdentification customerIdentification = new CustomerIdentification(getMovementsQuery.customerIdentification());

        Customer customer = customerManager.findCustomer(customerIdentification)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        return movementManager.findMovements(customerIdentification,
                        getMovementsQuery.startDate().atStartOfDay(),
                        getMovementsQuery.endDate().atTime(LocalTime.MAX))
                .stream()
                .peek(movement -> movement.getAccount().setCustomer(customer))
                .map(movementInfoMapper::toMovementInfoView)
                .toList();
    }
}
