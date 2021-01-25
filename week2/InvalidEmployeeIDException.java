package payroll;

public class InvalidEmployeeIDException extends RuntimeException{
    private String message;

    public InvalidEmployeeIDException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
