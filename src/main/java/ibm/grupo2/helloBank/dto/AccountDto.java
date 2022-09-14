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

    @NotNull(message = "Inform the agency.")
    String ag;

    @NotNull(message = "Inform the account number")
    String number;


    @NotNull(message = "Inform the account type.")
    String type;

    double balance = 0;

    @NotNull(message = "This account is active? (true / false)")
    boolean active = true;

    @NotNull(message = "Who is the customer? ")
    Customer owner_customer;

    @Column(nullable = false)
    LocalDateTime created_at;

    LocalDateTime updated_at;

}

