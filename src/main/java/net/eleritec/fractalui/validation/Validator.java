package net.eleritec.fractalui.validation;

import net.eleritec.fractalui.AbstractViewController;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: May 27, 2008
 * Time: 9:39:43 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Validator<T> {
    public void validate();
    public ValidationSet getMessages();
    public void setController(AbstractViewController<T> controller);
    public AbstractViewController<T> getController();
    public Validator<T> getContext();
}
