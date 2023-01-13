package org.demo;

import org.demo.components.applayout.AppLayout;

import org.dwcj.App;
import org.dwcj.annotations.AppMeta;
import org.dwcj.annotations.AppTheme;
import org.dwcj.annotations.AppTitle;
import org.dwcj.annotations.InlineStyleSheet;
import org.dwcj.controls.label.Label;
import org.dwcj.controls.panels.AppPanel;
import org.dwcj.controls.panels.Div;
import org.dwcj.exceptions.DwcException;

@AppTheme("light")
@AppTitle("App Layout Sample")
@AppMeta(name = "viewport", content = "width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=no")
@InlineStyleSheet(value = "" +
    "body,html {overflow: hidden}" +
    "" +
    " .bbj-logo {" +
    "    display: flex;" +
    "    align-items: center;" +
    "    justify-content: center;" +
    "    padding: var(--bbj-space-m) 0;" +
    "    margin-bottom: var(--bbj-space-m);" +
    "    border-bottom: thin solid var(--bbj-color-default)" +
    " }" +
    "" +
    " .bbj-logo img {" +
    "    max-width: 100px;" +
    " }")
public class AppLayoutSample extends App {

  @Override
  public void run() throws DwcException {
    AppPanel panel = new AppPanel();
    panel.addClassName("bbj-app-layout-sample");

    AppLayout layout = new AppLayout();
    
    Div header = new Div();
    header.addClassName("bbj-header-2");
    header.add(new Label("<html><bbj-icon-button name='menu-2' data-drawer-toggle></bbj-icon-button>header</html>"));

    // panel.add(header);
    // layout.setHeader(header);
    layout.getHeader().add(header);
    layout.getDrawer().add(new Label("<html><div class='bbj-logo'><img src='https://bbj-plugins.github.io/BBjAppLayout/demo/assets/logo.png' /></div></html>"));
    layout.getContent().add(new Label("<html><h1>DWJ Application</h1></html>"));
    layout.getFooter().add(new Label("Footer"));

    layout.addDrawerOpenedListener(event -> {
      // App.msgbox("Drawer opened");
    }).addDrawerClosedListener(event -> {
      App.msgbox("Drawer closed");
    });

    panel.add(layout);
  }
}