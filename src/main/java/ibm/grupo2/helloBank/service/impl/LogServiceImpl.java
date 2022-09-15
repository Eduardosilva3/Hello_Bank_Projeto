package ibm.grupo2.helloBank.service.impl;

import ibm.grupo2.helloBank.Models.Account;
import ibm.grupo2.helloBank.Models.Customer;
import ibm.grupo2.helloBank.Models.Log;
import ibm.grupo2.helloBank.Repositories.LogRepository;
import ibm.grupo2.helloBank.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public Page<Log> findBetweenDates(String number, Date date1, Date date2, int page) {

        PageRequest pg = PageRequest.of(page, 10);

        return logRepository.findByOriginAndDateGreaterThanEqualAndDateLessThanEqual(number,date1,date2, pg);
    }

}
