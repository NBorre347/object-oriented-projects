package hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;

class InventoryTest {

	@Test
	void testInventorySetAdd() 
	{
		
		System.out.println("Now testing InventorySet add...");
		
		InventorySet set = new InventorySet();
		VideoObj v1 = new VideoObj( "ABC", 2000, "D1" );
		VideoObj v1_2 = new VideoObj( "ABC", 2000, "D1" );
		VideoObj v2 = new VideoObj( "xyz", 2000, "D2" );
		
		assertTrue(set.size() == 0);
		assertTrue(set.get(v1) == null);
		assertTrue(set.get(v2) == null);
		
		set.addNumOwned(v1, 1);
		assertTrue(set.size() == 1);
		assertTrue(set.get(v1).numOwned == 1);
		assertTrue(set.get(v2) == null);
		
		set.addNumOwned(v2, 2);
		assertTrue(set.size() == 2);
		assertTrue(set.get(v1).numOwned == 1);
		assertTrue(set.get(v2).numOwned == 2);
		
		set.addNumOwned(v2, 2);
		assertTrue(set.size() == 2);
		assertTrue(set.get(v1).numOwned == 1);
		assertTrue(set.get(v2).numOwned == 4);
		
		set.addNumOwned(v1_2, 1);
		assertTrue(set.size() == 2);
		assertTrue(set.get(v1).numOwned == 2);
		assertTrue(set.get(v2).numOwned == 4);
		
		set.addNumOwned(v1, -2);
		assertTrue(set.size() == 1);
		assertTrue(set.get(v1) == null);
		assertTrue(set.get(v2).numOwned == 4);
		
		set.addNumOwned(v1, -2);
		assertTrue(set.size() == 1);
		assertTrue(set.get(v1) == null);
		assertTrue(set.get(v2).numOwned == 4);
		
		System.out.println("InventorySet add passes tests");
		
	}
	
	@Test
	void testInventoryAddExceptions()
	{
		
		System.out.println("Now testing InventorySet add exceptions...");
		
		InventorySet set = new InventorySet();
		VideoObj v1 = new VideoObj( "ABC", 2000, "D1" );
		VideoObj v1_2 = new VideoObj( "ABC", 2000, "D1" );
		VideoObj v2 = new VideoObj( "xyz", 2000, "D2" );
		
		try 
		{
			set.addNumOwned(null, 1);
			fail();
		} catch (IllegalArgumentException e) { }
		try 
		{
			set.addNumOwned(v1, 0);
			fail();
		} catch (IllegalArgumentException e) { }
		
		System.out.println("InventorySet add exceptions passes tests");
		
	}
	
	@Test
	void testInventoryClear()
	{
		
		System.out.println("Now testing InventorySet clear...");
		
		InventorySet set = new InventorySet();
		VideoObj v1 = new VideoObj( "ABC", 2000, "D1" );
		VideoObj v1_2 = new VideoObj( "ABC", 2000, "D1" );
		VideoObj v2 = new VideoObj( "xyz", 2000, "D2" );
		
		set.addNumOwned(v1, 10);
		set.addNumOwned(v2, 10);
		assertTrue(set.size() == 2);
		
		set.clear();
		assertTrue(set.size() == 0);
		assertTrue(set.get(v1) == null);
		assertTrue(set.get(v2) == null);
		
		System.out.println("InventorySet clear passes tests");
		
	}
	
	@Test
	void testInventoryCheckInOut()
	{
		
		System.out.println("Now testing InventorySet checkIn and checkOut...");
		
		InventorySet set = new InventorySet();
		VideoObj v1 = new VideoObj( "ABC", 2000, "D1" );
		VideoObj v1_2 = new VideoObj( "ABC", 2000, "D1" );
		VideoObj v2 = new VideoObj( "xyz", 2000, "D2" );
		
		set.addNumOwned(v1, 10);
		set.addNumOwned(v2, 2);
		
		set.checkOut(v1);
		assertTrue(set.get(v1).numOut == 1 && set.get(v1).numRentals == 1);
		set.checkOut(v1);
		assertTrue(set.get(v1).numOut == 2 && set.get(v1).numRentals == 2);
		set.checkOut(v1_2);
		assertTrue(set.get(v1).numOut == 3 && set.get(v1).numRentals == 3);
		
		set.checkOut(v2);
		assertTrue(set.get(v2).numOut == 1 && set.get(v2).numRentals == 1);
		set.checkOut(v2);
		assertTrue(set.get(v2).numOut == 2 && set.get(v2).numRentals == 2);
		try 
		{
			set.checkOut(v2);
			fail();
		} catch (IllegalArgumentException e) { }
		assertTrue(set.get(v2).numOut == 2 && set.get(v2).numRentals == 2);

		set.checkIn(v1);
		assertTrue(set.get(v1).numOut == 2 && set.get(v1).numRentals == 3);
		
		try 
		{
			set.checkOut(null);
			fail();
		} catch (IllegalArgumentException e) { }
		try 
		{
			set.checkIn(null);
			fail();
		} catch (IllegalArgumentException e) { }
		
		System.out.println("InventorySet checkIn and checkOut pass tests");
		
	}
	
	@Test
	void testInventoryCollection()
	{
		
		System.out.println("Now testing InventorySet toCollection...");
		
		InventorySet set = new InventorySet();
		VideoObj v1 = new VideoObj( "ABC", 2000, "D1" );
		VideoObj v2 = new VideoObj( "xyz", 2000, "D2" );
		
		set.addNumOwned(v1, 10);
		set.addNumOwned(v2, 10);
		
		Set<VideoObj> expected = new HashSet<VideoObj>();
		expected.add(v1);
		expected.add(v2);
		
		Set<VideoObj> actual = new HashSet<VideoObj>();
		Collection c = set.toCollection();
		Iterator<Record> it = c.iterator();
		while (it.hasNext())
		{
			Record r = it.next();
			assertTrue(expected.contains(r.video));
			actual.add(r.video);
		}
		
		assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
		
		System.out.println("InventorySet toCollection pass tests");
		
	}

}
