package payroll;

public class EmptyEmployeeNameException extends RuntimeException{
    private String message;

    public EmptyEmployeeNameException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
