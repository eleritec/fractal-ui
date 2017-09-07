package net.eleritec.fractalui;

import javax.swing.*;

import net.eleritec.fractalui.events.ControllerChangeEvent;
import net.eleritec.fractalui.resource.AbstractResource;
import net.eleritec.fractalui.resource.EmptyResource;
import net.eleritec.fractalui.validation.ValidationMessage;
import net.eleritec.fractalui.validation.report.MessageDialogReporter;
import net.eleritec.fractalui.validation.report.MessageReporter;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 1, 2008
 * Time: 3:51:52 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractView<T> extends JPanel {
	private static final long serialVersionUID = -3455451765205610305L;

	private AbstractResource resource;
    private AbstractViewPresenter<T> viewPresenter;
    private MessageReporter messageReporter;

    protected abstract void createComponents();
    protected abstract void initializeLayout();
    protected abstract AbstractViewPresenter<T> createPresenter();

    protected AbstractView() {
        this(null, true);
    }

    protected AbstractView(AbstractResource resource) {
        this(resource, true);
    }

    protected AbstractView(boolean initNow) {
        this(null, initNow);
    }

    protected AbstractView(AbstractResource resource, boolean initNow) {
        setResource(resource);
        if(initNow) {
            initializeView();
        }
    }


    protected void initializeView() {
        createComponents();
        initializeComponents();
        initializeLayout();
        initializeListeners();
        initializePresenter();
    }

    protected void initializeComponents() {

    }

    public AbstractViewPresenter<T> getViewPresenter() {
        return viewPresenter;
    }

    public void controllerChanged(ControllerChangeEvent<T> event) {
        getViewPresenter().controllerChanged(event);
    }

    protected void initializeListeners() {
        
    }

    protected void initializePresenter() {
        viewPresenter = createPresenter();
        if(viewPresenter==null) {
            viewPresenter = new AbstractViewPresenter.EmptyViewPresenter<T>();
        }
        viewPresenter.setView(this);
    }

    public JComponent toComponent() {
        return this;
    }

    protected AbstractResource createResource() {
        return EmptyResource.RESOURCE;
    }

    public AbstractResource getResource() {
        return resource;
    }

    protected void setResource(AbstractResource resource) {
        this.resource = resource==null? createResource(): resource;
    }

    protected UIComponentFactory getComponentFactory() {
        return resource.getFactory();
    }


    public MessageReporter getMessageReporter() {
        if(messageReporter==null) {
            messageReporter = createDefaultMessageReporter();
        }
        return messageReporter;
    }

    public void setMessageReporter(MessageReporter messageReporter) {
        this.messageReporter = messageReporter;
    }

    public List<ValidationMessage> reportMessages(List<? extends Object> messages) {
        MessageReporter reporter = getMessageReporter();
        return reporter.reportMessages(messages);
    }

    protected MessageReporter createDefaultMessageReporter() {
        return new MessageDialogReporter(this);
    }
}
