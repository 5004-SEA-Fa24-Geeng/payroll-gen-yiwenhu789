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
            return null;
        }

        double grossPay = payRate / 24;

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
        return String.format("Salary,%s,%s,%.2f,%.2f,%.2f,%.2f",
                name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid);
    }
}

