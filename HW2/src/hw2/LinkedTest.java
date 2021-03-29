package hw2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedTest {

	@Test
	void testDelete () {
	        MyLinked b = new MyLinked ();
	        assertTrue(b.isEmpty());
	        b.add (1);
	        assertTrue(b.size() == 1);
	        assertFalse(b.isEmpty());
	        assertTrue(b.getFirst().item == 1);

	        b.delete (0);
	        assertTrue(b.size() == 0);
	        assertTrue(b.isEmpty());

	        for (double i = 1; i < 13; i++) {
	            b.add (i);
	        }
	        //System.out.println(b.toString());
	        assertTrue(b.size() == 12);
	        assertFalse(b.isEmpty());
	        assertTrue(b.getFirst().item == 12);
	        assertTrue(b.get(5).item == 7);

	        b.delete (0);
	        //System.out.println(b.toString());
	        assertTrue(b.size() == 11);
	        assertFalse(b.isEmpty());
	        assertTrue(b.getFirst().item == 11);
	        assertTrue(b.get(5).item == 6);

	        b.delete (10);
	        //System.out.println(b.toString());
	        assertTrue(b.size() == 10);
	        assertFalse(b.isEmpty());
	        assertTrue(b.getFirst().item == 11);
	        assertTrue(b.get(5).item == 6);

	        b.delete (4);
	        //System.out.println(b.toString());
	        assertTrue(b.size() == 9);
	        assertFalse(b.isEmpty());
	        assertTrue(b.getFirst().item == 11);
	        assertTrue(b.get(5).item == 5);
	}
	
	@Test
	void testReverse () {
	        MyLinked b = new MyLinked ();
	        b.reverse ();
	        assertTrue(b.isEmpty());

	        b.add (1);
	        assertTrue(b.size() == 1);
	        assertFalse(b.isEmpty());
	        assertTrue(b.getFirst().item == 1);

	        b.reverse ();
	        assertTrue(b.size() == 1);
	        assertFalse(b.isEmpty());
	        assertTrue(b.getFirst().item == 1);

	        b.add (2);
	        assertTrue(b.size() == 2);
	        assertFalse(b.isEmpty());
	        assertTrue(b.get(0).item == 2);
	        assertTrue(b.get(1).item == 1);

	        b.reverse ();
	        assertTrue(b.size() == 2);
	        assertFalse(b.isEmpty());
	        assertTrue(b.get(0).item == 1);
	        assertTrue(b.get(1).item == 2);

	        b.reverse ();
	        assertTrue(b.size() == 2);
	        assertFalse(b.isEmpty());
	        assertTrue(b.get(0).item == 2);
	        assertTrue(b.get(1).item == 1);

	        for (double i = 3; i < 7; i++) {
	            b.add (i);
	            b.add (i);
	        }
	        //System.out.println(b.toString());
	        assertTrue(b.size() == 10);
	        assertFalse(b.isEmpty());
	        assertTrue(b.get(0).item == 6);
	        assertTrue(b.get(2).item == 5);
	        assertTrue(b.get(4).item == 4);
	        assertTrue(b.get(6).item == 3);
	        assertTrue(b.get(8).item == 2);
	        assertTrue(b.get(9).item == 1);

	        b.reverse ();
	        //System.out.println(b.toString());
	        assertTrue(b.size() == 10);
	        assertFalse(b.isEmpty());
	        assertTrue(b.get(0).item == 1);
	        assertTrue(b.get(1).item == 2);
	        assertTrue(b.get(2).item == 3);
	        assertTrue(b.get(4).item == 4);
	        assertTrue(b.get(6).item == 5);
	        assertTrue(b.get(8).item == 6);
	}
	
	@Test
	void testRemove () {
	        MyLinked b = new MyLinked ();
	        b.remove (4);
	        assertTrue(b.size() == 0);
	        assertTrue(b.isEmpty());

	        b.add (1);
	        b.remove (4);
	        assertTrue(b.size() == 1);
	        assertFalse(b.isEmpty());
	        assertTrue(b.getFirst().item == 1);
	        assertTrue(b.find(1) != -1);

	        b.remove (1);
	        assertTrue(b.size() == 0);
	        assertTrue(b.isEmpty());
	        assertTrue(b.find(1) == -1);

	        for (double i = 1; i < 5; i++) {
	            b.add (i);
	            b.add (i);
	        }
	        for (double i = 1; i < 5; i++) {
	            b.add (i);
	            b.add (i);
	            b.add (i);
	            b.add (i);
	            b.add (i);
	        }
	        //System.out.println(b.toString());
	        assertTrue(b.size() == 28);
	        assertFalse(b.isEmpty());
	        assertTrue(b.find(1) != -1);
	        assertTrue(b.find(2) != -1);
	        assertTrue(b.find(3) != -1);
	        assertTrue(b.find(4) != -1);

	        b.remove (9);
	        //System.out.println(b.toString());
	        assertTrue(b.size() == 28);
	        assertFalse(b.isEmpty());

	        b.remove (3);
	        //System.out.println(b.toString());
	        assertTrue(b.size() == 21);
	        assertFalse(b.isEmpty());
	        assertTrue(b.get(5).item == 2);
	        assertTrue(b.get(17).item == 2);
	        assertTrue(b.find(3) == -1);

	        b.remove (1);
	        //System.out.println(b.toString());
	        assertTrue(b.size() == 14);
	        assertFalse(b.isEmpty());
	        assertTrue(b.get(10).item == 4);
	        assertTrue(b.get(13).item == 2);
	        assertTrue(b.find(1) == -1);

	        b.remove (4);
	        //System.out.println(b.toString());
	        assertTrue(b.size() == 7);
	        assertFalse(b.isEmpty());
	        assertTrue(b.get(0).item == 2);
	        assertTrue(b.get(1).item == 2);
	        assertTrue(b.get(2).item == 2);
	        assertTrue(b.get(3).item == 2);
	        assertTrue(b.get(4).item == 2);
	        assertTrue(b.get(5).item == 2);
	        assertTrue(b.get(6).item == 2);
	        assertTrue(b.find(4) == -1);

	        b.remove (2);
	        //System.out.println(b.toString());
	        assertTrue(b.size() == 0);
	        assertTrue(b.isEmpty());
	        assertTrue(b.find(2) == -1);

	}

}
