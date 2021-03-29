package shop.data;

import junit.framework.Assert;
import junit.framework.TestCase;

public class VideoTEST extends TestCase {
  public VideoTEST(String name) {
    super(name);
  }
  
  	public void testEquals()
  	{
  		Video v1 = Data.newVideo("X", 2000, "Y");
  		Video v2 = Data.newVideo("X", 2000, "Y");
  		Video v2_2 = Data.newVideo("X", 2000, "Y");
  		Video v3 = Data.newVideo("X1", 2000, "Y");
  		Video v4 = Data.newVideo("X", 2000, "Y1");
  		Video v5 = Data.newVideo("X", 3000, "Y");
  		String h = "This is not a VideoObj";

  		assertTrue(v1.equals(v1));
  		assertTrue(v1.equals(v2));
  		assertTrue(v1.equals(v2_2));
  		assertTrue(v2.equals(v2_2));
  		
  		assertFalse(v1.equals(v3));
  		assertFalse(v1.equals(v4));
  		assertFalse(v1.equals(v5));
  		assertFalse(v1.equals(h));
  	}
  	
  	public void testHash()
  	{
  		Video v1 = Data.newVideo("X", 2000, "Y");
  		Video v2 = Data.newVideo("X", 2000, "Y");
  		Video v2_2 = Data.newVideo("X", 2000, "Y");
  		Video v3 = Data.newVideo("X1", 2000, "Y");
  		Video v4 = Data.newVideo("X", 2000, "Y1");
  		Video v5 = Data.newVideo("X", 3000, "Y");
  		String h = "This is not a VideoObj";
  		
  		assertTrue(v1.hashCode() == v1.hashCode());
  		assertTrue(v1.hashCode() == v2.hashCode());
  		assertTrue(v2.hashCode() == v2_2.hashCode());
  		assertTrue(v1.hashCode() == v2_2.hashCode());

  		assertFalse(v1.hashCode() == v3.hashCode());
  		assertFalse(v1.hashCode() == v4.hashCode());
  		assertFalse(v1.hashCode() == v5.hashCode());
  		assertFalse(v1.hashCode() == h.hashCode());

  		int h1 = v3.hashCode();
  		int h2 = v3.hashCode();
  		int h3 = v3.hashCode();
  		int h4 = v3.hashCode();
  		assertTrue(h1 == h2 && h2 == h3 && h3 == h4);
  	}
  	
  	public void testCompare()
  	{
  		Video v1 = Data.newVideo("X1", 2000, "Y1");
  		Video v2 = Data.newVideo("X1", 2000, "Y1");
  		Video v2_2 = Data.newVideo("X1", 2000, "Y1");
  		Video v3 = Data.newVideo("X2", 2000, "Y1");
  		Video v4 = Data.newVideo("X1", 2000, "Y2");
  		Video v5 = Data.newVideo("X1", 3000, "Y1");
  		// sgn(x.compareTo(y)) == -sgn(y. compareTo(x))
  		// (x. compareTo(y) > 0 && y.compareTo(z) > 0) implies x.compareTo(z) > 0
  		// x.compareTo(y) == 0 implies that sgn(x.compareTo(z)) == sgn(y.compareTo(z))
  		// (x.compareTo(y) == 0) == (x.equals(y))
  		assertTrue(v1.compareTo(v1) == 0);
  		assertTrue(v1.compareTo(v2) == 0);
  		assertTrue(v2.compareTo(v2_2) == 0);
  		assertTrue(v1.compareTo(v2_2) == 0);

  		assertTrue(v1.compareTo(v3) < 0);
  		assertTrue(v3.compareTo(v1) > 0);

  		assertTrue(v1.compareTo(v4) < 0);
  		assertTrue(v4.compareTo(v1) > 0);
  		
  		assertTrue(v3.compareTo(v4) > 0);
  		assertTrue(v4.compareTo(v5) > 0);
  		assertTrue(v3.compareTo(v5) > 0);
  		
  		assertTrue(v1.compareTo(v5) < 0);
  		assertTrue(v2.compareTo(v5) < 0);
  	}
  
}
