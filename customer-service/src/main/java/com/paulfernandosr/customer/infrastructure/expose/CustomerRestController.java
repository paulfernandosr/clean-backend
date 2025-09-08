package com.paulfernandosr.customer.infrastructure.expose;

import com.paulfernandosr.customer.application.model.*;
import com.paulfernandosr.customer.application.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerRestController {

    private final GetCustomerUseCase getCustomerUseCase;
    private final GetAllCustomersUseCase getAllCustomersUseCase;
    private final CreateCustomerUseCase createCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    @GetMapping("/{identification}")
    public ResponseEntity<CustomerInfoView> getCustomer(@PathVariable String identification) {
        return ResponseEntity.ok(getCustomerUseCase.execute(new GetCustomerQuery(identification)));
    }

    @GetMapping
    public ResponseEntity<List<CustomerInfoView>> getAllCustomers() {
        return ResponseEntity.ok(getAllCustomersUseCase.execute());
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CreateCustomerCommand createCustomerCommand) {
        createCustomerUseCase.execute(createCustomerCommand);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{identification}")
                .buildAndExpand(createCustomerCommand.identification())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{identification}")
    public ResponseEntity<Void> updateCustomer(@PathVariable String identification,
                                               @RequestBody UpdateCustomerCommand.CustomerUpdateData customerUpdateData) {
        updateCustomerUseCase.execute(new UpdateCustomerCommand(identification, customerUpdateData));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{identification}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String identification) {
        deleteCustomerUseCase.execute(new DeleteCustomerCommand(identification));
        return ResponseEntity.noContent().build();
    }
}
