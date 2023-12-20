package me.skinnynoonie.bukkit.locationutils.shape;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class ConcreteLineTest {

    @Test
    void testConstructorThrows() {
        assertThrows(IllegalStateException.class, () ->
                new ConcreteLine(
                        new Location(null, 50, 50, 50),
                        new Location(null, 50, 50, 100),
                        0)
        );
    }

    @Test
    public void testFirstLocationIsStart() {
        ConcreteLine concreteLine = new ConcreteLine(new Location(null, 50, 50, 50), new Location(null, 50, 50, 100), 3);

        assertEquals(new Vector(0, 0, 0), concreteLine.getPositionIterator().next());
    }

    @Test
    public void testTotalDistances() {
        ConcreteLine concreteLine = new ConcreteLine(new Location(null, 50, 50, 50), new Location(null, 50, 50, 100), 3);

        int iterations = 0;
        Iterator<Vector> vectors = concreteLine.getPositionIterator();
        while (vectors.hasNext()) {
            vectors.next();
            iterations++;
        }

        assertEquals(16, iterations);
    }

}