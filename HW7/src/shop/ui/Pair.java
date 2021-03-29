package shop.ui;

final class Pair<T> {
	final String prompt;
    final T action;

    Pair(String thePrompt, T theAction) {
      prompt = thePrompt;
      action = theAction;
    }
}
