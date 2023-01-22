package org.demo;

import org.demo.shoelace.components.details.SlDetails;
import org.demo.shoelace.components.details.SlDetailsGroup;
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
    // panel.addClassName("app-panel");

    SlDetailsGroup group = new SlDetailsGroup();
    panel.add(group);

    SlDetails details = new SlDetails("Details 1", "My text");
    // details.addOpenListener(event -> {
    // consoleLog("open 1");
    // }).addCloseListener(event -> {
    // consoleLog("close 1");
    // });

    SlDetails details2 = new SlDetails("Details 2", "My text 2");
    details2.addOpenListener(event -> {
      consoleLog("open 2");
    }).addCloseListener(event -> {
      consoleLog("close 2");
    });
    group.add(details, details2);

    // panel.add(details, details2);
  }

}
