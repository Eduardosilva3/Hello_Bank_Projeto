package ibm.grupo2.helloBank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
    public Long id;
    @NotNull
    public String name;
    @NotNull
    public String password;
}
