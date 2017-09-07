package net.eleritec.fractalui;

import javax.swing.event.ChangeListener;

import net.eleritec.fractalui.events.ControllerChangeEvent;
import net.eleritec.fractalui.events.ControllerListener;
import net.eleritec.fractalui.events.ControllerUITopic;
import net.eleritec.fractalui.events.EventMonitor;
import net.eleritec.fractalui.resource.AbstractResource;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 21, 2008
 * Time: 9:55:14 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractViewPresenter<T> implements ControllerListener<T> {
    private EventMonitor eventMonitor;
    private ControllerUITopic controllerTopic;
    private AbstractViewController<T> viewController;
    protected AbstractView<T> view;

    protected abstract void setupListeners();
    protected abstract void updateViewFromModel();

    protected AbstractViewPresenter() {
        initializeTopics();
    }

    protected void initializeTopics() {
        controllerTopic = new ControllerUITopic();
        controllerTopic.addListener(this);
    }

    public boolean setController(AbstractViewController<T> viewController) {
        if(isValidController(viewController) &&
                viewController!=this.viewController) {
            AbstractViewController<T> old = this.viewController;
            this.viewController = viewController;
            fireControllerChanged(old);
            return true;
        }
        return false;
    }
    
    protected boolean isValidController(AbstractViewController<T> controller) {
    	return true;
    }

    private void fireControllerChanged(AbstractViewController<T> previous) {
        ControllerChangeEvent<T> event = new ControllerChangeEvent<T>(this,
                                                      previous, viewController);
        controllerTopic.controllerChanged(event);
    }

    protected void cleanupPreviousController(AbstractViewController<T> previous) {

    }

    public void controllerChanged(ControllerChangeEvent<T> event) {
        if(event.getPreviousController()!=null) {
            cleanupPreviousController(event.getPreviousController());
        }
        if(event.getCurrentController()!=null) {
            setupNewController(event.getCurrentController());
        }
        
        onModelChanged(event);
    }

    protected void setupNewController(AbstractViewController<T> current) {

    }

    public AbstractViewController<T> getViewController() {
        return viewController;
    }

    public AbstractResource getResource() {
        return view==null? null: view.getResource();
    }

    protected T getDataModel() {
        AbstractViewController<T> controller = getViewController();
        return controller==null? null: controller.getDataModel();
    }

    public void refreshModel() {
        requestModelUpdateFromView();
    }

    public void refreshView() {
        requestViewUpdateFromModel();
    }

    protected void requestModelUpdateFromView() {
        if(getDataModel()!=null) {
            updateModelFromView();
            fireModelChanged();
        }
    }

    public void repaint() {
        getView().repaint();
    }

    public void fireModelChanged() {
        getViewController().fireModelChanged(this);
    }

    public void onModelChanged(Object updater) {
        if(isModelUpdateProcessRequired(updater)) {
            try {
                setMonitoredListenersEnabled(false);
                dispatchModelChange();
            }
            finally {
                setMonitoredListenersEnabled(true);
            }
        }
    }

    protected void requestViewUpdateFromModel() {
        onModelChanged(this);
    }

    private void dispatchModelChange() {
        if(getDataModel()==null) {
            loadDefaultState();
        }
        else {
            updateViewFromModel();
        }
    }

    private void dispatchDefaultState() {
        try {
            setMonitoredListenersEnabled(false);
            loadDefaultState();
        }
        finally {
            setMonitoredListenersEnabled(true);
        }
    }



    protected boolean isModelUpdateProcessRequired(Object source) {
        return true;
    }

    protected void loadDefaultState() {
        
    }

    protected void updateModelFromView() {
        
    }

    public boolean isValid() {
        return getView()!=null;
    }

    public AbstractView<T> getView() {
        return view;
    }

    public void setView(AbstractView<T> view) {
        if(this.view!=view) {
            if(this.view!=null) {
                teardownView();
            }

            this.view = view;
            setupView();
        }
    }

    protected void setupView() {
        if(isValid()) {
            initializeView();
            setupListeners();
            dispatchDefaultState();
        }
    }

    protected void initializeView() {

    }

    protected void teardownView() {
        teardownListeners();
        unloadState();
    }

    protected void unloadState() {
    }

    protected void teardownListeners() {
    }

    private EventMonitor getMonitor() {
        if(eventMonitor==null) {
            eventMonitor = new EventMonitor();
        }
        return eventMonitor;
    }

    private void setMonitoredListenersEnabled(boolean enabled) {
        if(eventMonitor!=null) {
            eventMonitor.setEnabled(enabled);
        }
    }

    protected ChangeListener createChangeListener(ChangeListener listener) {
        return getMonitor().createChangeListener(listener);
    }

    protected ItemListener createItemListener(ItemListener listener) {
        return getMonitor().createItemListener(listener);
    }

    protected ActionListener createActionListener(ActionListener listener) {
        return getMonitor().createActionListener(listener);
    }

    protected ChangeListener createChangeListener(Runnable runnable) {
        return getMonitor().createChangeListener(runnable);
    }

    protected ItemListener createItemListener(Runnable runnable) {
        return getMonitor().createItemListener(runnable);
    }
    
    protected ActionListener createActionListener(Runnable runnable) {
    	return getMonitor().createActionListener(runnable);
    }
  

    public static class EmptyViewPresenter<T> extends AbstractViewPresenter<T> {
        protected boolean isValidController(AbstractViewController<T> controller) {
            return true;
        }
        protected void setupListeners() {
        }
        protected void updateViewFromModel() {
        }
    }
}
