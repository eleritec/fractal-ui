package net.eleritec.fractalui.defaults;

import java.util.List;

import net.eleritec.fractalui.*;
import net.eleritec.fractalui.resource.AbstractResource;


/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 24, 2008
 * Time: 4:06:39 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DefaultCompositeBuilder<M> extends DefaultViewBuilder<M>
                                            implements ControllerLibrary {
    private DefaultViewProviderFactory builderFactory;
    private AbstractResource resource;

    protected abstract void initializeProviders();

    protected DefaultCompositeBuilder() {
        this(null, null);
    }

    protected DefaultCompositeBuilder(AbstractResource resource) {
        this(resource, null);
    }

    protected DefaultCompositeBuilder(AbstractResource resource, M dataModel) {
        setResource(resource);
        builderFactory = new DefaultViewProviderFactory();
        initializeProviders();
        if(dataModel!=null) {
            getViewController().setDataModel(dataModel);
        }
    }

    protected AbstractViewController<M> loadController() {
        AbstractViewController<M> controller = createController();
        controller.setLibrary(this);
        loadSubcontrollers(controller);
        controller.setValidator(createValidator());
        controller.setReady(true);
        return controller;
    }

    protected AbstractView<M> loadView() {
        AbstractView<M> view = super.loadView();
        if(view instanceof DefaultCompositeView) {
            ((DefaultCompositeView)view).initialize(this);
        }
        return view;
    }

    public AbstractViewController<M> getController(String viewName) {
        ViewProvider<M> provider = getProvider(viewName);
        return provider.getViewController();
    }

    public AbstractView<M> getView(String viewName) {
        ViewProvider<M> provider = getProvider(viewName);
        return provider.getViewComponent();
    }

    public AbstractView findView(String viewName) {
        ViewProvider provider = getProvider(viewName, false);
        return provider==null? null: provider.getViewComponent();
    }

    protected void linkHierarachy(ViewProvider provider) {
        if(isInitialized()) {
            AbstractViewController self = getViewController();
            linkHierarachy(self, provider);
        }
    }

    protected void linkHierarachy(AbstractViewController parent,
                                  ViewProvider provider) {
        AbstractViewController child = provider.getViewController();
        parent.addChild(child);
    }

    public List<AbstractViewController<?>> getControllers() {
        return builderFactory.getControllers();
    }

    private ViewProvider<M> getProvider(String providerName) {
        return getProvider(providerName, true);
    }

    private ViewProvider<M> getProvider(String providerName, boolean loadIfNotFound) {
        ViewProvider<M> provider = builderFactory.getProvider(providerName);
        if(provider==null && loadIfNotFound) {
            provider = new DefaultViewProvider<M>(providerName);
            builderFactory.addProvider(providerName, provider);
        }

        if(provider!=null) {
            linkHierarachy(provider);
        }
        return provider;
    }

    protected void addProvider(String name, ViewProvider<M> provider) {
        builderFactory.addProvider(name, provider);
        if(provider!=null) {
            provider.setParent(this);
        }
    }

    public ViewSnapshot getSnapshot() {
        return isInitialized()? getViewController().getSnapshot():
               null;
    }

    private void loadSubcontrollers(AbstractViewController parent) {
        if(!isInitialized()) {
            for(String key: builderFactory.getProviderNames()) {
                ViewProvider provider = getProvider(key);
                linkHierarachy(parent, provider);
            }
        }
    }

    public AbstractResource getResource() {
        return resource;
    }

    public void setResource(AbstractResource resource) {
        this.resource = resource;
    }
}
