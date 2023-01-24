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
    "  gap: 1rem;" +
    "  height: 100%;" +
    "}")
public class Ui5App extends App {

  @Override
  public void run() throws DwcException {

    AppPanel panel = new AppPanel();
    panel.addClassName("app-panel");

    Ui5Button defaults = new Ui5Button("Default");
    defaults.setDesign(Ui5Button.Design.DEFAULT);
    
    Ui5Button transparent = new Ui5Button("Transparent");
    transparent.setDesign(Ui5Button.Design.TRANSPARENT);

    Ui5Button positive = new Ui5Button("Positive");
    positive.setDesign(Ui5Button.Design.POSITIVE);

    Ui5Button negative = new Ui5Button("Negative");
    negative.setDesign(Ui5Button.Design.NEGATIVE);

    Ui5Button emphasized = new Ui5Button("Emphasized");
    emphasized.setDesign(Ui5Button.Design.EMPHASIZED);

    Ui5Button attention = new Ui5Button("Attention");
    attention.setDesign(Ui5Button.Design.ATTENTION);

    // loop over buttons and add click listener
    Ui5Button[] buttons = {defaults, transparent, positive, negative, emphasized, attention};
    for (Ui5Button button : buttons) {
      button.addClickListener((event) -> {
        consoleLog("Button " + button.getText() + " Clicked");
      });
      panel.add(button);
    }
  }
}
