package ibm.grupo2.helloBank.Controller;

import ibm.grupo2.helloBank.Models.Account;
import ibm.grupo2.helloBank.Repositories.AccountRepository;
import ibm.grupo2.helloBank.Response.Response;
import ibm.grupo2.helloBank.dto.AccountDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(name = "account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    public ResponseEntity<Response<AccountDto>> create(@Valid @RequestBody AccountDto accountDto, BindingResult result) {

        final ModelMapper modelMapper = null;

        Response<AccountDto> response = new Response<AccountDto>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Account account = modelMapper.map(accountDto, Account.class);
        response.setData(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);


    }
}

