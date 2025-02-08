import org.junit.jupiter.api.Test;
import student.*;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @Test
    void testHourlyEmployeeInitialization() {
        HourlyEmployee employee = new HourlyEmployee("Yiwen Hu", "999", 18.5, 80000, 12000, 10);

        assertEquals("Yiwen Hu", employee.getName());
        assertEquals("999", employee.getID());
        assertEquals(18.5, employee.getPayRate());
        assertEquals("Hourly", employee.getEmployeeType());
        assertEquals(80000, employee.getYTDEarnings());
        assertEquals(12000, employee.getYTDTaxesPaid());
        assertEquals(10, employee.getPretaxDeductions());
    }

    @Test
    void testSalaryEmployeeInitialization() {
        SalaryEmployee employee = new SalaryEmployee("Way Way", "220", 50000, 20000, 5000, 1000);

        assertEquals("Way Way", employee.getName());
        assertEquals("220", employee.getID());
        assertEquals(50000, employee.getPayRate());
        assertEquals("Salary", employee.getEmployeeType());
        assertEquals(20000, employee.getYTDEarnings());
        assertEquals(5000, employee.getYTDTaxesPaid());
        assertEquals(1000, employee.getPretaxDeductions());
    }

    @Test
    void testRunPayroll_HourlyEmployee() {
        HourlyEmployee employee = new HourlyEmployee("Yiwen Hu", "x345", 20, 10000, 1500, 200);
        IPayStub payStub = employee.runPayroll(40);

        assertNotNull(payStub);
        double expectedGrossPay = 800;
        double expectedTaxableIncome = expectedGrossPay - 200;
        double expectedTaxes = expectedTaxableIncome * 0.2265;
        double expectedNetPay = expectedTaxableIncome - expectedTaxes;

        assertEquals(expectedNetPay, payStub.getPay(), 0.01);
        assertEquals(expectedTaxes, payStub.getTaxesPaid(), 0.01);
    }

    @Test
    void testRunPayroll_HourlyEmployee_Overtime() {
        HourlyEmployee employee = new HourlyEmployee("Yiwen Hu", "x789", 20, 10000, 1500, 200);
        IPayStub payStub = employee.runPayroll(50);

        assertNotNull(payStub);
        double expectedGrossPay = (40 * 20) + (10 * 30);
        double expectedTaxableIncome = expectedGrossPay - 200;
        double expectedTaxes = expectedTaxableIncome * 0.2265;
        double expectedNetPay = expectedTaxableIncome - expectedTaxes;

        assertEquals(expectedNetPay, payStub.getPay(), 0.01);
        assertEquals(expectedTaxes, payStub.getTaxesPaid(), 0.01);
    }

    @Test
    void testRunPayroll_SalaryEmployee() {
        SalaryEmployee employee = new SalaryEmployee("Way Way", "x676", 52000.0, 20000, 5000, 1000);
        IPayStub payStub = employee.runPayroll(40);

        assertNotNull(payStub);
        double expectedGrossPay = 52000.0 / 24;
        double expectedTaxableIncome = expectedGrossPay - 1000;
        double expectedTaxes = expectedTaxableIncome * 0.2265;
        double expectedNetPay = expectedTaxableIncome - expectedTaxes;

        assertEquals(expectedNetPay, payStub.getPay(), 0.01);
        assertEquals(expectedTaxes, payStub.getTaxesPaid(), 0.01);
    }

    @Test
    void testRunPayroll_NegativeHours() {
        HourlyEmployee employee = new HourlyEmployee("Yiwen Hu", "x999", 20, 10000, 1500, 200);
        IPayStub payStub = employee.runPayroll(-5);

        assertNull(payStub);
    }

    @Test
    public void testRunPayroll_LargeValues() {
        SalaryEmployee employee = new SalaryEmployee("Big Money", "S999", 1_000_000_000.00, 5_000_000.00, 2_000_000.00, 50000.00);
        IPayStub payStub = employee.runPayroll(40);

        assertNotNull(payStub, "Payroll should handle large salary values");
        assertTrue(payStub.getPay() > 0, "Net pay should be positive for high salaries");
    }
}
