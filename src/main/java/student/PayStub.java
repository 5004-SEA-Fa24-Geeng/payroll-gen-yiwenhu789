package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PayStub implements IPayStub {
    private final String employeeName;
    private final double netPay;
    private final double taxesPaid;
    private final double ytdEarnings;
    private final double ytdTaxesPaid;

    /**
     * Constructor for PayStub.
     *
     * @param employeeName The name of the employee.
     * @param netPay The final amount after deductions and taxes.
     * @param taxesPaid The total taxes paid for this payroll period.
     * @param ytdEarnings The year-to-date earnings.
     * @param ytdTaxesPaid The year-to-date taxes paid.
     */
    public PayStub(String employeeName, double netPay, double taxesPaid, double ytdEarnings, double ytdTaxesPaid) {
        this.employeeName = employeeName;
        this.netPay = netPay;
        this.taxesPaid = taxesPaid;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
    }

    @Override
    public double getPay() {
        return netPay;
    }

    @Override
    public double getTaxesPaid() {
        return taxesPaid;
    }

    /**
     * Converts the PayStub object to a CSV string.
     *
     * Format: `"employee_name,net_pay,taxes,ytd_earnings,ytd_taxes_paid"`
     *
     * @return the CSV string representation of the PayStub.
     */
    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%s,%s",
                employeeName, formatDouble(netPay), formatDouble(taxesPaid),
                formatDouble(ytdEarnings), formatDouble(ytdTaxesPaid));
    }

    private String formatDouble(double value) {
        String formatted = String.format("%.2f", value);
        return formatted;
    }
}
