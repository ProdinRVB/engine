package org.dwcj.controls.toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Consumer;

import org.dwcj.App;
import org.dwcj.Environment;
import org.dwcj.bridge.IDwcjBBjBridge;
import org.dwcj.bridge.PanelAccessor;
import org.dwcj.controls.AbstractControl;
import org.dwcj.controls.panels.AbstractDwcjPanel;
import org.dwcj.controls.toast.events.ToastVisibilityEvent;

import com.basis.bbj.proxies.event.BBjNativeJavaScriptEvent;
import com.basis.bbj.proxies.sysgui.BBjHtmlView;
import com.basis.bbj.proxies.sysgui.BBjWindow;
import com.basis.bbj.proxyif.SysGuiEventConstants;
import com.basis.startup.type.BBjException;

final public class Toast extends AbstractControl {
  private final ArrayList<String> scripts = new ArrayList<String>();
  private final String uuid = UUID.randomUUID().toString().substring(0, 8);
  private final ArrayList<Consumer<ToastVisibilityEvent>> onVisibilityChangedCallbacks = new ArrayList<Consumer<ToastVisibilityEvent>>();
  private Toast.Theme theme = Toast.Theme.GRAY;
  private Toast.Placement placement = Toast.Placement.BOTTOM;

  /**
   * The theme of the toast.
   */
  public static enum Theme {
    DEFAULT, DANGER, GRAY, INFO, PRIMARY, SUCCESS, WARNING
  }

  /**
   * The placement of the toast.
   */
  public static enum Placement {
    TOP_LEFT, TOP, TOP_RIGHT, BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT
  }

  public Toast() {
    super();
  }

  /**
   * Set the default theme of the toast.
   * 
   * @param theme the theme of the toast.
   * @return the toast instance.
   * @see Theme
   */
  public Toast setTheme(Theme theme) {
    this.theme = theme;
    return this;
  }

  /**
   * Get the default theme of the toast.
   * 
   * @return the default theme of the toast.
   * @see Theme
   */
  public Toast.Theme getTheme() {
    return theme;
  }

  /**
   * Set the default placement of the toast.
   * 
   * @param placement the placement of the toast.
   * 
   * @return the toast instance.
   * @see Placement
   */
  public Toast setPlacement(Placement placement) {
    this.placement = placement;
    return this;
  }

  /**
   * Get the default placement of the toast.
   * 
   * @return the default placement of the toast.
   * @see Placement
   */
  public Toast.Placement getPlacement() {
    return placement;
  }

  /**
   * Get the UUID of the toast.
   * 
   * @return the UUID of the toast.
   */
  public String getUUID() {
    return uuid;
  }

  /**
   * Open the toast with the given message and for the given duration.
   * 
   * @param message  the message to show in the toast.
   * @param duration the duration of the toast in milliseconds.
   * @return
   * 
   * @return the toast instance.
   */
  public Toast show(String message, int duration) {
    StringBuilder sb = new StringBuilder();
    sb.append("(async () => {");
    sb.append(" await customElements.whenDefined('bbj-toast');");
    sb.append(" const hv = document.querySelector('[data-hv=\"").append(getUUID()).append("\"]');");
    sb.append(" let toast = document.querySelector('[data-toast=\"").append(getUUID()).append("\"]');");
    sb.append(" if (!toast) {");
    sb.append("  toast = document.createElement('bbj-toast');");
    sb.append("  toast.setAttribute('data-toast', '").append(getUUID()).append("');");
    sb.append("  document.body.appendChild(toast);");
    sb.append(" }");
    sb.append("toast.addEventListener('bbj-opened', (e) => {");
    sb.append(" if(!hv || !hv.basisDispatchCustomEvent) return;");
    sb.append(" if(!e.target.isSameNode(toast)) return;");
    sb.append(" hv.basisDispatchCustomEvent(hv, {type:'bbj-opened', detail: ''});");
    sb.append("});");
    sb.append("toast.addEventListener('bbj-closed', (e) => {");
    sb.append(" if(!hv || !hv.basisDispatchCustomEvent) return;");
    sb.append(" if(!e.target.isSameNode(toast)) return;");
    sb.append(" hv.basisDispatchCustomEvent(hv, {type:'bbj-closed', detail: ''});");
    sb.append("});");
    sb.append("if(toast.opened) await toast.close();");
    sb.append("toast.message = '").append(message).append("';");
    sb.append("toast.duration = ").append(duration).append(";");
    sb.append("toast.open();");
    sb.append("toast.theme= '").append(resolveEnum(getTheme())).append("';");
    sb.append("toast.placement= '").append(resolveEnum(getPlacement())).append("';");
    sb.append("})();");

    if (ctrl == null) {
      scripts.add(sb.toString());
    } else {
      try {
        ((BBjHtmlView) ctrl).executeAsyncScript(sb.toString());
      } catch (BBjException e) {
        App.msgbox(e.getMessage());
        e.printStackTrace();
      }
    }

    return this;
  }

