package net.eleritec.fractalui;

import net.eleritec.fractalui.resource.AbstractResource;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 24, 2008
 * Time: 3:12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ViewProvider<M> {
    public AbstractView<M> getViewComponent();
    public AbstractViewController<M> getViewController();
    
    public ViewProvider<?> getParent();
    public void setParent(ViewProvider<?> provider);

    public AbstractResource getResource();
}
