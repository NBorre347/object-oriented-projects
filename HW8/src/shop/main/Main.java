package shop.main;

import shop.ui.UIFactory;
import shop.ui.UI;
import shop.data.Data;

public class Main {
  private Main() {}
  public static void main(String[] args) {
    UI ui;
    
    ui = UIFactory.textUI();
    Control control = new Control(Data.newInventory(), ui);
    control.run();
  }
}
