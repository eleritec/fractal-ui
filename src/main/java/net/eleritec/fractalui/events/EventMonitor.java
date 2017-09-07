package net.eleritec.fractalui.events;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.util.ArrayList;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 28, 2008
 * Time: 12:46:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class EventMonitor {
    private ArrayList<Enabler> handlers;

    public EventMonitor() {
        handlers = new ArrayList<Enabler>();
    }

    public void setEnabled(boolean enabled) {
        for(Enabler listener: handlers) {
            listener.setEnabled(enabled);
        }
    }

    private Object trackListener(Object listener, Class...types) {
        Enabler handler = (Enabler) EnabledListenerProxy.getProxy(listener, types);
        handlers.add(handler);
        return handler;
    }

    public ChangeListener createChangeListener(ChangeListener listener) {
        return (ChangeListener)trackListener(listener,
                                                   ChangeListener.class);
    }

    public ItemListener createItemListener(ItemListener listener) {
        return (ItemListener)trackListener(listener,
                                                   ItemListener.class);
    }

    public ActionListener createActionListener(ActionListener listener) {
        return (ActionListener)trackListener(listener,
                                                   ActionListener.class);
    }

    public ChangeListener createChangeListener(final Runnable runnable) {
        return createChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                runnable.run();
            }
        });
    }

    public ItemListener createItemListener(final Runnable runnable) {
        return createItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                runnable.run();
            }
        });
    }

    public ActionListener createActionListener(final Runnable runnable) {
        return createActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                runnable.run();
            }
        });
    }
}
