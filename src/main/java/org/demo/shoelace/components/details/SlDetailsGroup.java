package org.demo.shoelace.components.details;

import java.util.HashMap;
import java.util.Map;

import org.dwcj.webcomponent.WebComponent;
import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.annotations.EventName;
import org.dwcj.webcomponent.annotations.NodeName;
import org.dwcj.webcomponent.events.Event;

/**
 * The Class SlDetailsGroup is a container for {@link SlDetails} components.
 * 
 * It allows to open only one details at a time. When a details is opened, all
 * other details in the group are closed.
 * 
 * @author Hyyan Abo Fakher
 * @see SlDetails
 * @since 1.0.0
 */
@NodeName("div")
public final class SlDetailsGroup extends WebComponent {
  private Map<String, SlDetails> details = new HashMap<>();

  /**
   * Instantiates a new sl details group.
   */
  public SlDetailsGroup() {
    super();
    addEventListener(SlDetailsGroupToggleEvent.class, this::handleToggle);
  }

  /**
   * Add details to the group.
   * 
   * @param details the details
   * @return the group
   */
  public SlDetailsGroup add(SlDetails details) {
    String id = addControl(details);
    details.setAttribute("sl-details-group", id);
    details.setUserData("sl-details-group", id);
    this.details.put(id, details);
    return this;
  }

  /**
   * Add details to the group.
   * 
   * @param details the details
   * @return the group
   */
  public SlDetailsGroup add(SlDetails... details) {
    for (SlDetails detail : details) {
      add(detail);
    }

    return this;
  }

  /**
   * Remove details from the group.
   * 
   * @param details the details
   * @return the group
   */
  public SlDetailsGroup remove(SlDetails details) {
    String id = (String) details.getUserData("sl-details-group");
    if (id != null) {
      removeControl(id);
      this.details.remove(id);
    }

    return this;
  }

  /**
   * Close all details in the group except the given one.
   * 
   * @return the group
   */
  private void handleToggle(SlDetailsGroupToggleEvent event) {
    String id = event.getDetailsId();
    for (SlDetails detail : details.values()) {
      if (!detail.getUserData("sl-details-group").equals(id)) {
        detail.close();
      }
    }
  }

  @EventName("sl-show")
  @EventExpressions(detail = "event.detail.control= event.target.getAttribute('sl-details-group')")
  private class SlDetailsGroupToggleEvent extends Event<SlDetailsGroup> {

    /**
     * @param control  the control
     * @param eventMap the event map
     */
    public SlDetailsGroupToggleEvent(SlDetailsGroup control, Map<String, Object> eventMap) {
      super(control, eventMap);
    }

    /**
     * Gets the details id.
     *
     * @return the details id
     */
    public String getDetailsId() {
      @SuppressWarnings("unchecked")
      Map<String, String> detail = (Map<String, String>) getEventMap().get("detail");
      return detail.get("control");
    }
  }
}
