package shop.ui;

import java.util.List;

abstract class UIConcrete<T> implements UIElement<T>{
	private final String _heading;
	protected final List<Pair<T>> _menu;
	
	public UIConcrete(String heading, List<Pair<T>> menu)
	{
		_menu = menu;
		_heading = heading;
	}
	
	public int size() {
		return _menu.size();
	}
	public String getHeading() {
		return _heading;
	}
	public String getPrompt(int i) {
		return _menu.get(i).prompt;
	}
	
	//abstract public boolean runAction(int i, String input);
}

final class UIForm extends UIConcrete<UIFormTest> implements Form/*<UIFormTest>*/ {

	public UIForm(String heading, List<Pair<UIFormTest>> menu) {
		super(heading, menu);
	}
	
	public boolean checkInput(int i, String input)
	{
		if (null == _menu.get(i))
			return true;
		return _menu.get(i).action.run(input);
	}
}

final class UIMenu extends UIConcrete<UIMenuAction> implements Menu/*<UIMenuAction>*/ {
	public UIMenu(String heading, List<Pair<UIMenuAction>> menu) {
		super(heading, menu);
	}
	
	public void runAction(int i)
	{
		_menu.get(i).action.run();
	}
}