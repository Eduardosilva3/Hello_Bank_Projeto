package ibm.grupo2.helloBank.Controller;

import ibm.grupo2.helloBank.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("logs")
public class LogController {

    @Autowired
    LogService logService;



}
