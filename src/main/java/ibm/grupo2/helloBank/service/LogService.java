package ibm.grupo2.helloBank.service;

import ibm.grupo2.helloBank.Models.Account;
import ibm.grupo2.helloBank.Models.Customer;
import ibm.grupo2.helloBank.Models.Log;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface LogService {

    Log generate(Account acc,Account destiny ,Customer customer);
    Log save(Log log);

//
    List<Log> findBetweenDates(String number, Date date1, Date date2);
}
