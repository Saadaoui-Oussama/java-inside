package fr.umlv.javainside;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

    @Test
    public void testBasique() {
        assertEquals(5, new Point(5, 3).x());
        assertEquals(3, new Point(5, 3).y());
    }
}