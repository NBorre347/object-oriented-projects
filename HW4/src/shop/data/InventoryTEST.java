package shop.data;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.List;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

// TODO: complete the tests
public class InventoryTEST extends TestCase {
  public InventoryTEST(String name) {
    super(name);
  }

  public void testSize() {
	  InventorySet I = new InventorySet();
	  Video v1 = new VideoObj("AA", 2000, "BB");
	  Video v2 = new VideoObj("AA", 2000, "CC");
	  Video v2_2 = new VideoObj("AA", 2000, "CC");
	  Video v3 = new VideoObj("DD", 2000, "BB");
	  
	  assertTrue(I.size() == 0);
	  I.addNumOwned(v1, 1);
	  assertTrue(I.size() == 1);
	  I.addNumOwned(v1, 1);
	  assertTrue(I.size() == 1);
	  I.addNumOwned(v2, 3);
	  assertTrue(I.size() == 2);
	  I.addNumOwned(v2_2, 1);
	  assertTrue(I.size() == 2);
	  I.addNumOwned(v3, 1);
	  assertTrue(I.size() == 3);
	  I.addNumOwned(v1, -1);
	  assertTrue(I.size() == 3);
	  I.addNumOwned(v1, -1);
	  assertTrue(I.size() == 2);
	  try { I.addNumOwned(v1, -1); fail(); }
	  catch (IllegalArgumentException e) {  }
  }

  public void testAddNumOwned() {
	  InventorySet I = new InventorySet();
	  Video v1 = new VideoObj("AA", 2000, "BB");
	  Video v2 = new VideoObj("AA", 2000, "CC");
	  Video v2_2 = new VideoObj("AA", 2000, "CC");
	  Video v3 = new VideoObj("DD", 2000, "BB");
	  
	  assertTrue(null == I.get(v1));
	  I.addNumOwned(v1, 1);
	  assertTrue(I.get(v1).video() == v1);
	  assertTrue(I.get(v1).numOwned() == 1);
	  
	  assertTrue(null == I.get(v2));
	  I.addNumOwned(v2, 3);
	  assertTrue(I.get(v2).video() == v2);
	  assertTrue(I.get(v2).numOwned() == 3);

	  I.addNumOwned(v2_2, 1);
	  assertTrue(I.get(v2_2).video() == v2);
	  assertTrue(I.get(v2_2).numOwned() == 4);
	  
	  try { I.addNumOwned(v3, 0); fail(); }
	  catch (IllegalArgumentException e) {}
	  try { I.addNumOwned(null, 8); fail(); }
	  catch (IllegalArgumentException e) {}
	  
	  I.addNumOwned(v1, -1);
	  assertTrue(null == I.get(v1));
	  try { I.addNumOwned(v1, -1); fail(); }
	  catch (IllegalArgumentException e) {}
	  
	  I.addNumOwned(v1, 2);
	  try { I.addNumOwned(v1, -3); fail(); }
	  catch (IllegalArgumentException e) {}
	  assertTrue(I.get(v1).numOwned() == 2);
  }

