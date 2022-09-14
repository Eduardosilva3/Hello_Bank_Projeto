package ibm.grupo2.helloBank.Controller;

import ibm.grupo2.helloBank.Models.Account;
import ibm.grupo2.helloBank.Repositories.AccountRepository;
import ibm.grupo2.helloBank.Response.Response;
import ibm.grupo2.helloBank.dto.AccountDto;
import ibm.grupo2.helloBank.service.AccountService;
import net.bytebuddy.matcher.ElementMatchers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping
    public ResponseEntity<Response<AccountDto>> create(@Valid @RequestBody AccountDto accountDto, BindingResult result) {

        Response<AccountDto> response = new Response<AccountDto>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Account ac1 = new Account(accountDto.getId(), accountDto.getAg(), accountDto.getNumber(),
                accountDto.getType(), accountDto.getBalance(), accountDto.isActive(), accountDto.getOwner_customer(),
                accountDto.getCreated_at(), accountDto.getUpdated_at());


        Account ac2 = accountService.save(ac1);

        response.setData(accountDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<Account>> findAll() {

        List<Account> accounts = accountService.findAll();

        return ResponseEntity.ok().body(accounts);
    }

    @GetMapping(value = "/{number}")
    public ResponseEntity<Response<Account>> findByNumber(@PathVariable("number") String number) {

        Response<Account> response = new Response<>();

        Optional<Account> acc = accountService.findByNumber(number);

        if (!acc.isPresent()) {
            response.getErrors().add("There's no account with this number, try again.");
            return ResponseEntity.badRequest().body(response);
        }

        response.setData(acc.get());
        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable("id") Long id) {
        Response<String> response = new Response<String>();

        Optional<Account> acc = accountService.findById(id);
        if (!acc.isPresent()) {
            response.getErrors().add("The account id number" + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        accountService.deleteById(id);
        response.setData("The account with id " + id + " has been deleted.");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{number}")
    public ResponseEntity<Response<String>> delete(@PathVariable("number") String number) {
        Response<String> response = new Response<String>();

        Optional<Account> acc = accountService.findByNumber(number);
        if (!acc.isPresent()) {
            response.getErrors().add("The account number" + number + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        accountService.deleteById(acc.get().getId());
        response.setData("The account with number " + number + " has been deleted.");
        return ResponseEntity.ok().body(response);
    }



    @PutMapping
    public ResponseEntity<Response<AccountDto>> update(@Valid @RequestBody AccountDto dto, BindingResult result) {
        Response<AccountDto> response = new Response<AccountDto>();

        Optional<Account> acc = accountService.findById(dto.getId());

        if (!acc.isPresent()) {
            result.addError(new ObjectError("Account", "Account not found."));
        }
        if (result.hasErrors()) {
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

            return ResponseEntity.badRequest().body(response);
        }

        Account saved = accountService.update(dto.getId(), dto);
        response.setData(dto);
        System.out.println("This account is updated!");
        return ResponseEntity.ok().body(response);
    }


}

