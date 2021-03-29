package hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecordTest {

	@Test
	void testRecord() 
	{
		
		System.out.println("Now testing Record...");
		
		VideoObj v = new VideoObj("title1", 2000, "director1");
		Record r1 = new Record(v, 15, 4, 22);
		Record r2 = r1.copy();
		
		assertTrue(r1.toString().equals("title1 (2000) : director1 [15,4,22]"));
		assertTrue(r1 != r2);
		assertTrue(r1.toString().equals(r2.toString()));
		
		System.out.println("Record passes tests");

	}

}
