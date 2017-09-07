package net.eleritec.fractalui.defaults;

import net.eleritec.fractalui.*;
import net.eleritec.fractalui.resource.AbstractResource;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 24, 2008
 * Time: 9:19:55 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DefaultViewBuilder<T> extends AbstractViewBuilder<T>
        implements ViewComponentFactory<T>, ViewProvider {
    private ViewProvider parent;
    private AbstractViewController<T> controller;
    private AbstractView<T> view;


    protected DefaultViewBuilder() {
    }

    protected DefaultViewBuilder(T dataModel) {
        getViewController().setDataModel(dataModel);
    }

    public abstract AbstractView<T> createView();

    public ViewComponentFactory<T> createViewFactory() {
        return this;
    }

    protected boolean isInitialized() {
        return view!=null && controller!=null;
    }

    protected void initialize() {
        if(!isInitialized()) {
            this.view = buildView();
            AbstractViewPresenter<T> presenter = this.view.getViewPresenter();
            this.controller = presenter.getViewController();
        }
    }

    public AbstractViewController<T> getViewController() {
        return getViewController(true);
    }

    public AbstractView<T> getViewComponent() {
        return getViewComponent(true);
    }


    public ViewProvider getParent() {
        return parent;
    }

    public void setParent(ViewProvider parent) {
        this.parent = parent;
    }

    public AbstractResource getResource() {
        return null;
    }

    protected AbstractResource getParentResource() {
        ViewProvider parent = getParent();
        return parent==null? null: parent.getResource();
    }

    public AbstractViewController<T> getViewController(boolean createIfNecessary) {
        if(controller==null && createIfNecessary) {
            initialize();
        }
        return controller;
    }

    public AbstractView<T> getViewComponent(boolean createIfNecessary) {
        if(view==null && createIfNecessary) {
            initialize();
        }
        return view;
    }
}
