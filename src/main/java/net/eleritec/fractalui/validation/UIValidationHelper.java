package net.eleritec.fractalui.validation;

import javax.swing.*;

import net.eleritec.fractalui.AbstractView;
import net.eleritec.fractalui.AbstractViewController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: May 27, 2008
 * Time: 6:39:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class UIValidationHelper<M> {
    private AbstractViewController<M> controller;

    public UIValidationHelper() {
        this(null);
    }

    public UIValidationHelper(AbstractViewController<M> controller) {
        this.controller = controller;
    }

    public AbstractViewController<M> getController() {
        return controller;
    }

    public void setController(AbstractViewController<M> controller) {
        this.controller = controller;
    }

    public ValidationSet performValidation() {
        ValidationSet validation = new ValidationSet();
        List<AbstractViewController<?>> controllers = getValidatingSubcontrollers();
        for(AbstractViewController<?> controller: controllers) {
            UIValidationHelper<?> helper = controller.getValidationHelper();
            ValidationSet subMessages = helper.performValidation();
            validation.add(subMessages);
        }

        Validator<M> validator = controller.getValidator();
        if(validator!=null) {
            validator = validator.getContext();
            validator.setController(controller);
            validator.validate();
            validation.add(validator.getMessages());
        }

        return validation;
    }

    protected List<AbstractViewController<?>> getValidatingSubcontrollers() {
        return controller.getSnapshot().getSubcontrollers();
    }

    public boolean validate() {
        ValidationSet messages = performValidation();
        if(messages.isValid()) {
            return true;
        }

        AbstractView<M> view = controller.getSnapshot().getView();
        if(messages.hasErrors()) {
            view.reportMessages(messages.getErrors());
            return false;
        }

        List<ValidationMessage> warningResults =
                    view.reportMessages(messages.getWarnings());
        for(ValidationMessage result: warningResults) {
            if(result.getResult()!= JOptionPane.OK_OPTION) {
                return false;
            }
        }

        return true;
    }
}
