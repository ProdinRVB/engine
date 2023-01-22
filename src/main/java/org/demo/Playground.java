package org.demo;

import java.awt.Color;

import org.demo.shoelace.components.button.SlButton;
import org.demo.shoelace.components.colorpicker.SlColorPicker;
import org.demo.shoelace.components.details.SlDetails;
import org.demo.shoelace.components.details.SlDetailsGroup;
import org.demo.shoelace.utils.CssColor;
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

    SlColorPicker colorPicker = new SlColorPicker(Color.RED);
    colorPicker.setHoist(true);
    colorPicker.addChangeListener(e -> {
      consoleLog(e.getFormattedValue());
    });
    SlButton button = new SlButton("Click Me");
    button.addClassName("button");
    button.addClickListener(e -> {
      colorPicker.setValue(new Color((int)(Math.random() * 0x1000000)));
    });
    
    SlDetails details = new SlDetails("Color Picker");
    details.getContent().add(colorPicker);
    details.addOpenListener(e -> {
      consoleLog("Open");
    });
    
    SlDetails details2 = new SlDetails("Random Color Picker");
    details2.getContent().add(button);
    
    SlDetailsGroup group = new SlDetailsGroup();
    group.addClassName("details-group");
    group.add(details, details2);

    panel.add(group);

  }

}
