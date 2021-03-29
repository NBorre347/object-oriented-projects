package hw1;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class testStandard {
	
	public static void main(String[] args)
	{
		
		//VideoObj Testing
		
		System.out.println("Now testing VideoObj");
		
		try {
			new VideoObj("X", 1800, "Y");
			assert(false);
	    } catch (IllegalArgumentException e) { }
	    try {
	    	new VideoObj("X", 5000, "Y");
	    	assert(false);
	    } catch (IllegalArgumentException e) { }
	    try {
	    	new VideoObj("X", 1801, "Y");
	    	new VideoObj("X", 4999, "Y");
	    } catch (IllegalArgumentException e) {
	    	assert(false);
	    }
	    try {
	    	new VideoObj("", 2000, "");
	    	new VideoObj("  ", 2000, "  ");
	    	assert(false);
	    } catch (IllegalArgumentException e) { }
	    
	    String title1 = "ABC";
		String title2 = "xyz";
		String director1 = "Eric Johnsanthan";
		String director2 = "Alan Smithy";
		int year1 = 1989;
		int year2 = 2007;
		
		VideoObj v1 = new VideoObj(title1, year1, director1);
		VideoObj v2 = new VideoObj(title2, year2, director2);
		
		assert(v1.title().equals("ABC"));
		assert(v2.title().equals("xyz"));
		
		assert(v1.year() == 1989);
		assert(v2.year() == 2007);
		
		assert(v1.director().equals("Eric Johnsanthan"));
		assert(v2.director().equals("Alan Smithy"));

		VideoObj v3 = new VideoObj(title2, year1, director1);
		VideoObj v4 = new VideoObj(title1, year1, director2);
		VideoObj v1_2 = new VideoObj(title1, year1, director1);
		
		assert(v1.equals(v3) == false);
		assert(v1.equals(v1_2) == true);
		assert(v1.equals(v3) == false);
		assert(v1.equals(null) == false);
		
		assert(v1.compareTo(v1_2) == 0);
		assert(v1.compareTo(v3) < 0);
		assert(v1.compareTo(v4) > 0);
		
		assert(v1.toString().equals("ABC (1989) : Eric Johnsanthan"));
		assert(v2.toString().equals("xyz (2007) : Alan Smithy"));
		
		assert(v1.hashCode() == -269848043);
		assert(v2.hashCode() == -400575153);
		
		//Record Testing
		
		System.out.println("Now testing Record");
		
		VideoObj v = new VideoObj("title1", 2000, "director1");
		Record r1 = new Record(v, 15, 4, 22);
		Record r2 = r1.copy();
		
		assert(r1.toString().equals("title1 (2000) : director1 [15,4,22]"));
		assert(r1 != r2);
		assert(r1.toString().equals(r2.toString()));
		
		//InventorySet Testing
		
		System.out.println("Now testing InventorySet");
		
		InventorySet set = new InventorySet();
		v1 = new VideoObj( "ABC", 2000, "D1" );
		v1_2 = new VideoObj( "ABC", 2000, "D1" );
		v2 = new VideoObj( "xyz", 2000, "D2" );
		
		assert(set.size() == 0);
		assert(set.get(v1) == null);
		assert(set.get(v2) == null);
		
		set.addNumOwned(v1, 1);
		assert(set.size() == 1);
		assert(set.get(v1).numOwned == 1);
		assert(set.get(v2) == null);
		
		set.addNumOwned(v2, 2);
		assert(set.size() == 2);
		assert(set.get(v1).numOwned == 1);
		assert(set.get(v2).numOwned == 2);
		
		set.addNumOwned(v2, 2);
		assert(set.size() == 2);
		assert(set.get(v1).numOwned == 1);
		assert(set.get(v2).numOwned == 4);
		
		set.addNumOwned(v1_2, 1);
		assert(set.size() == 2);
		assert(set.get(v1).numOwned == 2);
		assert(set.get(v2).numOwned == 4);
		
		set.addNumOwned(v1, -2);
		assert(set.size() == 1);
		assert(set.get(v1) == null);
		assert(set.get(v2).numOwned == 4);
		
		set.addNumOwned(v1, -2);
		assert(set.size() == 1);
		assert(set.get(v1) == null);
		assert(set.get(v2).numOwned == 4);
		
		try {
			set.addNumOwned(null, 1);
			assert(false);
		} catch (IllegalArgumentException e) { }
		try {
			set.addNumOwned(v1, 0);
			assert(false);
		} catch (IllegalArgumentException e) { }
		
		set.clear();
		assert(set.size() == 0);
		assert(set.get(v1) == null);
		assert(set.get(v2) == null);
		
		set.addNumOwned(v1, 10);
		set.addNumOwned(v2, 2);
		
		set.checkOut(v1);
		assert(set.get(v1).numOut == 1 && set.get(v1).numRentals == 1);
		set.checkOut(v1);
		assert(set.get(v1).numOut == 2 && set.get(v1).numRentals == 2);
		set.checkOut(v1_2);
		assert(set.get(v1).numOut == 3 && set.get(v1).numRentals == 3);
		
		set.checkOut(v2);
		assert(set.get(v2).numOut == 1 && set.get(v2).numRentals == 1);
		set.checkOut(v2);
		assert(set.get(v2).numOut == 2 && set.get(v2).numRentals == 2);
		try {
			set.checkOut(v2);
			assert(false);
		} catch (IllegalArgumentException e) { }
		assert(set.get(v2).numOut == 2 && set.get(v2).numRentals == 2);

		set.checkIn(v1);
		assert(set.get(v1).numOut == 2 && set.get(v1).numRentals == 3);
		
		try {
			set.checkOut(null);
			assert(false);
		} catch (IllegalArgumentException e) { }
		try {
			set.checkIn(null);
			assert(false);
		} catch (IllegalArgumentException e) { }
		
		set.clear();
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
			assert(expected.contains(r.video));
			actual.add(r.video);
		}
		
		assert(expected.containsAll(actual) && actual.containsAll(expected));
		
		System.out.println("Tests passed without an issue");
		
		//assert(false);
		
	}
	
}
