import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    void nullException() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null, 20.2, 500.5);
                }
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("emptyStrings")
    void blankNameException(String name) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name, 20.2, 500.5);
                }
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    static Stream<String> emptyStrings() {
        return Stream.of(" ", " ", "\n", "   ", "", "\t");
    }

    @Test
    void negativeSpeedTest() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Black Beauty", -20.2, 500.5);
                }
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void negativeDistanceTest() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("Black Beauty", 20.2, -500.5);
                }
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    
    @Test
    void getNameTest() {
        assertEquals("Black Beauty", (new Horse("Black Beauty", 20.2, 500.5).getName()));
    }

    @Test
    void getSpeedTest() {
        assertEquals(20.2, (new Horse("Black Beauty", 20.2, 500.5).getSpeed()));
    }

    @Test
    void getDistanceTest() {
        assertEquals(500.5, (new Horse("Black Beauty", 20.2, 500.5).getDistance()));
        assertEquals(0, (new Horse("Black Beauty", 20.2).getDistance()));
    }

    @Test
    void getMoveTest() {

        try (MockedStatic<Horse> horse =  Mockito.mockStatic(Horse.class)) {
            Horse asd = new Horse("asd", 20, 0);
            asd.move();

            horse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            horse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            assertEquals(10, asd.getDistance() + asd.getSpeed() * Horse.getRandomDouble(0.2, 0.9));
        }
    }

}
