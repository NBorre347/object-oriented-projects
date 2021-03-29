package hw3;

import java.util.List;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.Comparator;

import java.util.ArrayList;

public class fp {
	
// f takes U to V.. ie. f.apply(U): V
	
static <U,V> List<V> map(Iterable<U> l, Function<U,V> f) {
	// walk through the U's
	// use f at every stage f.apply
	// construct list of V's
	
	List<V> Lf = new ArrayList<V>();
	
	for (U x: l) {
		V y = f.apply(x);
		Lf.add(y);
	}
	
	return Lf;
}


static <U,V> V foldLeft(V e, Iterable<U>l, BiFunction<V,U,V> f){
	// walk through the U's [u1,u2, ..,un]
	//                       e
	// use f at every stage v1= f.apply(e,u1)
	//                         v2= f.apply(v1,u2)
	//						    v3= f.apply(v2,u3)..
	// return the last v
	
	V running = e;
	V last = e;
	
	for (U x : l)
	{
		running = f.apply(last, x);
		last = running;
	}
	
	return running;
}


// similar to above
// but from the right
//     vn=  f(un,e)
//          vn-1 = f(un-1,vn)
// ..
// return the first v
static <U,V> V foldRight(V e, List<U>l, BiFunction<U,V,V> f){
	
	V running = e;
	V last = e;

	//for (int i = 0; i < l.size(); i++) 
	for (int i = l.size()-1; i >= 0; i--) 
	{
		U x = l.get(i);
		running = f.apply(x, last);
		last = running;
	}
	
	return running;
}



static <U> Iterable<U> filter(Iterable<U> l, Predicate<U> p){
	
	List<U> L = new ArrayList<U>();
	
	for (U x : l)
	{
		boolean z = p.test(x);
		if (z)
		{
			L.add(x);
		}
	}
	
	return L;
}

static <U> U minVal(Iterable<U> l, Comparator<U> c){
	// write using fold.  No other loops or recursion permitted. 

	U res = foldLeft(l.iterator().next(), l, (U x, U y) -> {
		if (c.compare(x, y) < 0) {return x;} 
		else {return y;}
	});
	
	return res;
}

static <U extends Comparable<U>> int minPos(Iterable<U> l){
	// write using fold.  No other loops or recursion permitted. 

	int pos = 0;
	int i = 0;
	U b = l.iterator().next();
	
	helper<U> temp = new helper<U>(i, pos, b);
	
	helper<U> ret = foldLeft(temp, l, (helper<U> x, U a) -> {
		if (a.compareTo(x.item) < 0) { x.pos = x.i; x.item = a; }
		else {  }
		x.i++;
		return x;
	});
	
	return ret.pos;
}

static <U extends Comparable<U>> int minPosIterate(Iterable<U> l){
	// Debugging function meant to test consistency with normal solution
	// Disregard when grading
	
	int pos = 0;
	int i = 0;
	U min = l.iterator().next();
	
	for (U x : l)
	{
		if (x.compareTo(min) < 0)
		{
			pos = i;
			min = x;
		}
		else
		{
			// do nothing
		}
		i++;
	}
	
	return pos;
}

	public static void main(String[] args) {
		// (1) Use map to implement the following behavior (described in Python).  i.e given a List<T> create a List<Integer> of the hashes of the objects.
		// names = ['Mary', 'Isla', 'Sam']
		// for i in range(len(names)):
		       // names[i] = hash(names[i])
		
		List<String> names = new ArrayList<String>(List.of("Mary", "Isla", "Sam"));
		List<Integer> act = map(names, (String x) -> {return x.hashCode();});
		
		List<Integer> exp = new ArrayList<Integer>();
		for (int i = 0; i < names.size(); i++)
		{
			exp.add(names.get(i).hashCode());
		}
		
		System.out.println("1: " + act.equals(exp));
		
		// (2) Use foldleft to calculate the sum of a list of integers.
		// i.e write a method: int sum(List<Integer> l)
		
		List<Integer> L = new ArrayList<Integer>(List.of(1,2,3,4,5,6,7,8,9));
		int X = foldLeft(0, L, (Integer x, Integer y) -> {return x + y;});
		System.out.println("2: " + X + " =? 45");
		
		// (3) Use foldRight to concatenate a list of strings i.e write a method
		// String s (List<String> l)
		
		List<String> SL = new ArrayList<String>(List.of("Left", "Middle", "Right"));
		String S = foldRight("", SL, (String x, String y) -> {return x.concat(y);});
		System.out.println("3: " + S);
		
		// (4) consider an array of Persons. Use filter to 
		// print the names of the Persons whose salary is
		// greater than 100000
		
		Person p1 = new Person(150000, "Sam");
		Person p2 = new Person(50000, "Pam");
		Person p3 = new Person(80000, "Cam");
		Person p4 = new Person(120000, "Clam");
		Person p5 = new Person(180000, "Slam");
		Person p6 = new Person(75000, "Salami");
		
		List<Person> P = new ArrayList<Person>(List.of(p1, p2, p3, p4, p5, p6));
		
		Iterable<Person> Pnew = filter(P, (Person x) -> {return x.getSalary() > 100000;});
		
		System.out.print("4: ");
		for (Person i : Pnew)
		{
			System.out.print(i.name() + ", ");
		}
		System.out.println();
		
		// (5) Use minVal to calculate the minimum of a List of 
		//       Integers
		
		L = new ArrayList<Integer>(List.of(10,6,2,5,3,1,8,9,7,4));
		int k = minVal(L, (Integer x, Integer y) -> {return x - y;});
		System.out.println("5: " + k + " =? 1");
		
        // (6) Use minVal to calculate the maximum of a List of 
		//       Integers
		
		k = minVal(L, (Integer x, Integer y) -> {return y - x;});
		System.out.println("6: " + k + " =? 10");
		
		// (7)  Use minPos to calculate the position of the
		// minimum in  a List of  Integers
		
		int a = minPos(L);
		int b = minPosIterate(L);
		System.out.println("7: " + a + " =? " + b);

		// (8)  Use minPos to calculate the position of the
		// minimum in  a List of  String
		
		List<String> A = new ArrayList<>(List.of("Hksbd", "Ulkjc", "Clkytgs", "Enhyfs", "Bhhsaaw", "Blsbtcf"));
		a = minPos(A);
		b = minPosIterate(A);
		System.out.println("8: " + a + " =? " + b);
		
	}

}

class helper<U extends Comparable<U>>
{
	int i;
	int pos;
	U item;
	public helper(int a, int b, U c) { i = a; pos = b; item = c; }
}

class Person{
	final int salary;
	final String name;
	
	Person(int salary, String name){
		this.salary = salary;
		this.name = name;
	}
	
	int getSalary() { return salary; }
	String name() { return name;}
	
}