package com.paulfernandosr.account.infrastructure.expose;

import com.paulfernandosr.account.application.model.AccountInfoView;
import com.paulfernandosr.account.application.model.CreateAccountCommand;
import com.paulfernandosr.account.application.model.GetAccountQuery;
import com.paulfernandosr.account.application.model.UpdateAccountCommand;
import com.paulfernandosr.account.application.usecase.CreateAccountUseCase;
import com.paulfernandosr.account.application.usecase.GetAccountUseCase;
import com.paulfernandosr.account.application.usecase.GetAllAccountsUseCase;
import com.paulfernandosr.account.application.usecase.UpdateAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountRestController {

    private final GetAccountUseCase getAccountUseCase;
    private final GetAllAccountsUseCase getAllAccountsUseCase;
    private final CreateAccountUseCase createAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountInfoView> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(getAccountUseCase.execute(new GetAccountQuery(accountNumber)));
    }

    @GetMapping
    public ResponseEntity<List<AccountInfoView>> getAllAccounts() {
        return ResponseEntity.ok(getAllAccountsUseCase.execute());
    }

    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody CreateAccountCommand createAccountCommand) {
        createAccountUseCase.execute(createAccountCommand);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{accountNumber}")
                .buildAndExpand(createAccountCommand.accountNumber())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{accountNumber}")
    public ResponseEntity<Void> updateAccount(@PathVariable String accountNumber,
                                               @RequestBody UpdateAccountCommand.AccountUpdateData accountUpdateData) {
        updateAccountUseCase.execute(new UpdateAccountCommand(accountNumber, accountUpdateData));
        return ResponseEntity.noContent().build();
    }
}
