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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("logs")
public class LogController {

    @Autowired
    LogService logService;

    @Autowired
    AccountService accountService;

    @GetMapping(value = "/log/{number}")
    public ResponseEntity<Response<List<Log>>> findBetweenDates(@PathVariable("number") String number,
                                                                @RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
                                                                @RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate) {

        Response<List<Log>> response = new Response<>();
        Optional<Account> acc = accountService.findByNumber(number);

        if (!acc.isPresent()) {
            response.getErrors().add("Account not found, please check it and try again.");
            return ResponseEntity.badRequest().body(response);
        }

        List<Log> logs = logService.findBetweenDates(number, startDate, endDate);
        response.setData(logs);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/log")
    public ResponseEntity<List<Log>> findAll() {
        List<Log> logs = logService.findAll();

        return ResponseEntity.ok().body(logs);
    }
}
