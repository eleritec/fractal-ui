package net.eleritec.fractalui.defaults;

import net.eleritec.fractalui.AbstractCompositeView;
import net.eleritec.fractalui.AbstractView;
import net.eleritec.fractalui.AbstractViewPresenter;
import net.eleritec.fractalui.resource.AbstractResource;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 21, 2008
 * Time: 10:56:30 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DefaultCompositeView extends AbstractCompositeView {
    private DefaultCompositeBuilder builder;

    protected DefaultCompositeView() {
        super(false);
    }

    protected DefaultCompositeView(AbstractResource resource) {
        super(resource, false);
    }

    protected DefaultCompositeView(DefaultCompositeBuilder builder) {
        super(false);
        initialize(builder);
    }

    public void initialize(DefaultCompositeBuilder builder) {
        if(this.builder==null) {
            this.builder = builder;
            setResource(builder.getResource());
            initializeView();
        }
    }

    protected AbstractView getSubview(String viewName) {
        return builder.getView(viewName);
    }

    protected AbstractView findSubview(String viewName) {
        return builder.findView(viewName);
    }

    protected AbstractViewPresenter createPresenter() {
        return null;
    }
}
