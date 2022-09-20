package ibm.grupo2.helloBank.service.impl;

import ibm.grupo2.helloBank.Models.Account;
import ibm.grupo2.helloBank.Models.Customer;
import ibm.grupo2.helloBank.Models.Log;
import ibm.grupo2.helloBank.Repositories.LogRepository;
import ibm.grupo2.helloBank.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogRepository logRepository;

    @Override
    public Log generate(Account origin, Account destiny, Customer customer) {

        Log log = new Log();
        log.setOrigin(origin.getNumber());
        log.setOriginCpf(customer.getCpf());
        log.setDestiny(destiny.getNumber());

        return log;
    }

    @Override
    public Log save(Log log) {
        return logRepository.save(log);
    }

    @Override
    public List<Log> findBetweenDates(String number, Date date1, Date date2) {
                return logRepository.findAllByOriginAndDateGreaterThanEqualAndDateLessThanEqual(number,date1,date2);
    }

    @Override
    public List<Log> findAll() {
        return logRepository.findAll();
    }

}
