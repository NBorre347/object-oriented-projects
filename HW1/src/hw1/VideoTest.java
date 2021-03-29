package hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VideoTest {

	@Test
	void testVideoConstructor() 
	{
		
		System.out.println("Now testing VideoObj constructor...");
		
		try 
		{
			new VideoObj("X", 1800, "Y");
			fail();
	    } catch (IllegalArgumentException e) { }
	    try 
	    {
	    	new VideoObj("X", 5000, "Y");
	    	fail();
	    } catch (IllegalArgumentException e) { }
	    try 
	    {
	    	new VideoObj("X", 1801, "Y");
	    	new VideoObj("X", 4999, "Y");
	    } 
	    catch (IllegalArgumentException e) 
	    {
	    	fail();
	    }
	    try 
	    {
	    	new VideoObj("", 2000, "");
	    	new VideoObj("  ", 2000, "  ");
	    	fail();
	    } catch (IllegalArgumentException e) { }
	    
	    System.out.println("VideoObj constructor passes tests");
	    
	}
	
	@Test
	void testVideoFields() 
	{
		
		System.out.println("Now testing VideoObj fields...");
	    
	    String title1 = "ABC";
		String title2 = "xyz";
		String director1 = "Eric Johnsanthan";
		String director2 = "Alan Smithy";
		int year1 = 1989;
		int year2 = 2007;
		
		VideoObj v1 = new VideoObj(title1, year1, director1);
		VideoObj v2 = new VideoObj(title2, year2, director2);
		
		assertTrue(v1.title().equals("ABC"));
		assertTrue(v2.title().equals("xyz"));
		
		assertTrue(v1.year() == 1989);
		assertTrue(v2.year() == 2007);
		
		assertTrue(v1.director().equals("Eric Johnsanthan"));
		assertTrue(v2.director().equals("Alan Smithy"));
		
		System.out.println("VideoObj fields passes tests");
		
	}
	
	@Test
	void testVideoFunctions()
	{
		
		System.out.println("Now testing VideoObj functions...");

		String title1 = "ABC";
		String title2 = "xyz";
		String director1 = "Eric Johnsanthan";
		String director2 = "Alan Smithy";
		int year1 = 1989;
		int year2 = 2007;
		
		VideoObj v1 = new VideoObj(title1, year1, director1);
		VideoObj v2 = new VideoObj(title2, year2, director2);
		VideoObj v3 = new VideoObj(title2, year1, director1);
		VideoObj v4 = new VideoObj(title1, year1, director2);
		VideoObj v1_2 = new VideoObj(title1, year1, director1);
		
		assertFalse(v1.equals(v3));
		assertTrue(v1.equals(v1_2));
		assertFalse(v1.equals(v3));
		assertFalse(v1.equals(null));
		
		assertTrue(v1.compareTo(v1_2) == 0);
		assertTrue(v1.compareTo(v3) < 0);
		assertTrue(v1.compareTo(v4) > 0);
		
		assertTrue(v1.toString().equals("ABC (1989) : Eric Johnsanthan"));
		assertTrue(v2.toString().equals("xyz (2007) : Alan Smithy"));
		
		assertTrue(v1.hashCode() == -269848043);
		assertTrue(v2.hashCode() == -400575153);
		
		System.out.println("VideoObj functions passes tests");
		
	}

}
