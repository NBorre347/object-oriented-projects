package shop.main;

import shop.ui.Form;
import shop.ui.Menu;
import shop.ui.UI;
import shop.ui.UIMenuAction;
import shop.ui.UIError;
import shop.ui.UIFormTest;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Video;
import shop.data.Record;
import shop.command.Command;
import java.util.Iterator;
import java.util.List;

interface STATE {
	public STATE run();
}

enum STATES implements STATE {
	STARTSTATE()
	{
		public STATE run() {
			return MAINMENU;
		}
	}
	,
	MAINMENU()
	{
		public STATE run()
		{
			_s = DEFAULT;
			ui.processMenu(mainMenu);
			return _s;
		}
	}
	,
	DEFAULT()
	{
		public STATE run()
		{
			ui.displayError("doh!");
			return MAINMENU;
		}
	}
	,
	VIDEOADD()
	{
		public STATE run()
		{
			String[] result1 = ui.processForm(vidForm);
	        Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

	        String[] result2 = ui.processForm(numForm);
	                                             
	        Command c = Data.newAddCmd(inv, v, Integer.parseInt(result2[0]));
	        if (! c.run()) {
	          return ERROR;
	        }
			return MAINMENU;
		}
	}
	,
	CHECKIN()
	{
		public STATE run()
		{
			String[] result1 = ui.processForm(vidForm);
            Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
            
            Command c = Data.newInCmd(inv, v);
            if (! c.run()) {
              return ERROR;
            }
            return MAINMENU;
		}
	}
	,
	CHECKOUT()
	{
		public STATE run()
		{
			String[] result1 = ui.processForm(vidForm);
            Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);
            
            Command c = Data.newOutCmd(inv, v);
            if (! c.run()) {
              return ERROR;
            }
            return MAINMENU;
		}
	}
	,
	PRINT()
	{
		public STATE run()
		{
			ui.displayMessage(inv.toString());
			return MAINMENU;
		}
	}
	,
	CLEAR()
	{
		public STATE run()
		{
			if (!Data.newClearCmd(inv).run()) {
	          return ERROR;
	        }
			return MAINMENU;
		}
	}
	,
	UNDO()
	{
		public STATE run()
		{
			if (!Data.newUndoCmd(inv).run()) {
	          return ERROR;
	        }
			return MAINMENU;
		}
	}
	,
	REDO()
	{
		public STATE run()
		{
			if (!Data.newRedoCmd(inv).run()) {
	          return ERROR;
	        }
			return MAINMENU;
		}
	}
	,
	TOP10()
	{
		public STATE run()
		{
			Iterator<Record> sorted = inv.iterator((Record r1, Record r2) -> {return r2.numRentals() - r1.numRentals();});
        	int x = 1;
        	StringBuilder out = new StringBuilder();
        	while (sorted.hasNext() && x <= 10)
        	{
        		out.append("  ");
        		out.append(sorted.next().toString());
        		out.append("\n");
        		x++;
        	}
        	ui.displayMessage(out.toString());
        	return MAINMENU;
		}
	}
	,
	EXIT()
	{
		public STATE run() 
		{
			_s = DEFAULT;
			ui.processMenu(exitMenu);
			return _s;
		}
	}
	,
	INIT()
	{
		public STATE run()
		{
			Data.newAddCmd(inv, Data.newVideo("a", 2000, "m"), 1).run();
	        Data.newAddCmd(inv, Data.newVideo("b", 2000, "m"), 2).run();
	        Data.newAddCmd(inv, Data.newVideo("c", 2000, "m"), 3).run();
	        Data.newAddCmd(inv, Data.newVideo("d", 2000, "m"), 4).run();
	        Data.newAddCmd(inv, Data.newVideo("e", 2000, "m"), 5).run();
	        Data.newAddCmd(inv, Data.newVideo("f", 2000, "m"), 6).run();
	        Data.newAddCmd(inv, Data.newVideo("g", 2000, "m"), 7).run();
	        Data.newAddCmd(inv, Data.newVideo("h", 2000, "m"), 8).run();
	        Data.newAddCmd(inv, Data.newVideo("i", 2000, "m"), 9).run();
	        Data.newAddCmd(inv, Data.newVideo("j", 2000, "m"), 10).run();
	        Data.newAddCmd(inv, Data.newVideo("k", 2000, "m"), 11).run();
	        Data.newAddCmd(inv, Data.newVideo("l", 2000, "m"), 12).run();
	        Data.newAddCmd(inv, Data.newVideo("m", 2000, "m"), 13).run();
	        Data.newAddCmd(inv, Data.newVideo("n", 2000, "m"), 14).run();
	        Data.newAddCmd(inv, Data.newVideo("o", 2000, "m"), 15).run();
	        Data.newAddCmd(inv, Data.newVideo("p", 2000, "m"), 16).run();
	        Data.newAddCmd(inv, Data.newVideo("q", 2000, "m"), 17).run();
	        Data.newAddCmd(inv, Data.newVideo("r", 2000, "m"), 18).run();
	        Data.newAddCmd(inv, Data.newVideo("s", 2000, "m"), 19).run();
	        Data.newAddCmd(inv, Data.newVideo("t", 2000, "m"), 20).run();
	        Data.newAddCmd(inv, Data.newVideo("u", 2000, "m"), 21).run();
	        Data.newAddCmd(inv, Data.newVideo("v", 2000, "m"), 22).run();
	        Data.newAddCmd(inv, Data.newVideo("w", 2000, "m"), 23).run();
	        Data.newAddCmd(inv, Data.newVideo("x", 2000, "m"), 24).run();
	        Data.newAddCmd(inv, Data.newVideo("y", 2000, "m"), 25).run();
	        Data.newAddCmd(inv, Data.newVideo("z", 2000, "m"), 26).run();
	        
	        return MAINMENU;
		}
	}
	,
	ERROR()
	{
		public STATE run()
		{
			ui.displayError("Command failed");
			return MAINMENU;
		}
	}
	,
	EXITEDSTATE() 
	{
		public STATE run() {return this;}
	};
	
	static Inventory inv;
	static UI ui;
	static STATE _s = MAINMENU;
	
	static Form vidForm = new Form() {
		private final String _heading = "Enter Video";
		protected final List<UIFormTest> _menu = List.of(
				new UIFormTest() {
					public boolean run(String input) {
				          return ! "".equals(input.trim());
				        }
			      },
				new UIFormTest() {
			          public boolean run(String input) {
			            try {
			              int i = Integer.parseInt(input);
			              return i > 1800 && i < 5000;
			            } catch (NumberFormatException e) {
			              return false;
			            }
			          }
			        },
				new UIFormTest() {
			            public boolean run(String input) {
			              return ! "".equals(input.trim());
			            }
			          }
				);
		protected final List<String> _prompt = List.of("Title", "Year", "Director");
		
		public int size() {
			return _menu.size();
		}
		public String getHeading() {
			return _heading;
		}
		public String getPrompt(int i) {
			return _prompt.get(i);
		}
		public boolean checkInput(int i, String input)
		{
			if (null == _menu.get(i))
				return true;
			return _menu.get(i).run(input);
		}
	};
	
	static Form numForm = new Form() {
		
		public int size() {
			return 1;
		}
		public String getHeading() {
			return "";
		}
		public String getPrompt(int i) {
			return "Number of copies to add/remove";
		}
		public boolean checkInput(int i, String input)
		{
			try {
	            Integer.parseInt(input);
	            return true;
	          } catch (NumberFormatException e) {
	            return false;
	          }
		}
	};
	
	static Menu mainMenu = new Menu() {
		protected final List<UIMenuAction> _menu = List.of(
				new UIMenuAction() { public void run() { _s = DEFAULT; }},
				new UIMenuAction() { public void run() { _s = VIDEOADD; }},
				new UIMenuAction() { public void run() { _s = CHECKIN; }},
				new UIMenuAction() { public void run() { _s = CHECKOUT; }},
				new UIMenuAction() { public void run() { _s = PRINT; }},
				new UIMenuAction() { public void run() { _s = CLEAR; }},
				new UIMenuAction() { public void run() { _s = UNDO; }},
				new UIMenuAction() { public void run() { _s = REDO; }},
				new UIMenuAction() { public void run() { _s = TOP10; }},
				new UIMenuAction() { public void run() { _s = EXIT; }},
				new UIMenuAction() { public void run() { _s = INIT; }}
				);
		protected final List<String> _prompt = List.of(
				"Default",
				"Add/Remove copies of a video",
				"Check in a video",
				"Check out a video",
				"Print the inventory",
				"Clear the inventory",
				"Undo",
				"Redo",
				"Print top ten all time rentals in order",
				"Exit",
				"Initialize with bogus contents"
				);
		
		public int size() {
			return _menu.size();
		}
		public String getHeading() {
			return "Bob's Video";
		}
		public String getPrompt(int i) {
			return _prompt.get(i);
		}
		public void runAction(int i) {
			_menu.get(i).run();
		}
	};
	
	static Menu exitMenu = new Menu() {
		protected final List<UIMenuAction> _menu = List.of(
				new UIMenuAction() { public void run() { _s = DEFAULT; }},
				new UIMenuAction() { public void run() { _s = EXITEDSTATE; }},
				new UIMenuAction() { public void run() { _s = MAINMENU; }}
				);
		protected final List<String> _prompt = List.of(
				"Default",
				"Yes",
				"No"
				);
		
		public int size() {
			return _menu.size();
		}
		public String getHeading() {
			return "Are you sure you want to exit?";
		}
		public String getPrompt(int i) {
			return _prompt.get(i);
		}
		public void runAction(int i) {
			_menu.get(i).run();
		}
	};
	
	public abstract STATE run();

	
}

class Control {
  private Inventory _inventory;
  private UI _ui;
  
  private STATE _state;
  
  Control(Inventory inventory, UI ui) {
	  _inventory = inventory;	
	  _ui = ui;
	  
	  STATES.inv = _inventory;
	  STATES.ui = _ui;
	  _state = STATES.STARTSTATE;
  }
  
  void run() {
	  try {
	      while (_state != STATES.EXITEDSTATE) {
	    	  _state = _state.run();
	      }
	  } catch (UIError u) {
		  _state = STATES.EXITEDSTATE;
	  }
  }
}
