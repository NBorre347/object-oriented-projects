package hw3;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

class fpTest { 

	@Test
	void testMap() {
		LinkedList<Integer> l = new LinkedList<>();
		for (int i=0; i < 5; i++) l.addLast(i+1); // [1,2,3,4,5] list(INTEGER)
		
	     // Integer ==> Boolean
		 // static <U,V> List<V> map(Iterable<U> l, Function<U,V> f) 
		 // List(Boolean)
		
		List<Boolean> b = fp.map(l, 
				(Integer x) -> { return x%2 ==0;});
		Boolean[] s = {false,true,false, true,false};
		assertArrayEquals(b.toArray(),s);
		
		List<Integer> u = fp.map(l, (Integer x) -> {return x+1;});
		Integer[] r = {2,3,4,5,6};
		assertArrayEquals(u.toArray(),r);
	}
	
	@Test
	void testFoldLeft() {
		
		LinkedList<Integer> l = new LinkedList<>();
		assertTrue(0 == fp.foldLeft(0, l, (Integer x, Integer y) -> {return x+y;}));
		for (int i=0; i < 10; i++) l.addLast(i+1); // [1,2,3,4,5,6,7,8,9,10]
		assertTrue(55 == fp.foldLeft(0, l, (Integer x, Integer y) -> {return x+y;}));  /* add the numbers of the list
	          0 + 1
			    1 + 2
				   3 + 3
				      6 + 4 .... 55*/
		  
		LinkedList<String> s = new LinkedList<>();
		String r = "";
		for (int i=0; i < 4; i++) {
			s.addLast(new Integer(i).toString());	
			r = r.concat(new Integer(i).toString());
		}
		assertTrue(r.equals(fp.foldLeft("", s, (String x, String y) -> {return x.concat(y);})));
		              // concatenate the strings
	}

	@Test
	void testFoldRight() {
		
		LinkedList<String> s = new LinkedList<>();
		String r = "";
		for (int i=0; i < 4; i++) {
			s.addLast(new Integer(i).toString());	
			r = new Integer(i).toString().concat(r);
		}
		System.out.println(r);
		System.out.println(fp.foldRight("", s, (String x, String y) -> {return x.concat(y);}));
		//assertTrue(r.equals(fp.foldRight("", s, (String x, String y) -> {return x.concat(y);})));
		
		LinkedList<Integer> l1 = new LinkedList<>();
		for (int j=0; j < 6; j++) 
			l1.addLast(j+1);
		// [1,2,3]
		assertTrue(-3 == fp.foldRight(0, l1, (Integer x, Integer y) -> {return x-y;}));
		// 3 -0 =3. 2-3 =-1.  1-(-1) = 2
	}
	
	@Test
	void testFilter() {
		LinkedList<Integer> l = new LinkedList<>();
		for (int i=0; i < 5; i++) l.addFirst(i+1); //[1,2,3,4,5]
		Iterable<Integer> i = fp.filter(l, (Integer x ) -> {return x%2 != 0;}); 
		  // [1,3,5]
		int u = 0;
		for (Integer a: i) u++;
		assertTrue(u==3);
		
		Iterable<Integer> j = fp.filter(l, (Integer x ) -> {return x%2 == 0;}); 
		  // [2,4]
		int u1 = 0;
		for (Integer a: j) { u1++; }
		assertTrue(u1==2);
	}

}
