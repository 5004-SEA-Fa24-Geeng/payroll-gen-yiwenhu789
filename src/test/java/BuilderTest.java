import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import student.*;

public class BuilderTest {

    @Test
    void testBuildEmployeeFromCSV() {
        String csvLine = "Hourly,Yiwen Hu,999,18.5,200,10000,1500";
        IEmployee employee = Builder.buildEmployeeFromCSV(csvLine);

        assertTrue(employee instanceof HourlyEmployee);
        assertEquals("Yiwen Hu", employee.getName());
        assertEquals("999", employee.getID());
        assertEquals(18.5, employee.getPayRate());
    }

    @Test
    void testBuildTimeCardFromCSV() {
        String csvLine = "789,39";
        ITimeCard timeCard = Builder.buildTimeCardFromCSV(csvLine);

        assertEquals("789", timeCard.getEmployeeID());
        assertEquals(39, timeCard.getHoursWorked());
    }

    @Test
    void testInvalidEmployeeCSV() {
        String invalidCsv = "InvalidData";
        assertThrows(IllegalArgumentException.class, () -> Builder.buildEmployeeFromCSV(invalidCsv));
    }
}

