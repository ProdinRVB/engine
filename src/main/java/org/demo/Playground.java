package org.demo;

import org.demo.shoelace.badge.SlBadge;
import org.demo.shoelace.button.SlButton;
import org.demo.shoelace.button.events.SlButtonClickEvent;
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

    SlBadge badge = new SlBadge("10");
    badge.setPill(true);
    badge.setPulse(true);

    SlButton button = new SlButton("Click me");
    button.setPrefix("<sl-icon name='gear'></sl-icon>");
    // button.setVariant(SlButton.Variant.PRIMARY);
    button.setSize(SlButton.Size.LARGE);
    button.setBadge(badge);
    button.addClickListener((SlButtonClickEvent event) -> {
      App.msgbox("Hello World!");
    });

    SlButton button2 = new SlButton("Remove badge");
    button2.addClickListener((SlButtonClickEvent event) -> {
      button.getBadge().setPulse(false);
    });

    // // create a list of badges
    // for (int i = 0; i < 3; i++) {
    // SlBadge badge = new SlBadge();
    // badge.setText("Badge " + String.valueOf(i));
    // badge.setPill(true);
    // // set random variant
    // badge.setVariant(SlBadge.Variant.values()[(int) (Math.random() *
    // SlBadge.Variant.values().length)]);
    // badge.setPulse(true);
    // panel.add(badge);
    // }

    panel.add(button, button2);

  }

}
