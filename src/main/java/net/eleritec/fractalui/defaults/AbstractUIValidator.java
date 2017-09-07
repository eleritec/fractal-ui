package net.eleritec.fractalui.defaults;

import net.eleritec.fractalui.AbstractViewController;
import net.eleritec.fractalui.validation.ValidationMessage;
import net.eleritec.fractalui.validation.ValidationSet;
import net.eleritec.fractalui.validation.ValidationType;
import net.eleritec.fractalui.validation.Validator;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: May 27, 2008
 * Time: 12:15:59 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractUIValidator<T> implements Validator<T> {
    private AbstractViewController<T> controller;
    private ValidationSet results;

    protected abstract void performErrorValidation();
    protected abstract void performWarningValidation();

    protected AbstractUIValidator() {
        results = new ValidationSet();
    }

    protected ValidationMessage createMessage(String key, String text,
                                                        ValidationType type) {
        return new ValidationMessage(key, text);
    }

    protected ValidationMessage createMessage(String key, ValidationType type) {
        return new ValidationMessage(key, type);
    }

    protected void addError(String key) {
        results.addError(createMessage(key, ValidationType.ERROR));
    }

    protected void addError(String key, String message) {
        results.addError(createMessage(key, message, ValidationType.ERROR));
    }

    protected void addWarning(String key) {
        results.addWarning(createMessage(key, ValidationType.WARNING));
    }

    protected void addWarning(String key, String message) {
        results.addWarning(createMessage(key, message, ValidationType.WARNING));
    }

    public AbstractViewController<T> getController() {
        return controller;
    }

    @Override
    public void setController(AbstractViewController<T> controller) {
        this.controller = controller;
    }

    public void validate() {
        results.clear();
        performErrorValidation();
        performWarningValidation();
    }

    @SuppressWarnings("unchecked")
	public Validator<T> getContext() {
        Class<?> c = getClass();
        try {
            return (Validator<T>)c.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public ValidationSet getMessages() {
        return results;
    }
}
