package org.demo;

import java.awt.Color;
import org.demo.components.qrcode.QRCode;
import org.dwcj.App;
import org.dwcj.annotations.AppTheme;
import org.dwcj.annotations.AppTitle;
import org.dwcj.annotations.InlineStyleSheet;
import org.dwcj.controls.panels.AppPanel;
import org.dwcj.controls.textbox.TextBox;
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
public class QRSample extends App {

  private static final String DEFAULT_VALUE = "https://dwcj.org/";

  @Override
  public void run() throws DwcException {
    AppPanel panel = new AppPanel();
    panel.addClassName("app-panel");

    QRCode qr = new QRCode(DEFAULT_VALUE);
    qr.setSize(200);
    qr.setSize(qr.getSize());
    qr.setColor(Color.ORANGE);

    TextBox editBox = new TextBox();
    editBox.setText(DEFAULT_VALUE);
    editBox.setExpanse(TextBox.Expanse.LARGE);
    editBox.setAttribute("placeholder", "Enter a value");
    editBox.onEditModify((e) -> {
      qr.setValue(editBox.getText());
    });

    panel.add(qr, editBox);
  }
}