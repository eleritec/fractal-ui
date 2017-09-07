package net.eleritec.fractalui.events;

import javax.swing.event.ChangeListener;
import java.awt.event.ItemListener;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 28, 2008
 * Time: 11:59:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class EventFactory {

    public static ChangeListener createChangeListener(ChangeListener listener) {
        return (ChangeListener) EnabledListenerProxy.getProxy(listener,
                                                              ChangeListener.class);
    }

    public static ItemListener createItemListener(ItemListener listener) {
        return (ItemListener) EnabledListenerProxy.getProxy(listener,
                                                            ItemListener.class);
    }

}
