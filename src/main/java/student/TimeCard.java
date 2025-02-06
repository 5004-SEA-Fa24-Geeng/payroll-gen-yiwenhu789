package student;

public class TimeCard implements ITimeCard {
    private String employeeID;
    private double hoursWorked;

    /**
     * Constructor for TimeCard.
     *
     * @param employeeID The ID of the employee
     * @param hoursWorked The number of hours worked
     */
    public TimeCard(String employeeID, double hoursWorked) {
        this.employeeID = employeeID;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String getEmployeeID() {
        return employeeID;
    }

    @Override
    public double getHoursWorked() {
        return hoursWorked;
    }

    @Override
    public String toString() {
        return "TimeCard{employeeID='" + employeeID + "', hoursWorked=" + hoursWorked + "}";
    }
}

