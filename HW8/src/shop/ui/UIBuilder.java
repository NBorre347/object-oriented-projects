package shop.ui;

import java.util.ArrayList;
import java.util.List;

public interface UIBuilder<X, Y extends UIElement<X>> {
	public void add(String prompt, X action);
	public Y toUI(String heading);
}

class UIBuilderConcrete<X, Y extends UIElement<X>> implements UIBuilder<X,Y> {
	private final List<Pair<X>> _list = new ArrayList<Pair<X>>();
	private CrInt<X,Y> _C;
	
	public UIBuilderConcrete(CrInt<X,Y> C) { _C = C; }
	
	public void add(String prompt, X action)
	{
		if (null == action)
			throw new IllegalArgumentException();
		
		_list.add(new Pair<X>(prompt, action));
	}
	
	public Y toUI(String heading)
	{
		if (null == heading)
		      throw new IllegalArgumentException();
		
		if (_list.size() < 1)
		      throw new IllegalStateException();
		
		return _C.out(heading, _list);
	}
}

/*class CrForm implements CrInt<UIFormTest, UIForm1> {
public UIForm1 out(String _head, List<Pair<UIFormTest>> _list)
{
	return new UIForm1(_head, _list);
}
}

class CrMenu implements CrInt<UIMenuAction, UIMenu1> {
public UIMenu1 out(String _head, List<Pair<UIMenuAction>> _list)
{
	return new UIMenu1(_head, _list);
}
}*/
