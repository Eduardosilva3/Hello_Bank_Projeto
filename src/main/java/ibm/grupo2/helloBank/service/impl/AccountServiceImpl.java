package ibm.grupo2.helloBank.service.impl;

import ibm.grupo2.helloBank.Models.Account;
import ibm.grupo2.helloBank.Repositories.AccountRepository;
import ibm.grupo2.helloBank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;


    @Override
    public Account save(Account a) {
        return accountRepository.save(a);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findByNumber(String number) {
        return accountRepository.findByNumber(number);
    }

    @Override
    public void deleteById(Long id) {

    }


}
