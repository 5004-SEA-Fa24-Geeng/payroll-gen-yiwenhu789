package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        return String.format("Hourly,%s,%s,%.2f,%.2f,%.2f,%.2f",
                name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }
}
