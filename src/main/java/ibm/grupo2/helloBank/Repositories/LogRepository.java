package ibm.grupo2.helloBank.Repositories;

import ibm.grupo2.helloBank.Models.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LogRepository extends JpaRepository<Log, UUID> {
}
