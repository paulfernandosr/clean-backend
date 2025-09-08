package com.paulfernandosr.account.infrastructure.expose;

import com.paulfernandosr.account.application.model.GetMovementsQuery;
import com.paulfernandosr.account.application.model.MovementInfoView;
import com.paulfernandosr.account.application.usecase.GetMovementsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportRestController {

    private final GetMovementsUseCase getMovementsUseCase;

    @GetMapping
    public ResponseEntity<List<MovementInfoView>> getReport(
            @RequestParam String customerIdentification,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        GetMovementsQuery getMovementsQuery = new GetMovementsQuery(customerIdentification, startDate, endDate);
        return ResponseEntity.ok(getMovementsUseCase.execute(getMovementsQuery));
    }

}
