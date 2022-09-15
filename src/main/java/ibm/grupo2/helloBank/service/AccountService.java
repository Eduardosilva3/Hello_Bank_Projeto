package ibm.grupo2.helloBank.service;

import ibm.grupo2.helloBank.Models.Account;
import ibm.grupo2.helloBank.dto.AccountDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Account save(Account a);

    List<Account> findAll();

    Optional<Account> findByNumber(String number);

    void deleteById(Long id);

    void deleteByNumber(String number);

    Optional<Account> findById(Long id);

    Account update(Long id, AccountDto accountDto);

    //Transfer Methods | transfer / withdraw / deposit

    Account updateForTransfers(Long id, Account accountDDto);

    Account transfer(String origin, String destiny, double value);

    Account withdraw(String origin, double value);

    Account deposit(String origin, double value);
}
