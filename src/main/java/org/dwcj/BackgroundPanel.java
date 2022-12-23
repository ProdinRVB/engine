package org.dwcj;

import org.dwcj.controls.panels.AppPanel;
import org.dwcj.exceptions.DwcAppInitializeException;

/**
 * The BackgroundPanel is a special panel that is used in the background
 * of the application. It is not visible by default and is not intended to be
 * used by the user. Any attempt to change the visibility or the enabled state
 * of the panel will result in an `UnsupportedOperationException` being thrown.
 * 
 * The panel can be used by controls that need to be displayed in the background
 * of the application, such as the Toast control or by the controls that need to
 * have
 * access to a BBj window before the application construct the first `AppPanel`.
 * 
 * The class is package private and cannot be instantiated outside of the
 * package.
 * 
 * @author Hyyan Abo Fakher
 * @see AppPanel
 */
final public class BackgroundPanel extends AppPanel {

    /**
     * Construct a new CommunicationPanel
     * 
     * @throws DwcAppInitializeException
     */
    public BackgroundPanel() throws DwcAppInitializeException {
        super();
        super.setVisible(false);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws UnsupportedOperationException
     */
    @Override
    public BackgroundPanel setVisible(Boolean visible) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("CommunicationPanel is immutable.");
    }

    /**
     * {@inheritDoc}
     * 
     * @throws UnsupportedOperationException
     */
    @Override
    public BackgroundPanel setEnabled(Boolean enabled) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("CommunicationPanel is immutable.");
    }

    /**
     * {@inheritDoc}
     * 
     * @throws UnsupportedOperationException
     */
    @Override
    public BackgroundPanel setId(String id) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("CommunicationPanel is immutable.");
    }

    /**
     * {@inheritDoc}
     * 
     * @throws UnsupportedOperationException
     */
    @Override
    public BackgroundPanel addClass(String selector) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("CommunicationPanel is immutable.");
    }

    /**
     * {@inheritDoc}
     * 
     * @throws UnsupportedOperationException
     */
    @Override
    public BackgroundPanel removeClass(String selector) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("CommunicationPanel is immutable.");
    }
}
