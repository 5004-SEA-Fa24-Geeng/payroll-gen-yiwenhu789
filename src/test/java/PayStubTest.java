import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import student.*;

public class PayStubTest {

    @Test
    void testPayStub_CorrectValues() {
        PayStub payStub = new PayStub("Yiwen Hu", 1500, 300, 10000, 1500);

        assertEquals(1500, payStub.getPay(), 0.01);
        assertEquals(300, payStub.getTaxesPaid(), 0.01);
        assertEquals("Yiwen Hu,1500.0,300.0,10000.0,1500.0", payStub.toCSV());
    }
}

