package org.dwcj.controls.toast.events;

import org.dwcj.controls.toast.Toast;
import org.dwcj.interfaces.ControlEvent;

public class ToastVisibilityEvent implements ControlEvent {

    private final Toast toast;
    private final boolean visible;

    /**
     * Creates a new ToastVisibilityEvent.
     * 
     * @param toast   the toast.
     * @param visible true if the toast is visible, false otherwise.
     */
    public ToastVisibilityEvent(Toast toast, boolean visible) {
        this.toast = toast;
        this.visible = visible;
    }

    /**
     * Returns true if the toast is visible, false otherwise.
     * 
     * @return true if the toast is visible, false otherwise.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Toast getControl() {
        return toast;
    }
}
