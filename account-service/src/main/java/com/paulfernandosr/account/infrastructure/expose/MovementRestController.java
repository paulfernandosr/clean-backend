package com.paulfernandosr.account.infrastructure.expose;

import com.paulfernandosr.account.application.model.*;
import com.paulfernandosr.account.application.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movements")
public class MovementRestController {

    private final CreateMovementUseCase createMovementUseCase;

    @PostMapping
    public ResponseEntity<Void> createMovement(@RequestBody CreateMovementCommand createMovementCommand) {
        createMovementUseCase.execute(createMovementCommand);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
