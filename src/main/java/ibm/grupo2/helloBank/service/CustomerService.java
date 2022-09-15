package ibm.grupo2.helloBank.service;

import ibm.grupo2.helloBank.Models.Customer;
import ibm.grupo2.helloBank.dto.CustomerDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Optional<Customer>> findByAllByName(String name);

    Optional<Customer> findById(Long id);
    List<Customer> findAll();
    Customer create(Customer customer);
    Customer update(Long id, CustomerDto CustomerDto);
    void delete(Long id);

    Customer findByCpf(String cpf);
}
