package ibm.grupo2.helloBank.Repositories;

import ibm.grupo2.helloBank.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}