package ibm.grupo2.helloBank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ibm.grupo2.helloBank.Models.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @Id
    @GeneratedValue
    Long id;

    @NotNull(message = "Informe a agência.")
    String ag;

    @NotNull(message = "Informe a conta.")
    String number;


    @NotNull(message = "Insira se é conta poupança ou corrente")
    String type;

    double balance = 0;

    @NotNull(message = "Se a conta está ativa ou não. (true / false)")
    boolean active = true;

    @NotNull(message = "Informe qual cliente está vinculado a esta conta.")
    Customer owner_customer;

    @Column(nullable = false)
    LocalDateTime created_at;

    LocalDateTime updated_at;

}

