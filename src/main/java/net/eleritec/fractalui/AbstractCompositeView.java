package net.eleritec.fractalui;

import net.eleritec.fractalui.resource.AbstractResource;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 3, 2008
 * Time: 3:28:20 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractCompositeView<M> extends AbstractView<M> {

	private static final long serialVersionUID = 4744017233003558811L;

	protected AbstractCompositeView() {
        super();
    }

    protected AbstractCompositeView(boolean initNow) {
        super(initNow);
    }

    protected AbstractCompositeView(AbstractResource resource, boolean initNow) {
        super(resource, initNow);
    }

    protected void createComponents() {
    }
}
