package net.eleritec.fractalui;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.eleritec.fractalui.events.AbstractUITopic;
import net.eleritec.fractalui.validation.UIValidationHelper;
import net.eleritec.fractalui.validation.Validator;


/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Mar 31, 2008
 * Time: 12:58:03 PM
 * To change this template use File | Settings | File Templates.
 */

public abstract class AbstractViewController<T> {
    private ViewSnapshot<T> snapshot;
    private AbstractViewController<?> parent;
    private List<AbstractViewController<?>> children;

    private boolean ready;
    private ControllerLibrary library;
    private T dataModel;
    private ArrayList<AbstractViewPresenter<T>> presenters;
    private Set<AbstractUITopic> topics;
    private Validator<T> validator;
    private UIValidationHelper<T> validationHelper;

    protected abstract void initializeTopics();
    protected abstract void initializeListeners();

    protected AbstractViewController() {
        setValidationHelper(null);
        children = new ArrayList<AbstractViewController<?>>(0);
        presenters = new ArrayList<AbstractViewPresenter<T>>();
        topics = new HashSet<AbstractUITopic>();
        initializeTopics();
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        if(this.ready!=ready) {
            this.ready = ready;
            if(ready) {
                initializeListeners();
            }
        }
    }

    public Set<AbstractUITopic> getTopics() {
        return topics;
    }

    public void setTopics(Set<AbstractUITopic> topics) {
        this.topics = topics;
    }

    public ControllerLibrary getLibrary() {
        return library;
    }

    public void setLibrary(ControllerLibrary library) {
        this.library = library;
    }

    public T getDataModel() {
        return dataModel;
    }
    
    public boolean isValidModel(T model) {
    	return true;
    }

    public void setDataModel(T dataModel) {
        if(dataModel==null || isValidModel(dataModel)) {
            if(dataModel!=this.dataModel) {
                notifyModelChangePending();
            }
            this.dataModel = dataModel;
            dataModelUpdated();
            fireModelChanged(this);
        }
    }

    protected void notifyModelChangePending() {
        if(dataModel!=null) {
            cleanupDataModel();
        }
    }

    protected void cleanupDataModel() {

    }

    protected void dataModelUpdated() {
        if(getDataModel()!=null) {
            setupDataModel();
        }
    }

    protected void setupDataModel() {

    }

    protected void addTopics(AbstractUITopic...topics) {
        for(AbstractUITopic topic: topics) {
            addTopic(topic);
        }
    }

    protected void addTopic(AbstractUITopic topic) {
        if(topic!=null) {
            topics.add(topic);
        }
    }

    public void addView(AbstractView<T> view) {
        AbstractViewPresenter<T> presenter = view.getViewPresenter();
        if(presenter.setController(this)) {
            presenters.add(presenter);
        }
    }

    public void removeView(AbstractView<T> view) {
        AbstractViewPresenter<T> presenter = view.getViewPresenter();
        presenters.remove(presenter);
    }

    public List<AbstractViewPresenter<T>> getViewPresenters() {
        return presenters;
    }

    public void repaint() {
        for(AbstractViewPresenter<T> presenter: presenters) {
            presenter.repaint();
        }
    }

    public void refreshView() {
        for(AbstractViewPresenter<T> presenter: presenters) {
            presenter.refreshView();
        }
    }

    public AbstractUITopic getTopic(Class<?>...interfaces) {
        Set<AbstractUITopic> topics = getTopics(interfaces);
        return topics.size()==0? null: topics.iterator().next();
    }

    public Set<AbstractUITopic> getTopics(Class<?>...interfaces) {
        Set<AbstractUITopic> matches = new HashSet<AbstractUITopic>();
        for(AbstractUITopic topic: topics) {
            if(topic.isImplementor(interfaces)) {
                matches.add(topic);
            }
        }
        return matches;
    }

    public void fireModelChanged(Object source) {
        List<AbstractViewPresenter<T>> presenters = getViewPresenters();
        for(AbstractViewPresenter<T> presenter: presenters) {
            presenter.onModelChanged(source);
        }
    }

    public void addChild(AbstractViewController<?> controller) {
        if(controller!=null && !children.contains(controller)) {
            children.add(controller);
            controller.parent = this;
        }
    }

    public void removeChild(AbstractViewController<?> controller) {
        if(controller!=null && children.contains(controller)) {
            children.remove(controller);
            controller.parent = null;
        }
    }

    public List<AbstractViewController<?>> getChildren() {
        return new ArrayList<AbstractViewController<?>>(children);
    }

    public AbstractViewController<?> getParent() {
        return parent;
    }

    public ViewSnapshot<T> getSnapshot() {
        if(snapshot==null) {
            snapshot = new ViewSnapshot<T>(this);
        }
        return snapshot;
    }

    protected List<? extends AbstractViewPresenter<T>> getViewPresenters(Class<?>...types) {
        return getSnapshot().getPresenters(types);
    }

    protected AbstractViewController<?> getSubcontroller(String name) {
        return library==null? null: library.getController(name);
    }

    protected Object getPresenterDispatcher(Class<?>...types) {
        List<? extends AbstractViewPresenter<T>> presenters =
                getViewPresenters(types);

        // if there's only one presenter, then we don't need to hide behind
        // a proxy
        if(presenters.size()==1) {
            return presenters.get(0);
        }

        // weed out the non-interface classes before we create our proxy
        List<Class<?>> interfaces = new ArrayList<Class<?>>();
        for(Class<?> type: types) {
            if(type.isInterface()) {
                interfaces.add(type);
            }
        }

        Class<?>[] listenerTypes = interfaces.toArray(new Class[0]);
        return AbstractUITopic.createTopic(presenters, listenerTypes);
    }


    public Validator<T> getValidator() {
        return validator;
    }

    public void setValidator(Validator<T> validator) {
        this.validator = validator;
    }


    public void reportMessages(List<Object> messages) {
        AbstractView<T> view = getSnapshot().getView();
        if(view!=null) {
            view.reportMessages(messages);
        }
    }

    public boolean validate() {
        return validationHelper.validate();
    }

    public UIValidationHelper<T> getValidationHelper() {
        return validationHelper;
    }

    public void setValidationHelper(UIValidationHelper<T> validationHelper) {
        this.validationHelper = validationHelper==null?
                            createDefaultValidationHelper(): validationHelper;
        this.validationHelper.setController(this);
    }

    protected UIValidationHelper<T> createDefaultValidationHelper() {
        return new UIValidationHelper<T>(this);
    }
}

