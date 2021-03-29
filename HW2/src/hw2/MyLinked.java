package hw2;
public class MyLinked {
	
    static class Node {
        public Node() { }
        public double item;
        public Node next;
    }

    int N;
    Node first;
    
    public MyLinked () {
        first = null;
        N = 0;
        assert checkInvariants ();
    }


    private boolean checkInvariants() {
        assert((N != 0) || (first == null));
        Node x = first;
        for (int i = 0; i < N; i++) {
            if (x==null) {
                return false;
            }
            x = x.next;
        }
        assert(x == null);
        return true;
    }

    public boolean isEmpty () { return first == null; }
    
    public int size () { return N; }
    
    // Just added for the sake of testing
    // Returns the node at the given index
    public Node get (int index)
    {
    	if (index < 0 || index >= N) throw new IllegalArgumentException ();
    	
    	Node n = first;
    	for (int i = 0; i < index; i++)
    	{
    		assert (n != null);
    		n = n.next;
    	}
    	return n;
    }
    
    public Node getFirst ()
    	{ return first; }
    
    // returns first index of a given value or -1 if none exist
    public int find(double val)
    {
    	Node n = first;
    	int i = 0;
    	while (n != null)
    	{
    		if (n.item == val)
    			{ return i; }
    		i++;
    		n = n.next;
    	}
    	return -1;
    }
    
    public void add (double item) {
        Node newfirst = new Node ();
        newfirst.item = item;
        newfirst.next = first;
        first = newfirst;
        N++;
    }


    // delete the kth element
    public void delete (int k) {

        if (k < 0 || k >= N) throw new IllegalArgumentException ();
        assert (first != null);
    	assert(N >= 0);
        
        if (k == 0)
        {
        	Node last = first;
        	first = first.next;
        	last.next = null;
        	N--;
        	return;
        }
        
        Node n = first;
        Node prev = null;
        for (int i = 0; i < k; i++)
        {
        	prev = n;
        	n = n.next;
        	assert (prev != null);
        	assert (n != null);
            assert checkInvariants ();
        }
        prev.next = n.next;
        n.next = null;
        N--;
        assert checkInvariants ();
    }

    // reverse the list "in place"... without creating any new nodes
    public void reverse () {

        assert (N >= 0);
    	
    	if (first == null)
    		{ return; }
    	
    	Node[] temp = new Node[N];
    	Node running = first;
    	for (int i = 0; i < N; i++)
    	{
    		temp[i] = running;
    		running = running.next;
            assert checkInvariants ();
    	}
    	
    	Node prev = null;
    	for (int i = 0; i < N; i++)
    	{
    		temp[i].next = prev;
    		prev = temp[i];
    	}
    	first  = temp[N-1];
    	assert checkInvariants ();
    }

    // remove 
    public void remove (double item) {
    	
    	assert(N >= 0);
    	
    	Node n = first;
    	Node prev = null;
    	while (n != null)
    	{
    		if (n.item == item)
    		{
    			if (n == first)
    			{
    				first = first.next;
    				n.next = null;
    				N--;
    	    		n = first;
    	            assert checkInvariants ();
    			}
    			else
    			{
    				assert(prev != null);
    				assert(n != null);
    				prev.next = n.next;
    				Node nex = n.next;
    				n.next = null;
    				n = nex;
    				N--;
    	            assert checkInvariants ();
    			}
    		}
    		else
    		{
        		prev = n;
        		n = n.next;
                assert checkInvariants ();
    		}
    	}
    	
        assert checkInvariants ();
    }
    
    public String toString() 
    {
    	Node n = first;
    	String thing = "{ ";
    	for (int i = 0; i < N; i++)
        {
        	thing = thing + "[" + i + "] : " + n.item;
        	if (n.next != null)
        		{ thing = thing + " -> "; }
        	n = n.next;
        }
    	thing = thing + " }";
    	return thing;
    }

// TODO: CONVERT THE FOLLOWING TO JUNIT TESTS

    /*private static void testDelete () {
        MyLinked b = new MyLinked ();
        b.add (1);
        print ("singleton", b);
        b.delete (0);
        print ("deleted", b);
        for (double i = 1; i < 13; i++) {
            b.add (i);
        }
        print ("bigger list", b);
        b.delete (0);
        print ("deleted at beginning", b);
        b.delete (10);
        print ("deleted at end", b);
        b.delete (4);
        print ("deleted in middle", b);
    }
    private static void testReverse () {
        MyLinked b = new MyLinked ();
        b.reverse ();
        print ("reverse empty", b);
        b.add (1);
        print ("singleton", b);
        b.reverse ();
        print ("reverse singleton", b);
        b.add (2);
        print ("two", b);
        b.reverse ();
        print ("reverse two", b);
        b.reverse ();
        print ("reverse again", b);
        for (double i = 3; i < 7; i++) {
            b.add (i);
            b.add (i);
        }
        print ("bigger list", b);
        b.reverse ();
        print ("reversed", b);
    }
    private static void testRemove () {
        MyLinked b = new MyLinked ();
        b.remove (4);
        print ("removed 4 from empty", b);
        b.add (1);
        b.remove (4);
        print ("removed 4 from singelton", b);
        b.remove (1);
        print ("removed 1 from singelton", b);
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
        print ("longer list", b);
        b.remove (9);
        print ("removed all 9s", b); // does nothing
        b.remove (3);
        print ("removed all 3s", b);
        b.remove (1);
        print ("removed all 1s", b);
        b.remove (4);
        print ("removed all 4s", b);
        b.remove (2);
        print ("removed all 2s", b); // should be empty
    }*/

}

































