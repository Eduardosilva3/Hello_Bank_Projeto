package ibm.grupo2.helloBank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotNull(message = "Informe o nome correto.")
    String name;

    @NotNull(message = "Informe o cpf correto.")
    String cpf;

    @NotNull(message = "Informe o email.")
    String email;

    @NotNull(message = "Por favor, informe a idade.")
    int age;

    @NotNull(message = "Insira o telefone.")
    String phone;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    @Column(nullable = false)
    LocalDateTime created_at;

    LocalDateTime updated_at;

    @Column(nullable = false)
    boolean card;

    
}

