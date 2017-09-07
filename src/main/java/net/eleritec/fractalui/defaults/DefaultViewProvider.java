package net.eleritec.fractalui.defaults;

import net.eleritec.fractalui.AbstractView;
import net.eleritec.fractalui.AbstractViewController;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 24, 2008
 * Time: 8:43:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultViewProvider<T> extends DefaultViewBuilder<T> {
    private String name;

    public DefaultViewProvider(String name) {
        this.name = name;
    }

    public AbstractView createView() {
        return new BasicView(name);
    }

    public AbstractViewController<T> createController() {
        return new StandaloneViewController<T>();
    }
}
