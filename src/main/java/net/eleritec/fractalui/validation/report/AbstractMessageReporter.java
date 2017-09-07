package net.eleritec.fractalui.validation.report;

import javax.swing.*;

import net.eleritec.fractalui.AbstractView;
import net.eleritec.fractalui.resource.AbstractResource;
import net.eleritec.fractalui.validation.ValidationMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: May 27, 2008
 * Time: 8:20:43 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractMessageReporter implements MessageReporter {
    private AbstractView view;

    protected abstract MessageReport createReport(ValidationMessage message,
                                                    AbstractResource resource);
    protected abstract int reportError(MessageReport report);
    protected abstract int reportWarning(MessageReport report);

    public AbstractMessageReporter() {
    }

    public AbstractMessageReporter(AbstractView view) {
        this.view = view;
    }

    protected MessageReport createReport(ValidationMessage message) {
        AbstractResource resource = getResource();
        if(message==null || resource==null) {
            return null;
        }
        return createReport(message, resource);
    }

    protected String getMessageText(ValidationMessage message) {
        String key = message.getMessage();
        AbstractResource resource = getResource();
        if(resource==null) {
            return key==null? message.getKey(): key;
        }
        
        if(key==null) {
            return resource.getString(message.getKey());
        }
        return resource.getString(key);
    }


    public AbstractResource getResource() {
        return view==null? null: view.getResource();
    }

    public AbstractView getView() {
        return view;
    }

    public void setView(AbstractView view) {
        this.view = view;
    }

    public List<ValidationMessage> reportMessages(List<? extends Object> messages) {
        List<ValidationMessage> results = new ArrayList<ValidationMessage>();
        if(messages!=null) {
            for(Object message: messages) {
                ValidationMessage result = reportMessage(message);
                results.add(result);
            }
        }
        return results;
    }

    public ValidationMessage reportMessage(Object message) {
        if(message instanceof List) {
            List<ValidationMessage> results = reportMessages((List)message);
            return results.size()>0? results.get(0): null;
        }

        ValidationMessage messenger = getValidationMessage(message);
        MessageReport report = createReport(messenger);
        if(message!=null) {
            int response = report(report);
            if(messenger!=null) {
                messenger.setResult(response);
            }
        }
        return messenger;
    }

    protected ValidationMessage getValidationMessage(Object obj) {
        if(obj instanceof ValidationMessage) {
            return ((ValidationMessage)obj);
        }

        String key = obj==null? null: obj.toString();
        return new ValidationMessage(key);
    }

    protected int report(MessageReport report) {
        if(!isReportValid(report)) {
            return JOptionPane.CANCEL_OPTION;
        }
        
        switch(report.getMessage().getType()) {
            case ERROR:
                return reportError(report);
            case WARNING:
                return reportWarning(report);

        }
        return JOptionPane.OK_OPTION;
    }

    protected boolean isReportValid(MessageReport report) {
        return report!=null;
    }
}
