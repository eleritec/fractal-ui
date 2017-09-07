package net.eleritec.fractalui.defaults;

import net.eleritec.fractalui.AbstractViewController;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 24, 2008
 * Time: 2:25:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class StandaloneViewController<T> extends AbstractViewController<T> {

    protected void initializeTopics() {
        // no topics.  nothing to publish to the outside world
    }

    protected void initializeListeners() {
        // no subcontrollers whose topics we want to listen to
    }
}
