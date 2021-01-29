package payroll;

import java.util.regex.*;

public class Payroll
{
    private String name;
    private String id;
    private int hours;
    private double rate;

    public Payroll(){
        name = "";
        id = "";
        hours = 0;
        rate = 0.0;
    }

    public Payroll(String employeeName, String employeeID,
        int hoursWorked, double payRate){
        setName(employeeName);
        setID(employeeID);
        setHours(hoursWorked);
        setRate(payRate);
    }

    private boolean validateID(String employeeID){
        // matches beginning of LINE, followed by 2 word characters
        // followed by 4 digits, followed by EOL
        //Pattern idPattern = Pattern.compile("^\\w{2}\\d{4}$");

        // matches beginning of STRING, followed by 2 word characters
        // followed by 4 digits, followed by the end of the string
        // sans the final line terminator
        //Pattern idPattern = Pattern.compile("\\A\\w{2}\\d{4}\\Z");
        /*Matcher idMatcher = idPattern.matcher(employeeID);
        return idMatcher.find();*/
        return Pattern.matches("^\\w{2}\\d{4}$", employeeID);
    }

    public String getName(){
        return name;
    }

    public String getID(){
        return id;
    }

    public int getHours(){
        return hours;
    }

    public double getRate(){
        return rate;
    }

    public void setName(String employeeName){
        if (employeeName.isEmpty()){
            throw new EmptyEmployeeNameException("Employee name cannot be empty.");
        }
        name = employeeName;
    }

    public void setID(String employeeID){
        if (!validateID(employeeID)){
            throw new InvalidEmployeeIDException("Employee ID must be in the format LLNNNN (two letters followed by four digits).");
        }
        id = employeeID;
    }

    public void setHours(int hoursWorked){
        if (hoursWorked < 0 || hoursWorked > 84){
            throw new HoursWorkedOutOfBoundsException("Hours worked must be between 0 and 84 hours.");
        }
        hours = hoursWorked;
    }

    public void setRate(double payRate){
        if (payRate < 0.0 || payRate > 25.0){
            throw new PayrateOutOfBoundsException("Payrate must be between 0.00 and 25.00.");
        }
        rate = payRate;
    }
}
