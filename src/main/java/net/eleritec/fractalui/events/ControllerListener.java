package net.eleritec.fractalui.events;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 2, 2008
 * Time: 2:43:54 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ControllerListener<T> {
    public void controllerChanged(ControllerChangeEvent<T> event);
}
