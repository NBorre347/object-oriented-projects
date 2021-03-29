package shop.ui;

import java.util.List;

public class UIFactory {
  private UIFactory() {}
  //static private UI _UI = new PopupUI();
  static private UI _UI = new TextUI();
  static public UI ui () {
    return _UI;
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
