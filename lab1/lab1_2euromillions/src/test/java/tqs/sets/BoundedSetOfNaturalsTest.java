/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;

    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[] { 10, 20, 30, 40, 50, 60 });
        setC = BoundedSetOfNaturals.fromArray(new int[] { 50, 60 });
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @Test
    public void testInitialization() {
        BoundedSetOfNaturals newSet = new BoundedSetOfNaturals(5);
        assertEquals(0, newSet.size(), "New set should have size 0");

        assertEquals(6, setB.size(), "SetB should be initialized with 6 elements");
        assertTrue(setB.contains(10));
        assertTrue(setB.contains(60));
    }

    @Test
    public void testAddElement() {
        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

         assertTrue(setA.equals( BoundedSetOfNaturals.fromArray(new int[]{99})));
        
        assertThrows(IllegalArgumentException.class,
            () -> setB.add(11) );

        BoundedSetOfNaturals setD = new BoundedSetOfNaturals(2);
        setD.add(99);
        assertThrows(IllegalArgumentException.class,
            () -> setD.add(99) );

        assertThrows(IllegalArgumentException.class,
            () -> setD.add(-1) );


        assertTrue(!setB.contains(11), "add: added element not found in set.");
        assertEquals(6, setB.size(), "add: elements count not as expected.");
    }

    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[] { 10, -20, -30 };
        assertThrows(IllegalArgumentException.class, () -> BoundedSetOfNaturals.fromArray(elems),
                "Creating set from array with negative should throw IllegalArgumentException.");

        int[] duplicateElems = new int[] { 10, 10, 20 };
        assertThrows(IllegalArgumentException.class, () -> BoundedSetOfNaturals.fromArray(duplicateElems),
                "Creating set from array with duplicates should throw IllegalArgumentException.");
    }

    @Test
    public void testContains() {
        assertTrue(setB.contains(10), "SetB should contain 10.");
        assertFalse(setB.contains(5), "SetB should not contain 5.");
    }

    @Test
    public void testSize() {
        setA.add(1);
        assertEquals(1, setA.size(), "SetA should have size 1.");
        assertEquals(6, setB.size(), "SetB should have size 6.");
    }

    @Test
    public void testIntersectMethod() {
        assertTrue(setB.intersects(setC), "SetB and SetC should intersect.");
        BoundedSetOfNaturals setD = BoundedSetOfNaturals.fromArray(new int[] { 70, 80 });
        assertFalse(setB.intersects(setD), "SetB and SetD should not intersect.");
    }

    @Test
    public void testIterator() {
        int count = 0;
        for (int element : setB) {
            assertTrue(setB.contains(element), "SetB should contain iterated element.");
            count++;
        }
        assertEquals(setB.size(), count, "Iterator should iterate over each element exactly once.");
    }

    @Test
    public void testEqualsAndHashCode() {
        BoundedSetOfNaturals setD = BoundedSetOfNaturals.fromArray(new int[] { 10, 20, 30, 40, 50, 60 });
        assertEquals(setB, setD, "Two sets with the same elements should be equal.");
        assertEquals(setB.hashCode(), setD.hashCode(),
                "Two sets with the same elements should have the same hash code.");
    }


}
