package org.demo;

import org.demo.shoelace.badge.SlBadge;
import org.demo.shoelace.breadcrumb.BreadcrumbItem;
import org.demo.shoelace.breadcrumb.SlBreadcrumb;
import org.demo.shoelace.button.SlButton;
import org.demo.shoelace.button.events.SlButtonClickEvent;
import org.demo.shoelace.checkbox.SlCheckbox;
import org.demo.shoelace.checkbox.events.SlCheckboxChangeEvent;
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

    SlCheckbox checkbox = new SlCheckbox("Checkbox");
    checkbox.addChangeListener((SlCheckboxChangeEvent event) -> {
      App.consoleLog(event.isChecked() ? "Checked" : "Unchecked");
    });

    panel.add(checkbox);

    SlButton button = new SlButton("Button");
    button.addClickListener((SlButtonClickEvent event) -> {
      checkbox.click();
      App.consoleLog(checkbox.isChecked() ? "Checked 21" : "Unchecked 21");
    });

    panel.add(button);
  }

}
