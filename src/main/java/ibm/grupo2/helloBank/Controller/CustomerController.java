package ibm.grupo2.helloBank.Controller;


import ibm.grupo2.helloBank.Models.Customer;
import ibm.grupo2.helloBank.dto.CustomerDto;
import ibm.grupo2.helloBank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CustomerService iClientService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok().body(iClientService.findAll());
    }

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody @Valid CustomerDto clientDto){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(iClientService.create(convertDtoToEntity(clientDto)).getId()).toUri();
        AWSSNSController.addSubscriptionToSNSTopic(clientDto.getEmail());
        AWSSNSController.subTextSNS(clientDto.getPhone());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> create(@PathVariable Long id){
        Optional<Customer> client = iClientService.findById(id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.get().getId()).toUri();


        return ResponseEntity.created(uri).body(convertEntityToDto(client.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@PathVariable Long id, @RequestBody @Valid CustomerDto clientDto){
        return ResponseEntity.ok().body(convertEntityToDto(iClientService.update(id, clientDto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        iClientService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/cpf")
    public ResponseEntity<Customer> findByCpf(@RequestBody @Valid CustomerDto clientDto){
        convertDtoToEntity(clientDto);
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

    public CustomerDto convertEntityToDto(Customer c){
        CustomerDto dto = new CustomerDto();
        dto.setName(c.getName());
        dto.setCpf(c.getCpf());
        dto.setEmail(c.getEmail());
        dto.setAge(c.getAge());
        dto.setPhone(c.getPhone());
        dto.setPassword(c.getPassword());
        dto.setCreated_at(c.getCreated_at());
        dto.setUpdated_at(c.getUpdated_at());

        return dto;
    }
    public Customer convertDtoToEntity(CustomerDto dto){
        Customer c = new Customer();
        c.setName(dto.getName());
        c.setCpf(dto.getCpf());
        c.setEmail(dto.getEmail());
        c.setAge(dto.getAge());
        c.setPhone(dto.getPhone());
        c.setPassword(dto.getPassword());
        c.setCreated_at(dto.getCreated_at());
        c.setUpdated_at(dto.getUpdated_at());

        return c;
    }


}