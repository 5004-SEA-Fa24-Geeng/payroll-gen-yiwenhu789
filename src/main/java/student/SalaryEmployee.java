package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalaryEmployee implements IEmployee {
    private String name;
    private String id;
    private double payRate;
    private double ytdEarnings;
    private double ytdTaxesPaid;
    private double pretaxDeductions;

    public SalaryEmployee(String name, String id, double payRate, double ytdEarnings, double ytdTaxesPaid, double pretaxDeductions) {
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
    public String getEmployeeType() { return "Salary"; }

    @Override
    public double getYTDEarnings() { return ytdEarnings; }

    @Override
    public double getYTDTaxesPaid() { return ytdTaxesPaid; }

    @Override
    public double getPretaxDeductions() { return pretaxDeductions; }

    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            return null; // Skip employees with negative hours
        }

        // Salary employees receive fixed pay per period (24 pay periods per year)
        double grossPay = round(payRate / 24);

        // Apply pre-tax deductions
        double taxableIncome = round(grossPay - pretaxDeductions);

        // Calculate taxes (22.65% of taxable income)
        double taxesPaid = round(taxableIncome * 0.2265);

        // Calculate final net pay
        double netPay = round(taxableIncome - taxesPaid);

        // Update YTD earnings and taxes paid
        this.ytdEarnings = round(ytdEarnings + grossPay);
        this.ytdTaxesPaid = round(ytdTaxesPaid + taxesPaid);

        return new PayStub(this.name, netPay, taxesPaid, this.ytdEarnings, this.ytdTaxesPaid);
    }

    private double round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }


    @Override
    public String toCSV() {
        return String.format("Salary,%s,%s,%.2f,%.2f,%.2f,%.2f",
                name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }
}

