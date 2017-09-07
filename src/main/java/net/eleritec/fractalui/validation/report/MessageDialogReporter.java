package net.eleritec.fractalui.validation.report;

import javax.swing.*;

import net.eleritec.fractalui.AbstractView;
import net.eleritec.fractalui.dialog.OptionPaneModel;
import net.eleritec.fractalui.resource.AbstractResource;
import net.eleritec.fractalui.util.GenericFrame;
import net.eleritec.fractalui.validation.ValidationMessage;

import java.awt.Component;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: May 27, 2008
 * Time: 10:11:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class MessageDialogReporter extends AbstractMessageReporter {

    public MessageDialogReporter() {
    }

    public MessageDialogReporter(AbstractView view) {
        super(view);
    }

    public List<ValidationMessage> reportMessages(List<? extends Object> messages) {
        List<ValidationMessage> results = new ArrayList<ValidationMessage>();
        ValidationMessage error = null;
        if(messages!=null) {
            for(Object message: messages) {
                ValidationMessage realMessage = getValidationMessage(message);
                // only process 1 error message per list.  OptionPane reporting
                // for multiple errors is annoying to the end user.  have them
                // correct the error and revalidate before reporting the next
                // one in the list.
                if(realMessage.isError() && error!=null) {
                    continue;
                }

                ValidationMessage result = reportMessage(realMessage);
                results.add(result);

                // if we've processed an error, mark it so we don't process
                // any more
                if(result.isError()) {
                    error = result;
                }
            }
        }
        return results;
    }

    protected OptionPaneMessageReport createReport(ValidationMessage message,
                                                    AbstractResource resource) {
        String configData = resource.getString(message.getKey());
        OptionPaneModel model = getOptionPaneModel(configData);
        String text = getMessageText(message);
        model.setMessage(text);

        return new OptionPaneMessageReport(message, model);
    }

    protected OptionPaneModel getOptionPaneModel(String config) {
        return new OptionPaneModel();
    }

    protected boolean isReportValid(MessageReport report) {
        return report instanceof OptionPaneMessageReport &&
                getDialogParent()!=null && super.isReportValid(report);
    }

    protected int reportError(MessageReport report) {
        Component parent = getDialogParent();
        OptionPaneMessageReport r = (OptionPaneMessageReport)report;

        JOptionPane.showMessageDialog(parent, r.getDetails(),
                  r.getTitle(), JOptionPane.ERROR_MESSAGE, r.getIcon());
                return JOptionPane.OK_OPTION;
    }

    protected int reportWarning(MessageReport report) {
        Component parent = getDialogParent();
        OptionPaneMessageReport r = (OptionPaneMessageReport)report;

        if(r.getOptions()==null) {
            return JOptionPane.showConfirmDialog(parent, r.getDetails(),
                     r.getTitle(), r.getOptionType(),
                     JOptionPane.WARNING_MESSAGE, r.getIcon());
        }
        return JOptionPane.showOptionDialog(parent, r.getDetails(),
                     r.getTitle(), r.getOptionType(),
                     JOptionPane.WARNING_MESSAGE, r.getIcon(),
                     r.getOptions(), r.getDefaultOption());
    }


    protected Component getDialogParent() {
        return GenericFrame.findParentContainer(getView());
    }



}
