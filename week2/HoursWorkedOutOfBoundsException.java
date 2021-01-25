package payroll;

public class HoursWorkedOutOfBoundsException extends RuntimeException{
    private String message;

    public HoursWorkedOutOfBoundsException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
