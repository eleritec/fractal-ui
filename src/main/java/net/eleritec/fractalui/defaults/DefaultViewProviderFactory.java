package net.eleritec.fractalui.defaults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import net.eleritec.fractalui.AbstractViewController;
import net.eleritec.fractalui.ConstructionProviderFactory;
import net.eleritec.fractalui.ViewProvider;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 24, 2008
 * Time: 3:16:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultViewProviderFactory implements ConstructionProviderFactory {
    private HashMap<String, ViewProvider> providers;

    public DefaultViewProviderFactory() {
        providers = new HashMap<String, ViewProvider>();
    }

    public void addProvider(String name, ViewProvider provider) {
        providers.put(name, provider);
    }

    public void removeProvider(String name) {
        providers.remove(name);
    }

    public ViewProvider getProvider(String providerName) {
        return providers.get(providerName);
    }

    public Set<String> getProviderNames() {
        return providers.keySet();
    }

    public AbstractViewController getController(String controllerName) {
        ViewProvider provider = getProvider(controllerName);
        return provider==null? null: provider.getViewController();
    }

    public List<AbstractViewController<?>> getControllers() {
        ArrayList<AbstractViewController<?>> controllers =
                new ArrayList<AbstractViewController<?>>();
        for(ViewProvider provider: providers.values()) {
            AbstractViewController controller = provider.getViewController();
            if(!controllers.contains(controller)) {
                controllers.add(controller);
            }
        }
        return controllers;
    }
}
