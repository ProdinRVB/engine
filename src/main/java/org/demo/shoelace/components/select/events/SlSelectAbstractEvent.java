package org.demo.shoelace.components.select.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dwcj.webcomponent.annotations.EventExpressions;
import org.dwcj.webcomponent.events.Event;
import org.demo.shoelace.components.select.SlOption;
import org.demo.shoelace.components.select.SlSelect;

/**
 * @author Hyyan Abo Fakher
 */
@EventExpressions(detail = "event.detail.options = [].concat(component.value).join(',');", filter = "event.target.isSameNode(component)")
public abstract class SlSelectAbstractEvent extends Event<SlSelect> {

  /**
   * @param control  the control
   * @param eventMap the event map
   */
  public SlSelectAbstractEvent(SlSelect control, Map<String, Object> eventMap) {
    super(control, eventMap);
  }

  /**
   * Get the selected options.
   * 
   * @return the selected options
   */
  public List<SlOption> getSelected() {

    SlSelect control = (SlSelect) getControl();
    @SuppressWarnings("unchecked")
    Map<String, String> detail = (Map<String, String>) getEventMap().get("detail");
    List<String> options = detail.get("options") == null ? new ArrayList<>()
        : List.of(detail.get("options").split(","));
    List<SlOption> selected = new ArrayList<>();

    for (String option : options) {
      if (control.contains(option)) {
        selected.add(control.get(option));
      }
    }

    return selected;
  }
}
