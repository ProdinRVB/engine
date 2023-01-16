package org.demo;

import org.demo.shoelace.components.button.SlButton;
import org.demo.shoelace.components.button.events.SlButtonClickEvent;
import org.demo.shoelace.components.input.SlInput;
import org.demo.shoelace.components.input.events.SlInputModifiedEvent;
import org.dwcj.App;
import org.dwcj.annotations.Attribute;
import org.dwcj.annotations.InlineStyleSheet;
import org.dwcj.annotations.JavaScript;
import org.dwcj.annotations.StyleSheet;
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

    SlInput input = new SlInput();
    input.setPlaceholder("username ...");
    input.setPrefix("<sl-icon name='house'></sl-icon>");
    input.setHelpText("this is a message");
    input.addModifiedListener((SlInputModifiedEvent event) -> {
      App.consoleLog("Input changed: " + event.getValue());
    });

    SlButton button = new SlButton("Button");
    button.addClickListener((SlButtonClickEvent event) -> {
      input.focus();
      input.setSelectionRange(0,3);
    });

    panel.add(input,button);
  }

}
