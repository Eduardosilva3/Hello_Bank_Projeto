package ibm.grupo2.helloBank.Repositories;

import ibm.grupo2.helloBank.Models.Customer;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

        List<Optional<Customer>> findAllByName(String name);
        Optional<Customer> findByName(String name);
        Customer findByCpf(String cpf);
}