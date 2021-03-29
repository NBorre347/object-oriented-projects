package shop.ui;

public interface UI {
  public void processMenu(Menu menu);
  public String[] processForm(Form form);
  public void displayMessage(String message);
  public void displayError(String message);
}
