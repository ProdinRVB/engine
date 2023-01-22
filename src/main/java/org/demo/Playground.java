package org.demo;

import java.util.Arrays;
import java.util.List;

import org.demo.shoelace.components.button.SlButton;
import org.demo.shoelace.components.select.SlOption;
import org.demo.shoelace.components.select.SlSelect;
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

    SlSelect select = new SlSelect();
    select.setStyle("width", "300px");
    select.add("Email", "Phone", "Chat");
    select.setMultiple(true);
    select.setSelected("Phone", "Chat", "other");
    // List<SlOption> values = select.getSelected();
    // for (SlOption value : values) {
    //   msgbox(String.valueOf(value.getValue()));
    // }
    select.addShowListener(e -> {
      List<SlOption> values = select.getSelected();
      for (SlOption value : values) {
        consoleLog("Show: " + (value.getValue()));
      }
    });

    SlButton button = new SlButton("Log selected value");
    button.addClickListener(e -> {
      List<SlOption> values2 = select.getSelected();
      for (SlOption value : values2) {
        consoleLog(value.getValue());
      }
    });

    panel.add(select, button);
  }

}
