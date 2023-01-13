package org.demo;

import org.demo.components.drawer.Drawer;
import org.dwcj.App;
import org.dwcj.annotations.AppTheme;
import org.dwcj.annotations.AppTitle;
import org.dwcj.annotations.InlineStyleSheet;
import org.dwcj.controls.button.Button;
import org.dwcj.controls.label.Label;
import org.dwcj.controls.panels.AppPanel;
import org.dwcj.exceptions.DwcException;

@AppTheme("dark")
@AppTitle("QR Code Generator")
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
public class DrawerSample extends App {

  @Override
  public void run() throws DwcException {
    AppPanel panel = new AppPanel();
    panel.addClassName("app-panel");

    Drawer drawer = new Drawer();
    drawer.setPlacement(Drawer.Placement.BOTTOM_CENTER);
    drawer.setSize("70vh");
    drawer.getContent().add(new Label("Hello World!"));
    panel.add(drawer);

    Button button = new Button("Open Drawer");
    button.onClick(event -> {
        drawer.open();    
    });

    panel.add(button);
  }
} 