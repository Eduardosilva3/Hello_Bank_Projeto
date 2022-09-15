package ibm.grupo2.helloBank.service.impl;

import ibm.grupo2.helloBank.Controller.CustomerController;
import ibm.grupo2.helloBank.Models.Customer;
import ibm.grupo2.helloBank.Repositories.CustomerRepository;
import ibm.grupo2.helloBank.dto.CustomerDto;
import ibm.grupo2.helloBank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public List<Optional<Customer>> findByAllByName(String name) {
        try {
            return customerRepository.findAllByName(name);
        }catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public Optional<Customer> findById(Long id) {
        try {
            return customerRepository.findById(id);
        }catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public List<Customer> findAll() {
        System.out.println("Here is your list.");
        if (customerRepository.findAll().isEmpty())
            System.out.println("This customer list is empty.");
        return customerRepository.findAll();
    }

    @Override
    public Customer create(Customer customer) {
        System.out.println("This customer is saved now.");
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Long id, CustomerDto clientDto) {

        Optional<Customer> c1 = customerRepository.findById(id);
        CustomerDto dto = new CustomerDto();
        c1.get().setName(dto.getName());
        c1.get().setCpf(dto.getCpf());
        c1.get().setEmail(dto.getEmail());
        c1.get().setAge(dto.getAge());
        c1.get().setPhone(dto.getPhone());
        c1.get().setPassword(dto.getPassword());
        c1.get().setUpdated_at(LocalDateTime.now());

        System.out.println("This customer was updated with success!");
        customerRepository.deleteById(c1.get().getId());
        return customerRepository.save(c1.get());
    }

    @Override
    public void delete(Long id) {
        try {
            Optional <Customer> c1 = customerRepository.findById(id);
            System.out.println("This customer was deleted: " + c1.toString());
            customerRepository.deleteById(id);
        }catch (EntityNotFoundException e){
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public Customer findByCpf(String cpf) {
        return customerRepository.findByCpf(cpf);
    }
}
