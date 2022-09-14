package ibm.grupo2.helloBank.exception;

public class UnsufficientBalanceException extends RuntimeException {
    public UnsufficientBalanceException(String message){
        super(message);
    }
}

