package ibm.grupo2.helloBank.controller;

import ibm.grupo2.helloBank.Repositories.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "tb_account")
public class AccountControllerTest {

    @Autowired
    private AccountRepository accountRepository;


}
