package org.demo;

import org.demo.components.applayout.AppLayout;
import org.dwcj.App;
import org.dwcj.annotations.InlineStyleSheet;
import org.dwcj.controls.imagecontrol.ImageControl;
import org.dwcj.controls.label.Label;
import org.dwcj.controls.panels.Div;
import org.dwcj.controls.tabcontrol.TabControl;
import org.dwcj.controls.panels.AppPanel;
import org.dwcj.controls.tabcontrol.events.TabSelectEvent;
import org.dwcj.exceptions.DwcAppInitializeException;

@InlineStyleSheet(top = true, value = "" +
        " body,html {overflow: hidden}" +
        "" +
        " .bbj-toolbar {" +
        "    display: flex;" +
        "    align-items: center;" +
        "    gap: var(--bbj-space-m);" +
        "    padding: 0 var(--bbj-space-m);" +
        " }" +
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
public class BasisAppLayout extends App {
    private AppLayout layout;
    private Label pageContent;

    @Override
    public void run() throws DwcAppInitializeException {
        AppPanel p = new AppPanel();
        p.setText("Basic App Layout");

        layout = new AppLayout();
        p.add(layout);

        Div header = (Div) layout.getHeader();
        Div toolbar = new Div();
        header.add(toolbar);
        toolbar.addClassName("bbj-toolbar");
        toolbar.add(new Label("<html><bbj-icon-button name='menu-2' data-drawer-toggle></bbj-icon-button></html>"));
        toolbar.add(new Label("<html><h3>DWC Application</h3></html>"));

        Div drawer = (Div) layout.getDrawer();
        drawer.addClassName("bbj-drawer");

        ImageControl logo = new ImageControl();
        drawer.add(logo);
        logo.addClassName("bbj-logo");
        logo.setImageFromResource("logo.png");

        TabControl drawerMenu = new TabControl();
        drawer.add(drawerMenu);

        drawerMenu.onTabSelect(this::onMenuItemSelect);
        // drawerMenu.setCallback(drawerMenu.ON_TAB_SELECT,"onPageChanged")

        drawerMenu.setAttribute("nobody", "true");
        drawerMenu.setAttribute("borderless", "true");
        drawerMenu.setAttribute("placement", "left");

        drawerMenu.addTab("<bbj-icon name='dashboard'></bbj-icon>      Dashboard");
        drawerMenu.addTab("<bbj-icon name='shopping-cart'></bbj-icon>  Orders");
        drawerMenu.addTab("<bbj-icon name='users'></bbj-icon>          Customers");
        drawerMenu.addTab("<bbj-icon name='box'></bbj-icon>            Products");
        drawerMenu.addTab("<bbj-icon name='files'></bbj-icon>          Documents");
        drawerMenu.addTab("<bbj-icon name='checklist'></bbj-icon>      Tasks");
        drawerMenu.addTab("<bbj-icon name='chart-dots-2'></bbj-icon>   Analytics");

        Div content = (Div) layout.getContent();
        content.add(new Label("<html><h1>Application Title</h1></html>"));
        pageContent = new Label("<html><p>Content goes here</p></html>");
        content.add(pageContent);

    }

    private void onMenuItemSelect(TabSelectEvent tabSelectEvent) {
        pageContent.setText(tabSelectEvent.getTitle().replaceAll("<[^>]*>", "").trim());
    }
}