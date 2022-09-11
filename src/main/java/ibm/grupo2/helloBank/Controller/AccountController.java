package ibm.grupo2.helloBank.Controller;

import ibm.grupo2.helloBank.Models.Account;
import ibm.grupo2.helloBank.Repositories.AccountRepository;
import ibm.grupo2.helloBank.Response.Response;
import ibm.grupo2.helloBank.dto.AccountDto;
import ibm.grupo2.helloBank.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "account")
public class AccountController {

    @Autowired
    private AccountService accountService;

//    @Autowired
//    private ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity<Response<AccountDto>> create(@Valid @RequestBody AccountDto accountDto, BindingResult result) {

        Response<AccountDto> response = new Response<AccountDto>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Account ac1 = new Account(accountDto.getId(), accountDto.getAg(), accountDto.getNumber(),
                accountDto.getType(), accountDto.getBalance(), accountDto.isActive(), accountDto.getOwner_customer(),
                accountDto.getCreated_at(),accountDto.getUpdated_at());


        Account ac2 = accountService.save(ac1);

        response.setData(accountDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<Account>> findAll(){

        List<Response<AccountDto>> response = new ArrayList<Response<AccountDto>>();

        List<Account> accounts = accountService.findAll();

        return ResponseEntity.ok().body(accounts);
    }



}

