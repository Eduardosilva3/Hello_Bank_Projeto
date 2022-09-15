package ibm.grupo2.helloBank.service.impl;

import ibm.grupo2.helloBank.Models.Account;
import ibm.grupo2.helloBank.Models.Log;
import ibm.grupo2.helloBank.Repositories.AccountRepository;
import ibm.grupo2.helloBank.Repositories.LogRepository;
import ibm.grupo2.helloBank.dto.AccountDto;
import ibm.grupo2.helloBank.exception.ObjectNotFoundException;
import ibm.grupo2.helloBank.exception.UnsufficientBalanceException;
import ibm.grupo2.helloBank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LogServiceImpl logService;


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
        accountRepository.deleteById(id);
    }

    @Override
    public void deleteByNumber(String number) {
        Account acc = accountRepository.findByNumber(number).get();
        accountRepository.deleteById(acc.getId());
    }


    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account update(Long id, AccountDto dto) {

        Optional<Account> acc1 = accountRepository.findById(id);

        acc1.get().setAg(dto.getAg());
        acc1.get().setNumber(dto.getNumber());
        acc1.get().setType(dto.getType());
        acc1.get().setBalance(dto.getBalance());
        acc1.get().setUpdated_at(LocalDateTime.now());

        accountRepository.deleteById(acc1.get().getId());
        return accountRepository.save(acc1.get());
    }

    @Override
    public Account updateForTransfers(Long id, Account acc) {

        Optional<Account> acc1 = accountRepository.findById(id);

        acc1.get().setAg(acc.getAg());
        acc1.get().setNumber(acc.getNumber());
        acc1.get().setType(acc.getType());
        acc1.get().setBalance(acc.getBalance());
        acc1.get().setUpdated_at(LocalDateTime.now());


        accountRepository.deleteById(acc1.get().getId());
        return accountRepository.save(acc1.get());
    }


    @Override
    public Account transfer(String origin, String destiny, double value) {

        try {
            if (value < 0)
                value = value*-1;
            Optional<Account> acc1 = accountRepository.findByNumber(origin);
            Optional<Account> acc2 = accountRepository.findByNumber(destiny);
            if (acc1.get().getBalance() - value >= 0) {
                acc1.get().setBalance(acc1.get().getBalance() - value);
                acc1.get().setUpdated_at(LocalDateTime.now());
                acc2.get().setBalance(acc2.get().getBalance() + value);
                acc2.get().setUpdated_at(LocalDateTime.now());


                accountRepository.deleteById(acc1.get().getId());
                accountRepository.deleteById(acc2.get().getId());
                return (Account) accountRepository.saveAll(Arrays.asList(acc1.get(), acc2.get()));
            } else {
                throw new UnsufficientBalanceException("There's no balance to do this transfer");
            }
        } catch (EntityNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }

    @Override
    public Account withdraw(String origin, double value) {

        try {
            if (value < 0){
                value = value*-1;
            }
            Optional<Account> acc1 = accountRepository.findByNumber(origin);
            if (acc1.get().getBalance() - value >= 0) {
                acc1.get().setBalance(acc1.get().getBalance() - value);
                acc1.get().setUpdated_at(LocalDateTime.now());
                accountRepository.deleteById(acc1.get().getId());
                return accountRepository.save(acc1.get());
            } else {
                throw new UnsufficientBalanceException("There's no balance to do this transfer");
            }
        } catch (EntityNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }

    @Override
    public Account deposit(String origin, double value) {
        try {
            if (value < 0){
                value = value*-1;
            }
            Optional<Account> acc1 = accountRepository.findByNumber(origin);
            acc1.get().setBalance(acc1.get().getBalance() - value);
            acc1.get().setUpdated_at(LocalDateTime.now());
            accountRepository.deleteById(acc1.get().getId());
            return accountRepository.save(acc1.get());
        } catch (EntityNotFoundException e) {
            throw new ObjectNotFoundException(e.getMessage());
        }
    }


}
