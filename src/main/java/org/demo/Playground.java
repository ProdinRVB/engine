package org.demo;

import java.awt.Color;

import org.demo.shoelace.components.button.SlButton;
import org.demo.shoelace.components.button.SlIconButton;
import org.demo.shoelace.components.colorpicker.SlColorPicker;
import org.demo.shoelace.components.details.SlDetails;
import org.demo.shoelace.components.details.SlDetailsGroup;
import org.demo.shoelace.components.dialog.SlDialog;
import org.demo.shoelace.components.drawer.SlDrawer;
import org.demo.shoelace.components.input.SlInput;
import org.demo.shoelace.utils.CssColor;
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

    SlDrawer drawer = new SlDrawer();
    drawer.setPlacement(SlDrawer.Placement.END).setSize("40em");

    drawer.addOpenListener((e) -> {
      consoleLog("Drawer Opened");
    });
    drawer.addCloseListener((e) -> {
      consoleLog("Drawer Closed");
    });

    drawer.getContent().add(new Label("Drawer Content"));

    SlButton close = new SlButton("Close Drawer");
    close.setVariant(SlButton.Variant.PRIMARY);
    close.addClickListener((e) -> {
      drawer.close();
    });
    drawer.getFooter().add(close);

    SlIconButton icon = new SlIconButton("box-arrow-up-right");
    icon.addClickListener((e) -> {
      consoleLog("Icon Clicked");
    });
    drawer.getHeaderActions().add(icon);

    SlButton open = new SlButton("Open Drawer");
    open.addClickListener((e) -> {
      drawer.open();
    });

    SlInput input = new SlInput();
    input.addModifiedListener((e) -> {
      consoleLog("Input Modified: " + e.getValue());
    });

    panel.add(open, drawer, input);
  }

}
