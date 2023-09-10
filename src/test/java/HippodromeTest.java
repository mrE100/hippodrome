import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class HippodromeTest {
    @Test
    void nullAndEmptyException() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(null);
                }
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
        exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(new ArrayList<>());
                }
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Name" + i, 10.0, 500.0));
        }
        Hippodrome hip = new Hippodrome(horses);
        assertEquals(horses, hip.getHorses());
    }

    @Test
    void moveTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hip = new Hippodrome(horses);

        hip.move();
        for (Horse h : horses) {
            verify(h).move();
        }
    }

    @Test
    void getWinnerTest() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("a", 10, 1000));
        horses.add(new Horse("b", 10, 0));
        horses.add(new Horse("c", 10, 0));
        Hippodrome hip = new Hippodrome(horses);
        assertEquals("a", hip.getWinner().getName());

    }

}
