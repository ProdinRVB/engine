package org.demo;

import org.demo.ui5.button.Ui5Button;
import org.dwcj.App;
import org.dwcj.annotations.InlineJavaScript;
import org.dwcj.annotations.InlineStyleSheet;
import org.dwcj.controls.panels.AppPanel;
import org.dwcj.exceptions.DwcException;

@InlineJavaScript(value = "ui5.js", local = true)
@InlineStyleSheet(value = "" +
    ".app-panel{" +
    "  overflow-y: auto;" +
    "  display: flex;" +
    "  align-items: center;" +
    "  justify-content: center;" +
    "  flex-direction: column;" +
    "  gap: 1rem;" +
    "  height: 100%;" +
    "}")
public class Ui5App extends App {

  @Override
  public void run() throws DwcException {

    AppPanel panel = new AppPanel();
    panel.addClassName("app-panel");

    Ui5Button button = new Ui5Button();
    button.setText("Click Me");
    button.addClickListener((event) -> {
      consoleLog("Button Clicked");
    });

    panel.add(button);

    panel.add(panel);
  }
}
