package student;

/** 
 * This is a static class (essentially functions) that will help you build objects from CSV strings.
 * These objects are then used in the rest of the program. Often these builders are associated
 * with the objects themselves and the concept of a factory, but we placed
 * them here to keep the code clean (and to help guide you).
 */
public final class Builder {
    
    private Builder() {
    }


     /**
     * Builds an employee object from a CSV string.
     * 
     * You may end up checking the type of employee (hourly or salary) by looking at the first
     * element of the CSV string. Then building an object specific to that type.
     * 
     * @param csv the CSV string
     * @return the employee object
     */
    public static IEmployee buildEmployeeFromCSV(String csv) {
        String[] fields = csv.split(",");

        if (fields.length != 7) {
            throw new IllegalArgumentException("Invalid Employee CSV format: " + csv);
        }

        String employeeType = fields[0].trim();
        String name = fields[1].trim();
        String id = fields[2].trim();
        double payRate = Double.parseDouble(fields[3].trim());
        double pretaxDeductions = Double.parseDouble(fields[4].trim());
        double ytdEarnings = Double.parseDouble(fields[5].trim());
        double ytdTaxesPaid = Double.parseDouble(fields[6].trim());

        switch (employeeType.toUpperCase()) {
            case "HOURLY":
                return new HourlyEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
            case "SALARY":
                return new SalaryEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
            default:
                throw new IllegalArgumentException("Unknown employee type: " + employeeType);
        }
    }



   /**
     * Converts a TimeCard from a CSV String.
     * 
     * @param csv csv string
     * @return a TimeCard object
     */
    public static ITimeCard buildTimeCardFromCSV(String csv) {
        String[] fields = csv.split(",");

        if (fields.length != 2) {
            throw new IllegalArgumentException("Invalid TimeCard CSV format: " + csv);
        }

        String employeeID = fields[0].trim();
        double hoursWorked = Double.parseDouble(fields[1].trim());

        return new TimeCard(employeeID, hoursWorked);
    }
}
