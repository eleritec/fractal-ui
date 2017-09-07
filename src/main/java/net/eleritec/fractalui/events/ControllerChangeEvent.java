package net.eleritec.fractalui.events;

import java.util.EventObject;

import net.eleritec.fractalui.AbstractViewController;


/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 2, 2008
 * Time: 2:42:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ControllerChangeEvent<T> extends EventObject {
	private static final long serialVersionUID = -8301244871228855110L;
	
	private AbstractViewController<T> previousController;
    private AbstractViewController<T> currentController;

    public ControllerChangeEvent(Object source,
            AbstractViewController<T> previous, AbstractViewController<T> current) {
        super(source);
        this.previousController = previous;
        this.currentController = current;
    }

    public AbstractViewController<T> getPreviousController() {
        return previousController;
    }

    public AbstractViewController<T> getCurrentController() {
        return currentController;
    }
}
