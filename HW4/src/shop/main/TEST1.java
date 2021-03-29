package shop.main;

import junit.framework.Assert;
import junit.framework.TestCase;
import shop.command.Command;
import shop.data.Data;
import shop.data.Record;
import shop.data.Video;
import shop.data.Inventory;
import java.util.Iterator;

// TODO:
// write an integration test that tests the data classes.
// add in some videos, check out, check in, delete videos, etc.
// check that errors are reported when necessary.
// check that things are going as expected.
public class TEST1 extends TestCase {
  private Inventory _inventory = Data.newInventory();
  public TEST1(String name) {
    super(name);
  }
  private void expect(Video v, String s) {
    assertEquals(s,_inventory.get(v).toString());
  }
  private void expect(Record r, String s) {
    assertEquals(s,r.toString());
  }
  public void test1() {
    Command clearCmd = Data.newClearCmd(_inventory);
    clearCmd.run();
    
    Video v1 = Data.newVideo("Title1", 2000, "Director1");
    assertEquals(0, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    assertEquals(1, _inventory.size());
    expect(v1,"Title1 (2000) : Director1 [10,0,0]");
    
    // TODO
    
    Video v2 = Data.newVideo("AA", 2200, "BB");
    assertTrue(_inventory.size() == 1);
    assertTrue(Data.newAddCmd(_inventory, v2, 2).run());
    assertTrue(_inventory.size() == 2);
    expect(v2,"AA (2200) : BB [2,0,0]");
    
    assertTrue(Data.newOutCmd(_inventory, v1).run());
    expect(v1,"Title1 (2000) : Director1 [10,1,1]");
    assertTrue(Data.newOutCmd(_inventory, v1).run());
    expect(v1,"Title1 (2000) : Director1 [10,2,2]");
    assertTrue(Data.newInCmd(_inventory, v1).run());
    expect(v1,"Title1 (2000) : Director1 [10,1,2]");
    assertTrue(Data.newInCmd(_inventory, v1).run());
    expect(v1,"Title1 (2000) : Director1 [10,0,2]");
    assertFalse(Data.newInCmd(_inventory, v1).run());
    expect(v1,"Title1 (2000) : Director1 [10,0,2]");
    
    assertTrue(Data.newOutCmd(_inventory, v2).run());
    expect(v2,"AA (2200) : BB [2,1,1]");
    assertTrue(Data.newOutCmd(_inventory, v2).run());
    expect(v2,"AA (2200) : BB [2,2,2]");
    assertFalse(Data.newOutCmd(_inventory, v2).run());
    expect(v2,"AA (2200) : BB [2,2,2]");
    
    assertTrue(Data.newAddCmd(_inventory, v1, -5).run());
    expect(v1,"Title1 (2000) : Director1 [5,0,2]");
    assertTrue(Data.newAddCmd(_inventory, v1, -4).run());
    expect(v1,"Title1 (2000) : Director1 [1,0,2]");
    assertFalse(Data.newAddCmd(_inventory, v1, -2).run());
    expect(v1,"Title1 (2000) : Director1 [1,0,2]");
    
    assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    expect(v1,"Title1 (2000) : Director1 [6,0,2]");
    assertTrue(Data.newOutCmd(_inventory, v1).run());
    assertTrue(Data.newOutCmd(_inventory, v1).run());
    assertTrue(Data.newOutCmd(_inventory, v1).run());
    assertTrue(Data.newOutCmd(_inventory, v1).run());
    expect(v1,"Title1 (2000) : Director1 [6,4,6]");
    assertFalse(Data.newAddCmd(_inventory, v1, -3).run());
    expect(v1,"Title1 (2000) : Director1 [6,4,6]");
    assertTrue(Data.newInCmd(_inventory, v1).run());
    expect(v1,"Title1 (2000) : Director1 [6,3,6]");
    assertTrue(Data.newAddCmd(_inventory, v1, -3).run());
    expect(v1,"Title1 (2000) : Director1 [3,3,6]");
    
	Iterator<Record> I1 = _inventory.iterator((Record r1, Record r2) -> {return r1.video().year() - r2.video().year();});
	expect(I1.next(), "Title1 (2000) : Director1 [3,3,6]");
	expect(I1.next(), "AA (2200) : BB [2,2,2]");
	
	Iterator<Record> I2 = _inventory.iterator((Record r1, Record r2) -> {return r2.video().year() - r1.video().year();});
	expect(I2.next(), "AA (2200) : BB [2,2,2]");
	expect(I2.next(), "Title1 (2000) : Director1 [3,3,6]");
  }
}
