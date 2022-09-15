package ibm.grupo2.helloBank.Controller;


import ibm.grupo2.helloBank.Models.Customer;
import ibm.grupo2.helloBank.dto.CustomerDto;
import ibm.grupo2.helloBank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
@Log4j2
public class CustomerController {

    private final CustomerService iClientService;
    private final ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok().body(iClientService.findAll());
    }

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody @Valid CustomerDto clientDto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(iClientService.create(modelMapper.map(clientDto, Customer.class)).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> create(@PathVariable Long id){
        Optional<Customer> client = iClientService.findById(id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.get().getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(client, CustomerDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable Long id, @RequestBody @Valid CustomerDto clientDto){
        return ResponseEntity.ok().body(modelMapper.map(iClientService.update(id, clientDto), CustomerDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        iClientService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/cpf")
    public ResponseEntity<Customer> findByCpf(@RequestBody @Valid CustomerDto clientDto){
        modelMapper.map(clientDto, Customer.class);
        return ResponseEntity.ok().body(iClientService.findByCpf(clientDto.getCpf()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String messageError = error.getDefaultMessage();
            errors.put(fieldName, messageError);
        });

        return errors;
    }
}