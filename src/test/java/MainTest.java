import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {
    @Test
    @Timeout(value = 22)
    void failsIfExecutionTimeExceeds22Seconds() throws Exception {
        String[] args = {};
        Main.main(args);
    }
}
