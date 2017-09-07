package net.eleritec.fractalui.validation.report;

import javax.swing.*;

import net.eleritec.fractalui.dialog.OptionPaneModel;
import net.eleritec.fractalui.validation.ValidationMessage;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: May 27, 2008
 * Time: 8:25:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class OptionPaneMessageReport implements MessageReport {
    private OptionPaneModel model;
    private ValidationMessage message;

    public OptionPaneMessageReport(ValidationMessage message, OptionPaneModel model) {
        this.message = message;
        this.model = model;
    }

    public ValidationMessage getMessage() {
        return message;
    }

    public String getDetails() {
        return model.getMessage();
    }

    public String getTitle() {
        return model.getTitle();
    }

    public OptionPaneModel getModel() {
        return model;
    }

    public Icon getIcon() {
        return model.getIcon();
    }

    public Object[] getOptions() {
        return model.getOptions();
    }

    public int getOptionType() {
        return model.getOptionType();
    }

    public Object getDefaultOption() {
        return model.getDefaultOption();
    }
}
