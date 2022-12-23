package org.dwcj;

import com.basis.startup.type.BBjException;

import org.dwcj.controls.toast.Toast;
import org.dwcj.exceptions.DwcAppInitializeException;

/**
 * This is the central class representing an app. In order to implement an app,
 * extend this class and
 * override the run() method.
 *
 */
@SuppressWarnings("java:S1610") // we want this to be abstract class, not interface
public abstract class App {

  private BackgroundPanel backgroundPanel;

  /**
   * Log a String to the browser console (console.out)
   * 
   * @param output The message to log
   */
  public static void consoleLog(String output) {
    try {

      Environment.getInstance().getSysGui().executeScript("console.log(\"" + output + "\")");
    } catch (BBjException e) {
      e.printStackTrace();
    }
  }

  public static void consoleError(String output) {
    try {

      Environment.getInstance().getSysGui().executeScript("console.error(\"" + output + "\")");
    } catch (BBjException e) {
      e.printStackTrace();
    }
  }

  /**
   * Shows a message box
   * 
   * @param alert The message to show
   * @return
   */
  public static int msgbox(String alert) {
    return Environment.getInstance().getDwcjHelper().msgbox(alert, 0, "");
  }

  /**
   *
   * @param alert   The message to show
   * @param options
   * @return
   */
  public static int msgbox(String alert, int options) {
    return Environment.getInstance().getDwcjHelper().msgbox(alert, options, "");
  }

  /**
   *
   * @param alert   The message to show
   * @param options
   * @param title
   * @return
   */
  public static int msgbox(String alert, int options, String title) {
    return Environment.getInstance().getDwcjHelper().msgbox(alert, options, title);
  }

  /**
   * Show or hide a busy indicator overlay
   * 
   * @param busy A boolean value true=show false=hide
   */
  public static void busy(boolean busy) {
    try {
      if (busy) {
        Environment.getInstance().getBBjAPI().getBuiManager().getBusyIndicator().setText("");
      }
      Environment.getInstance().getBBjAPI().getBuiManager().getBusyIndicator().setVisible(busy);
    } catch (BBjException e) {
      // ignore
    }
  }

  /**
   * show the busy indicator with the text passed to this method
   * 
   * @param busyText the text to show
   */
  public static void busy(String busyText) {
    try {
      Environment.getInstance().getBBjAPI().getBuiManager().getBusyIndicator().setText(busyText);
      Environment.getInstance().getBBjAPI().getBuiManager().getBusyIndicator().setVisible(true);
    } catch (BBjException e) {
      // ignore
    }
  }

  /**
   * Create new toast object and return it.
   * 
   * @return The toast object
   */
  public Toast createToast() {
    Toast instance = null;

    try {
      BackgroundPanel cp = getBackgroundPanel();
      instance = new Toast();
      cp.add(instance);

    } catch (DwcAppInitializeException e) {
      e.printStackTrace();
    }

    return instance;
  }

  /**
   * Call this method to terminate your App.
   */
  public void terminate() {
    Environment.getInstance().getBBjAPI().postPriorityCustomEvent("doTerminate", null);
    cleanup();
    Environment.cleanup();
  }

  /**
   * Override this method to implement custom cleanup
   * e.g. kill all background threads that may still run
   */
  public void cleanup() {
  }

  /**
   * Override this method to implement your app behavior
   * 
   * @throws DwcAppInitializeException
   */
  public abstract void run() throws DwcAppInitializeException;

  protected App() {
    preRun();
    try {
      run();
    } catch (DwcAppInitializeException e) {
      e.printStackTrace();
    }
  }

  private void preRun() {
    Environment.getInstance().getBBjAPI().setCustomEventCallback("doTerminate", "terminate");
  }

  /**
   * Get the background panel instance. If the panel is not yet initialized, it
   * will be created.
   * 
   * @return the background panel
   * @throws DwcAppInitializeException
   */
  private BackgroundPanel getBackgroundPanel() throws DwcAppInitializeException {
    if (backgroundPanel == null) {
      backgroundPanel = new BackgroundPanel();
    }

    return backgroundPanel;
  }
}
