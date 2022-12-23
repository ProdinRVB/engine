package demo;

import java.util.function.Consumer;

import org.dwcj.App;
import org.dwcj.controls.button.Button;
import org.dwcj.controls.panels.AppPanel;
import org.dwcj.controls.toast.Toast;
import org.dwcj.controls.toast.events.ToastVisibilityEvent;
import org.dwcj.exceptions.DwcAppInitializeException;

public class Playground extends App {
    public Consumer<ToastVisibilityEvent> a;

    @Override
    public void run() throws DwcAppInitializeException {

        // createToast()
        // .setTheme(Toast.Theme.SUCCESS)
        // .show("The application has been updated successfully");
        AppPanel panel = new AppPanel();
        Toast toast = new Toast();
        toast.show("The application has been updated successfully 1");
        panel.add(toast);
        a = this::onVisibleChanged;
        toast.onVisibleChanged(a);

        toast.onVisibleChanged((e) -> {
            App.consoleLog("Toast visibility changed 2: " + e.isVisible());
        });

        Button open = new Button("Open");
        panel.add(open);
        open.onClick((e) -> {
            toast.show("The application has been updated successfully 2");
        });

        Button close = new Button("Close");
        panel.add(close);
        close.onClick((e) -> {
            toast.hide();
        });
    }

    public void onVisibleChanged(ToastVisibilityEvent e) {
        App.consoleLog("Toast visibility changed 1: " + e.isVisible());
        App.consoleLog(String.valueOf(e.getControl().removeOnVisibleChanged(a)));
    }
}