  /**
   * Open the toast with the given message for `5000` milliseconds.
   * 
   * @param message the message to show in the toast.
   * 
   * @return the toast instance.
   */
  public Toast show(String message) {
    return show(message, 5000);
  }

  /**
   * Close the toast.
   * 
   * @return the toast instance.
   * @throws BBjException
   */
  public Toast hide() {
    StringBuilder sb = new StringBuilder();
    sb.append("(async () => {");
    sb.append(" await customElements.whenDefined('bbj-toast');");
    sb.append(" const toast = document.querySelector('[data-toast=\"").append(getUUID()).append("\"]');");
    sb.append(" if (toast) toast.opened = false;");
    sb.append("})();");

    if (ctrl == null) {
      scripts.add(sb.toString());
    } else {
      try {
        ((BBjHtmlView) ctrl).executeAsyncScript(sb.toString());
      } catch (BBjException e) {
        e.printStackTrace();
      }
    }

    return this;
  }

  /**
   * Create the control
   * 
   * @param p the parent panel
   */
  protected void create(AbstractDwcjPanel p) {

    try {
      BBjWindow window = PanelAccessor.getDefault().getBBjWindow(p);
      byte[] flags = new byte[] { (byte) 0x00, (byte) 0x10 };
      BBjHtmlView hv = window.addHtmlView("", flags);
      hv.setAttribute("data-hv", getUUID());
      hv.setNoEdge(true);
      hv.setTabTraversable(false);
      hv.setFocusable(false);

      // handle events
      IDwcjBBjBridge dwcHelper = Environment.getInstance().getDwcjHelper();
      hv.setCallback(
          SysGuiEventConstants.ON_NATIVE_JAVASCRIPT,
          dwcHelper.getEventProxy(this, "onNativeJavascriptEvent"),
          "onEvent");

      ctrl = hv;
      catchUp();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Execute the given script using the underlying HTMLView
   * 
   * @param script
   */
  protected void executeScript(String script) {
    if (ctrl == null) {
      // store the script to be executed later
      scripts.add(script);
    } else {
      try {
        ((BBjHtmlView) ctrl).executeScript(script);
      } catch (BBjException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Given the enum value, resolve the string value to be used as an html
   * attribute
   * 
   * @param value the enum value
   * @return the string value
   */
  protected String resolveEnum(Enum<?> value) {
    return value.name().toLowerCase().replaceAll("_", "-");
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("java:S3776")
  @Override
  protected void catchUp() throws IllegalAccessException {
    if (Boolean.TRUE.equals(this.getCaughtUp()))
      throw new IllegalAccessException("catchUp cannot be called twice");

    super.catchUp();

    // empty the scripts queue
    if (!this.scripts.isEmpty()) {
      while (!this.scripts.isEmpty()) {
        executeScript(this.scripts.remove(0));
      }
    }
  }

  /**
   * Add a callback to be invoked when the toast visibility changes.
   * 
   * @param callback the callback to be invoked
   * @return the toast instance
   */
  public Toast onVisibleChanged(Consumer<ToastVisibilityEvent> callback) {
    onVisibilityChangedCallbacks.add(callback);
    return this;
  }

  /**
   * Remove the given callback from the list of callbacks to be invoked
   * when the toast visibility changes.
   * 
   * @param callback the callback to be removed
   * @return the toast instance
   */
  public boolean removeOnVisibleChanged(Consumer<ToastVisibilityEvent> callback) {
    return onVisibilityChangedCallbacks.remove(callback);
  }

  /**
   * Handle the native javascript events from the underlying HTMLView
   * and fire the custom events.
   * 
   * @param ev
   * @throws BBjException
   */
  @SuppressWarnings("unused")
  private void onNativeJavascriptEvent(BBjNativeJavaScriptEvent ev) throws BBjException {
    HashMap<String, String> map = ev.getEventMap();
    String type = map.get("type");
    boolean visible = type.equals("bbj-opened");

    ToastVisibilityEvent customEvent = new ToastVisibilityEvent(this, visible);
    Iterator<Consumer<ToastVisibilityEvent>> it = onVisibilityChangedCallbacks.iterator();
    while (it.hasNext())
      it.next().accept(customEvent);
  }
}
