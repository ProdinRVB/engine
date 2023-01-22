package org.demo;

import org.demo.components.toast.Toast;
import org.demo.components.toast.events.ToastButtonClickedEvent;
import org.demo.components.toast.events.ToastClosedEvent;
import org.dwcj.App;
import org.dwcj.annotations.AppTheme;
import org.dwcj.annotations.AppTitle;
import org.dwcj.annotations.InlineStyleSheet;
import org.dwcj.controls.panels.AppPanel;
import org.dwcj.exceptions.DwcException;
import org.dwcj.webcomponent.events.EventListener;

@AppTheme("dark")
@AppTitle("Toast Sample")
@InlineStyleSheet(value = "" +
    ".toast-custom-theme {" +
    "  --bbj-toast-background: linear-gradient(" +
    "    to right," +
    "    hsl(203, 100%, 48%)," +
    "    hsl(1, 89%, 51%)" +
    "  );" +
    "  --bbj-toast-color: white;" +
    "  --bbj-toast-button-color: white;" +
    "  --bbj-toast-border-color: hsl(0, 0%, 73%);" +
    "}")
public class ToastSample extends App {

  @Override
  public void run() throws DwcException {
    AppPanel panel = new AppPanel();

    // Button btn = new Button("Show Toast");
    // btn.onClick((e) -> {
    // App.msgbox("Clicked Listener 1");
    // });
    // panel.add(btn);

    EventListener<ToastClosedEvent> closedListener = (ToastClosedEvent e) -> {
      App.msgbox("Closed Listener 1");
    };

    Toast toast = new Toast();
    toast.addClassName("toast-custom-theme");
    toast
        .setPlacement(Toast.Placement.TOP)
        .addButton("update-btn", "Update")
        .addCloseButton()
        .show("The application has new updates.")
        .addButtonClickedListener((ToastButtonClickedEvent e) -> {
          Toast component = (Toast) e.getControl();
          String buttonId = e.getButtonId();
          switch (buttonId) {
            case "update-btn":
              component.removeAllButtons();
              component.show("The application is updating please wait ...");
              break;
            case "close":
              App.msgbox("Toast closed in the client. Server received the close event.");
              break;
          }
        })
        // .addOpenedListener((ToastOpenedEvent e) -> {
        // App.msgbox("Opened Listener");
        // })
        .addClosedListener(closedListener)
        .addClosedListener((ToastClosedEvent e) -> {
          App.msgbox("Closed Listener 2");
        })
        .removeClosedListener(closedListener);

    panel.add(toast);
  }
}