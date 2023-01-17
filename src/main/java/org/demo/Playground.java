package org.demo;

import org.demo.shoelace.components.button.SlButton;
import org.demo.shoelace.components.button.events.SlButtonClickEvent;
import org.demo.shoelace.components.dialog.SlDialog;
import org.demo.shoelace.components.dialog.events.SlDialogCloseEvent;
import org.demo.shoelace.components.input.SlInput;
import org.demo.shoelace.components.input.events.SlInputChangeEvent;
import org.demo.shoelace.components.input.events.SlInputModifiedEvent;
import org.demo.shoelace.components.radio.SlRadio;
import org.demo.shoelace.components.radiobutton.SlRadioButton;
import org.demo.shoelace.components.radiogroup.SlRadioGroup;
import org.demo.shoelace.components.radiogroup.events.SlRadioGroupChangeEvent;
import org.dwcj.App;
import org.dwcj.annotations.Attribute;
import org.dwcj.annotations.InlineStyleSheet;
import org.dwcj.annotations.JavaScript;
import org.dwcj.annotations.StyleSheet;
import org.dwcj.controls.label.Label;
import org.dwcj.controls.panels.AppPanel;
import org.dwcj.exceptions.DwcException;

@StyleSheet(id = "sl-css", top = true, url = "https://cdn.jsdelivr.net/npm/@shoelace-style/shoelace@2.0.0-beta.88/dist/themes/light.css")
@JavaScript(id = "sl-js", top = true, url = "https://cdn.jsdelivr.net/npm/@shoelace-style/shoelace@2.0.0-beta.88/dist/shoelace.js", attributes = {
    @Attribute(name = "type", value = "module")
})
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
public class Playground extends App {

  @Override
  public void run() throws DwcException {

    AppPanel panel = new AppPanel();
    panel.addClassName("app-panel");

    // SlInput input = new SlInput();
    // input.setPlaceholder("username ...");
    // input.setPrefix("<sl-icon name='house'></sl-icon>");
    // input.setHelpText("this is a message");
    // input.addChangeListener((SlInputChangeEvent event) -> {
    // App.consoleLog("Input changed: " + event.getValue());
    // });

    // SlRadioGroup radioGroup = new SlRadioGroup("Select an option", "Choose the
    // most appropriate option.");
    // SlRadioButton r1 = new SlRadioButton("Option 1", "option1");
    // SlRadioButton r2 = new SlRadioButton("Option 2", "option2");
    // SlRadioButton r3 = new SlRadioButton("Option 3", "option3");
    // radioGroup.add(r1, r2, r3);

    SlDialog dialog = new SlDialog("Dialog");
    SlButton close = new SlButton("Close");

    close.addClickListener((SlButtonClickEvent event) -> {
      dialog.close();
      // App.msgbox("Dialog closed");
    });

    SlInput input = new SlInput();
    input.setPlaceholder("I will have focus when the dialog is opened");
    input.setAutoFocus(true);

    dialog.getContent().add(input);
    dialog.getFooter().add(close);
    dialog.addCloseListener((SlDialogCloseEvent event) -> {
      App.consoleLog("Dialog closed");
    });

    SlButton button = new SlButton("Button");
    button.addClickListener((SlButtonClickEvent event) -> {
      dialog.open();
    });

    panel.add(button, dialog);
  }

}
