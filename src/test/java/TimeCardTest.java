import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import student.*;

public class TimeCardTest {

    @Test
    void testTimeCardInitialization() {
        TimeCard timeCard = new TimeCard("146", 40);

        assertEquals("146", timeCard.getEmployeeID());
        assertEquals(40, timeCard.getHoursWorked());
    }
}
