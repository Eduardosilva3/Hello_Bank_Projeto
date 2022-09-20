package ibm.grupo2.helloBank.Controller;

import ibm.grupo2.helloBank.Models.Account;
import ibm.grupo2.helloBank.Models.Customer;
import ibm.grupo2.helloBank.Models.Log;

import ibm.grupo2.helloBank.Response.Response;
import ibm.grupo2.helloBank.dto.AccountDto;
import ibm.grupo2.helloBank.service.AccountService;
import ibm.grupo2.helloBank.service.CustomerService;
import ibm.grupo2.helloBank.service.LogService;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Api(value = "animal")
@RestController
@RequestMapping(value = "account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    CustomerService customerService;

    @Autowired
    LogService logService;


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

        Log log = logService.generate(ac1,ac1,ac1.getOwner_customer());
        log.setValue(ac1.getBalance());
        log.setLogType("create");
        logService.save(log);

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

        Log log = logService.generate(acc.get(),acc.get(),acc.get().getOwner_customer());
        log.setValue(0);
        log.setLogType("delete");
        logService.save(log);

        accountService.deleteById(id);
        response.setData("The account with id " + id + " has been deleted.");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{number}")
    public ResponseEntity<Response<String>> deleteNumber(@PathVariable("number") String number) {
        Response<String> response = new Response<String>();

        Optional<Account> acc = accountService.findByNumber(number);
        if (!acc.isPresent()) {
            response.getErrors().add("The account number" + number + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Log log = logService.generate(acc.get(),acc.get(),acc.get().getOwner_customer());
        log.setValue(0);
        log.setLogType("delete");
        logService.save(log);

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

        Log log = logService.generate(acc.get(),acc.get(),acc.get().getOwner_customer());
        log.setValue(0);
        log.setLogType("update");
        logService.save(log);

        Account saved = accountService.update(dto.getId(), dto);
        response.setData(dto);
        System.out.println("This account is updated!");
        return ResponseEntity.ok().body(response);
    }

//    //transferêcia
    @PutMapping(value = "/transfer/{origin}")
    public ResponseEntity<List<Response<Account>>> transfer(@Valid @PathVariable ("origin") String origin,  String destiny,
                                                             double value, BindingResult result) {
        Response<Account> response1 = new Response<>();
        Response<Account> response2 = new Response<>();

        List<Response<Account>> responseList = new ArrayList<>();

        Optional<Account> acc1 = accountService.findByNumber(origin);
        Optional<Account> acc2 = accountService.findByNumber(destiny);


        if (!acc1.isPresent() || !acc2.isPresent()) {
            result.addError(new ObjectError("Account", "Account not found."));
        }
        if (result.hasErrors()) {
            result.getAllErrors().forEach(r -> response1.getErrors().add(r.getDefaultMessage()));

            responseList.add(response1);
            return ResponseEntity.badRequest().body(responseList);
        }

        Log log = logService.generate(acc1.get(),acc2.get(),acc1.get().getOwner_customer());
        log.setValue(value);
        log.setLogType("transfer");
        logService.save(log);

        accountService.transfer(origin, destiny, value);
        responseList.addAll(Arrays.asList(response1, response2));
        System.out.println("Transfer completed!");
        return ResponseEntity.ok().body(responseList);
    }


    @PutMapping(value = "/withdraw/{origin}")
    public ResponseEntity<Response<Account>> withdraw(@PathVariable ("origin") String origin,
                                                       double value, BindingResult result) {
        Response<Account> response = new Response<Account>();

        Optional<Account> acc1 = accountService.findByNumber(origin);

        if (!acc1.isPresent()) {
            result.addError(new ObjectError("Account", "Account not found."));
        }
        if (result.hasErrors()) {
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

            return ResponseEntity.badRequest().body(response);
        }

        Log log = logService.generate(acc1.get(),acc1.get(),acc1.get().getOwner_customer());
        log.setValue(value);
        log.setLogType("withdraw");
        logService.save(log);

        Account accFinal = accountService.withdraw(origin, value);
        response.setData(accFinal);
        System.out.println("Withdraw completed! Your total balance is: $ " + accFinal.getBalance() + ".");
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/deposit/{origin}")
    public ResponseEntity<Response<Account>> deposit(@PathVariable ("origin") String origin,
                                                      double value) {
        Response<Account> response = new Response<Account>();

        Optional<Account> acc1 = accountService.findByNumber(origin);

        /*if (!acc1.isPresent()) {
            result.addError(new ObjectError("Account", "Account not found."));
        }
        if (result.hasErrors()) {
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

            return ResponseEntity.badRequest().body(response);
        }*/

        Optional<Customer> customer = customerService.findById(acc1.get().getOwner_customer().getId());
        String textSms = String.format("Attetion, you have a new message from HelloBank! /n You received R$ %f in your account: %s",value,origin);
        AWSSNSController sms = new AWSSNSController();

        sms.pubTextSMS(textSms, customer.get().getPhone());


        Log log = logService.generate(acc1.get(),acc1.get(),acc1.get().getOwner_customer());
        log.setValue(value);
        log.setLogType("deposit");
        logService.save(log);

        Account accFinal = accountService.deposit(origin, value);
        response.setData(accFinal);
        System.out.println("Deposit completed! Your total balance is: $ " + accFinal.getBalance() + ".");
        return ResponseEntity.ok().body(response);
    }

}

