package org.demo.shoelace.components.details;

import java.util.ArrayList;
import java.util.List;

import org.dwcj.webcomponent.WebComponent;
import org.dwcj.webcomponent.annotations.NodeName;

@NodeName("div")
public final class SlDetailsGroup extends WebComponent {

  private List<SlDetails> details = new ArrayList<>();
}
