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
}

