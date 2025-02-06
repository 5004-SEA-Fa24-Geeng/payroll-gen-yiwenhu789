package student;

public class HourlyEmployee implements IEmployee {
    private String name;
    private String id;
    private double payRate;
    private double ytdEarnings;
    private double ytdTaxesPaid;
    private double pretaxDeductions;

    public HourlyEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getID() { return id; }

    @Override
    public double getPayRate() { return payRate; }

    @Override
    public String getEmployeeType() { return "Hourly"; }

    @Override
    public double getYTDEarnings() { return ytdEarnings; }

    @Override
    public double getYTDTaxesPaid() { return ytdTaxesPaid; }

    @Override
    public double getPretaxDeductions() { return pretaxDeductions; }

    @Override
    public IPayStub runPayroll(double hoursWorked) {
        // Implementation for generating pay stub will be added later
        return null;
    }

    @Override
    public String toCSV() {
        return String.format("Hourly,%s,%s,%.2f,%.2f,%.2f,%.2f",
                name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }
}
