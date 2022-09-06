package ibm.grupo2.helloBank.Repositories;

import ibm.grupo2.helloBank.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

}
