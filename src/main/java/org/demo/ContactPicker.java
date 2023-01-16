package org.demo;

import com.basiscomponents.db.DataRow;
import com.basiscomponents.db.ResultSet;

import org.demo.components.drawer.Drawer;
import org.dwcj.App;
import org.dwcj.annotations.InlineStyleSheet;
import org.dwcj.controls.button.Button;
import org.dwcj.controls.button.events.ButtonClickEvent;
import org.dwcj.controls.label.Label;
import org.dwcj.controls.panels.AppPanel;
import org.dwcj.controls.panels.Div;
import org.dwcj.exceptions.DwcAppInitializeException;

@InlineStyleSheet(value = "" +
    ".root {" +
    "  display: flex;" +
    "  align-items: center;" +
    "  justify-content: center;" +
    "  width: 100vw;" +
    "  height: 100vh;" +
    "}" +
    "       " +
    ".contactsList {" +
    "  padding: var(--bbj-space-m);" +
    "}" +
    "       " +
    ".bookEntry {" +
    "  display: flex;" +
    "  gap: 1rem;" +
    "  align-items: center;" +
    "  padding: var(--bbj-space-s);" +
    "  cursor: var(--bbj-cursor-click);" +
    "  transition: background-color var(--bbj-transition);" +
    "  border-bottom: thin solid var(--bbj-color-default);" +
    "  border-style: solid !important;" +
    "}" +
    "" +
    ".bookEntry:hover {" +
    "  background-color: var(--bbj-color-primary-alt);" +
    "}" +
    "" +
    ".bookEntry__avatar {" +
    "  display: flex;" +
    "  align-items: center;" +
    "  width: var(--bbj-size-l);" +
    "  height: var(--bbj-size-l);" +
    "  border-radius: var(--bbj-border-radius-round);" +
    "}" +
    "" +
    ".bookEntry__avatar img {" +
    "  width: 100%;" +
    "  height: 100%;" +
    "}" +
    "" +
    ".bookEntry__info {" +
    "  flex: 1;" +
    "  display: flex;" +
    "  flex-direction: column;" +
    "}" +
    "" +
    ".bookEntry__location {" +
    "  font-size: var(--bbj-font-size-s);" +
    "  color: var(--bbj-color-default-text);" +
    "}")
public class ContactPicker extends App {

  private Drawer contactsDrawer;
  private Div drawerPanel;

  @Override
  public void run() throws DwcAppInitializeException  {

    AppPanel p = new AppPanel();
    p.setText("Drawer Demo");
    p.addClassName("root");

    Button btn = new Button("Open Contacts");
    p.add(btn);
    btn.onClick(this::onOpenContacts);
    btn.setTheme(Button.Theme.PRIMARY);
    btn.setExpanse(Button.Expanse.LARGE);

    contactsDrawer = new Drawer();
    p.add(contactsDrawer);
    contactsDrawer.setPlacement(Drawer.Placement.BOTTOM_CENTER);
    contactsDrawer.setSize("70vh");

    drawerPanel = (Div) contactsDrawer.getContent();
    drawerPanel.addClassName("contactsList");
  }

  private void fillPanel() {

    ChileCompanyContactsBC bc = null;
    try {
      bc = new ChileCompanyContactsBC();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    ResultSet rs = null;
    try {
      rs = bc.retrieve();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    for (DataRow row : rs) {
      String firstName = row.getField("FIRST_NAME").getString().trim();
      String lastName = row.getField("LAST_NAME").getString().trim();
      String fullName = firstName + " " + lastName;
      String phone = row.getField("PHONE").getString().trim();
      String country = row.getField("COUNTRY").getString().trim();
      String city = row.getField("CITY").getString().trim();
      String fullLocation = country + " - " + city;

      Div card = new Div();
      drawerPanel.add(card);
      card.addClassName("bookEntry");

      String avatarContent = "<html><img src='https://ui-avatars.com/api/?name=" + fullName
          + "&&background=random'' /></html>";
      Label avatar = new Label(avatarContent);
      card.add(avatar);
      avatar.addClassName("bookEntry__avatar");

      Div info = new Div();
      card.add(info);

      info.addClassName("bookEntry__info");

      Label name = new Label(fullName);
      info.add(name);
      name.addClassName("bookEntry__name");

      Label location = new Label(fullLocation);
      info.add(location);
      location.addClassName("bookEntry__location");

      Button call = new Button("<html><bbj-icon name='phone'></bbj-icon></html>");
      card.add(call);

      call.setEnabled(!phone.isBlank());
      call.setUserData("phone", phone);
      call.onClick(this::makeCall);
    }
  }

  private void makeCall(ButtonClickEvent buttonClickEvent) {
    App.msgbox("Call " + buttonClickEvent.getControl().getUserData("phone"));

  }

  private void onOpenContacts(ButtonClickEvent buttonClickEvent) {
    contactsDrawer.open();
    fillPanel();
  }

}