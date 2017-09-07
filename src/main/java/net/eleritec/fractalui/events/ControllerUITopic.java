package net.eleritec.fractalui.events;

import net.eleritec.fractalui.AbstractViewPresenter;
import net.eleritec.fractalui.events.AbstractUITopic;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 2, 2008
 * Time: 2:44:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class ControllerUITopic extends AbstractUITopic
        implements ControllerListener {

    private ControllerListener getDelegate() {
        return (ControllerListener)getDispatcher();
    }


    public void controllerChanged(ControllerChangeEvent event) {
        getDelegate().controllerChanged(event);
    }

    public void addListener(ControllerListener listener) {
        subscribe(listener);
    }

    public void removeListener(ControllerListener listener) {
        unsubscribe(listener);
    }
}
