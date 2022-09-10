package ibm.grupo2.helloBank.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Response<T> {

    private T data;
    private List<String> errors;

    public List<String> getErrors(){
        if (this.errors == null) {
            this.errors = new ArrayList<String>();
        }
        return errors;
    }

}