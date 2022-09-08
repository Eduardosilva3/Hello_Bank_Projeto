package ibm.grupo2.helloBank.service;

import ibm.grupo2.helloBank.Models.Account;

import java.util.Optional;

public interface AccountService {

    Account save(Account a);

    Optional<Account> findByNumber(String number);

    void deleteById(Long id);



}
