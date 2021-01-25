package payroll;

public class PayrateOutOfBoundsException extends RuntimeException{
    private String message;

    public PayrateOutOfBoundsException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
