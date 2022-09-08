package ibm.grupo2.helloBank.Repositories;

import ibm.grupo2.helloBank.Models.Customer;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.UUID;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


}