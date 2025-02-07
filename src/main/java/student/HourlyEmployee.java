package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HourlyEmployee implements IEmployee {
    /** The name of the employee */
    private String name;

    /** The unique employee ID */
    private String id;

    /** The hourly pay rate of the employee */
    private double payRate;

    /** The year-to-date earnings of the employee */
    private double ytdEarnings;

    /** The year-to-date taxes paid by the employee */
    private double ytdTaxesPaid;

    /** The pre-tax deductions applied to the employee's salary */
    private double pretaxDeductions;

    /**
     * Constructs an HourlyEmployee with the given details.
     *
     * @param name The name of the employee
     * @param id The unique employee ID
     * @param payRate The hourly pay rate of the employee
     * @param ytdEarnings The total earnings of the employee for the year
     * @param ytdTaxesPaid The total taxes paid by the employee for the year
     * @param pretaxDeductions The total pre-tax deductions applied to the employee
     */
    public HourlyEmployee(String name, String id, double payRate, double ytdEarnings,
                          double ytdTaxesPaid, double pretaxDeductions) {
        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.pretaxDeductions = pretaxDeductions;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public double getPayRate() {
        return payRate;
    }

    @Override
    public String getEmployeeType() {
        return "Hourly";
    }

    @Override
    public double getYTDEarnings() {
        return ytdEarnings;
    }

    @Override
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid;
    }

    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions;
    }

    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;
        }

        double regularHours = Math.min(40, hoursWorked);
        double overtimeHours = Math.max(0, hoursWorked - 40);
        double grossPay = regularHours * payRate + overtimeHours * payRate * 1.5;

        double taxableIncome = grossPay - pretaxDeductions;

        double taxesPaid = taxableIncome * 0.2265;

        double netPay = taxableIncome - taxesPaid;

        this.ytdEarnings = round(ytdEarnings + netPay);
        this.ytdTaxesPaid = round(ytdTaxesPaid + taxesPaid);

        return new PayStub(this.name, round(netPay), round(taxesPaid), this.ytdEarnings, this.ytdTaxesPaid);
    }

    private double round(double value) {
        BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
        double rounded = bd.doubleValue();

        return rounded;
    }


    @Override
    public String toCSV() {
        return String.format("HOURLY,%s,%s,%.2f,%.2f,%.2f,%.2f",
                name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }
}
