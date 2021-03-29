package shop.ui;

import java.util.List;

public class UIFactory {
  private UIFactory() {}
  static private UI _UI1 = new PopupUI();
  static private UI _UI2 = new TextUI();
  static public UI popupUI () {
    return _UI1;
  }
  static public UI textUI () {
	    return _UI2;
	  }
  
  static public UIBuilder<UIFormTest, Form> newFormBuilder()
  {
	  return new UIBuilderConcrete<UIFormTest, Form>(
			new CrInt<UIFormTest, Form>() {
				public UIForm out(String _head, List<Pair<UIFormTest>> _list)
				{
					return new UIForm(_head, _list);
				}
			}
		);
  }
  
  static public UIBuilder<UIMenuAction, Menu> newMenuBuilder()
  {
	  return new UIBuilderConcrete<UIMenuAction, Menu>(
			new CrInt<UIMenuAction, Menu>() {
				public UIMenu out(String _head, List<Pair<UIMenuAction>> _list)
				{
					return new UIMenu(_head, _list);
				}
			}
		);
  }
  
  /*static public UIBuilder<UIFormTest, UIForm> newFormBuilder()
  {
	  return new UIBuilderConcrete<UIFormTest, UIForm>(
			new CrInt<UIFormTest, UIForm>() {
				public UIForm out(String _head, List<Pair<UIFormTest>> _list)
				{
					return new UIForm(_head, _list);
				}
			}
		);
  }
  
  static public UIBuilder<UIMenuAction, UIMenu> newMenuBuilder()
  {
	  return new UIBuilderConcrete<UIMenuAction, UIMenu>(
			new CrInt<UIMenuAction, UIMenu>() {
				public UIMenu out(String _head, List<Pair<UIMenuAction>> _list)
				{
					return new UIMenu(_head, _list);
				}
			}
		);
  }*/
  
}
