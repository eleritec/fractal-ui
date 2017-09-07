package net.eleritec.fractalui;

import net.eleritec.fractalui.resource.AbstractResource;

/**
 * @author cbutler
 */
public interface AbstractUI<T> {
    public AbstractView<T> getView();
    public AbstractViewController<T> getController();
    public T getModel();
    public AbstractResource getResource();
}
