import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DemoTest {

    @Test
    void testAddition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    void testAdditionFails() {
        assertEquals(0.3, 0.1 + 0.2, Math.pow(10, -10));
    }

}
