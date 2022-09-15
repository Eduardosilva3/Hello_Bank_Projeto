package ibm.grupo2.helloBank.Controller;

import ibm.grupo2.helloBank.Models.Account;
import ibm.grupo2.helloBank.Models.Log;
import ibm.grupo2.helloBank.Response.Response;
import ibm.grupo2.helloBank.service.AccountService;
import ibm.grupo2.helloBank.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("logs")
public class LogController {

    @Autowired
    LogService logService;

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/{number}")
    public ResponseEntity<Response<Page<Log>>> findBetweenDates(@PathVariable("number") String number,
                                                                @RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
                                                                @RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate,
                                                                @RequestParam(name = "page", defaultValue = "0") int page) {

        Response<Page<Log>> response = new Response<Page<Log>>();
        Optional<Account> acc = accountService.findByNumber(number);

        if (!acc.isPresent()) {
            response.getErrors().add("Account not found, please check it and try again.");
            return ResponseEntity.badRequest().body(response);
        }

        Page<Log> logs = logService.findBetweenDates(number, startDate, endDate, page);
        response.setData(logs);
        return ResponseEntity.ok().body(response);
    }

}
