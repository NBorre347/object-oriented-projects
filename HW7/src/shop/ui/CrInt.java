package shop.ui;

import java.util.List;

interface CrInt<X, Y extends UIElement<X>> {
	public Y out(String _head, List<Pair<X>> _list);
}