import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void testCheck(){
        boolean test;

        Room room1 = new Room("", "");
        Room room2 = new Room("", "");
        room1.setEast(room2);

        test = room1.hasEast();

        test = test && room2.hasWest();

        test = test && !room1.hasWest();

        test = test && !room1.hasSouth();

        test = test && !room1.hasNorth();

        assertTrue(test);
    }

}