  public void testCheckOutCheckIn() {
	  InventorySet I = new InventorySet();
	  Video v1 = new VideoObj("AA", 2000, "BB");
	  Video v2 = new VideoObj("AA", 2000, "CC");
	  Video v2_2 = new VideoObj("AA", 2000, "CC");
	  
	  try { I.checkOut(null); fail(); }
	  catch (IllegalArgumentException e) {}
	  
	  I.addNumOwned(v1, 1);
	  I.checkOut(v1);
	  assertTrue(I.get(v1).numOut() == 1 && I.get(v1).numRentals() == 1);
	  try { I.checkOut(v1); fail(); }
	  catch (IllegalArgumentException e) {}
	  
	  I.addNumOwned(v2, 3);
	  I.checkOut(v2);
	  assertTrue(I.get(v2).numOut() == 1 && I.get(v2).numRentals() == 1);
	  I.checkOut(v2_2);
	  assertTrue(I.get(v2).numOut() == 2 && I.get(v2).numRentals() == 2);
	  I.addNumOwned(v2, -1);
	  assertTrue(I.get(v2).numOwned() == 2);
	  assertTrue(I.get(v2).numOut() == 2 && I.get(v2).numRentals() == 2);
	  try { I.addNumOwned(v2, -1); fail(); }
	  catch (IllegalArgumentException e) {}
	  
	  try { I.checkIn(null); fail(); }
	  catch (IllegalArgumentException e) {}
	  
	  I.checkIn(v1);
	  assertTrue(I.get(v1).numOut() == 0 && I.get(v1).numRentals() == 1);
	  try { I.checkIn(v1); fail(); }
	  catch (IllegalArgumentException e) {}
	  
	  I.checkIn(v2);
	  assertTrue(I.get(v2).numOut() == 1 && I.get(v2).numRentals() == 2);
	  I.checkOut(v2);
	  assertTrue(I.get(v2).numOut() == 2 && I.get(v2).numRentals() == 3);
	  try { I.addNumOwned(v2, -1); fail(); }
	  catch (IllegalArgumentException e) {}
	  I.checkIn(v2);
	  try { I.addNumOwned(v2, -1); }
	  catch (IllegalArgumentException e) { fail(); }
  }

  public void testClear() {
	  InventorySet I = new InventorySet();
	  Video v1 = new VideoObj("AA", 2000, "BB");
	  Video v2 = new VideoObj("AA", 2000, "CC");
	  Video v3 = new VideoObj("DD", 2000, "BB");
	  
	  I.addNumOwned(v1, 8);
	  I.addNumOwned(v2, 12);
	  I.addNumOwned(v3, 3);
	  
	  I.clear();
	  assertTrue(I.size() == 0);
	  assertTrue(I.get(v1) == null);
	  assertTrue(I.get(v2) == null);
	  assertTrue(I.get(v3) == null);
  }

  public void testGet() {
	  InventorySet I = new InventorySet();
	  Video v1 = new VideoObj("AA", 2000, "BB");
	  Video v1_1 = new VideoObj("AA", 2000, "BB");
	  I.addNumOwned(v1, 2);
	  I.addNumOwned(v1_1, 1);
	  
	  Record r1 = I.get(v1);
	  Record r2 = I.get(v1_1);
	  assertTrue(r1.equals(r2));
  }

  public void testIterator1() {
	  InventorySet I = new InventorySet();
	  Video v1 = new VideoObj("AA", 2200, "BB");
	  Video v2 = new VideoObj("AA", 2500, "CC");
	  Video v3 = new VideoObj("DD", 2000, "BB");
	  Set<Video> expected = new HashSet<>();

	  I.addNumOwned(v1, 2);
	  expected.add(v1);
	  I.addNumOwned(v2, 6);
	  expected.add(v2);
	  I.addNumOwned(v3, 5);
	  expected.add(v3);
	  
	  Iterator<Record> actual = I.iterator();
	  try { actual.remove(); fail(); }
	  catch (Exception e) {}
	  
	  while (actual.hasNext())
	  {
		  Record r = actual.next();
		  assertTrue(expected.contains(r.video()));
	  }
  }
  
  public void testIterator2() {
	  InventorySet I = new InventorySet();
	  Video v1 = new VideoObj("AA", 2200, "BB");
	  Video v2 = new VideoObj("AA", 2500, "CC");
	  Video v3 = new VideoObj("DD", 2000, "BB");
	  List<Video> expected = new ArrayList<>();

	  I.addNumOwned(v1, 2);
	  I.addNumOwned(v2, 6);
	  I.addNumOwned(v3, 5);
	  
	  expected.add(v3);
	  expected.add(v1);
	  expected.add(v2);
	  
	  Iterator<Record> actual = I.iterator((Record r1, Record r2) -> {return r1.video().year() - r2.video().year();});
	  try { actual.remove(); fail(); }
	  catch (Exception e) {}
	  
	  int i = 0;
	  while (actual.hasNext())
	  {
		  Record r = actual.next();
		  assertTrue(r.video().equals(expected.get(i)));
		  i++;
	  }
  }

}
