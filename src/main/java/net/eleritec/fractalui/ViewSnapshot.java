package net.eleritec.fractalui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.eleritec.fractalui.events.AbstractUITopic;
import net.eleritec.fractalui.util.ObjectUtil;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 25, 2008
 * Time: 11:05:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class ViewSnapshot<T> {
    private AbstractViewController<T> controller;

    public static <T> ViewSnapshot<T> getSnapshot(AbstractView<T> view) {
        AbstractViewController<T> controller =
                view.getViewPresenter().getViewController();
        return controller.getSnapshot();
    }


    public ViewSnapshot(AbstractViewController<T> controller) {
        this.controller = controller;
    }

    public AbstractViewController<?> getController() {
        return controller;
    }

    public AbstractView<T> getView() {
        List<AbstractView<T>> views = getViews();
        return views.size()==0? null: views.get(0);
    }

    public List<AbstractView<T>> getViews() {
        ArrayList<AbstractView<T>> views = new ArrayList<AbstractView<T>>();
        for(AbstractViewPresenter<T> presenter: controller.getViewPresenters()) {
            views.add(presenter.getView());
        }
        return views;
    }

    public List<AbstractViewController<?>> getSubcontrollers() {
        return getSubcontrollers(false);
    }

    public List<AbstractViewController<?>> getSubcontrollers(boolean deep) {
        ArrayList<AbstractViewController<?>> subs =
                new ArrayList<AbstractViewController<?>>();
        collectSubcontrollers(controller, subs, deep);
        return subs;
    }

    private void collectSubcontrollers(AbstractViewController<?> parent,
                                       List<AbstractViewController<?>> cache,
                                       boolean recurse) {
        List<AbstractViewController<?>> children = parent.getChildren();
        for(AbstractViewController<?> child: children) {
            if(!cache.contains(child)) {
                cache.add(child);
            }
        }

        if(recurse) {
            for(AbstractViewController<?> child: children) {
                collectSubcontrollers(child, cache, recurse);
            }
        }
    }

    public List<AbstractView<?>> getSubviews() {
        return getSubviews(false);
    }

    public List<AbstractView<?>> getSubviews(boolean deep) {
        List<AbstractViewController<?>> controllers = getSubcontrollers(deep);
        List<AbstractView<?>> subs = new ArrayList<AbstractView<?>>(controllers.size());
        for(AbstractViewController<?> ctrl: controllers) {
        	List<?> presObj = ctrl.getViewPresenters();
        	@SuppressWarnings("unchecked")
			List<AbstractViewPresenter<?>> presenters = (List<AbstractViewPresenter<?>>)presObj;
            for(AbstractViewPresenter<?> presenter: presenters) {
                AbstractView<?> view = presenter.getView();
                if(!subs.contains(view)) {
                    subs.add(view);
                }
            }
        }
        return subs;
    }

    public AbstractViewPresenter<T> getPresenter() {
        List<AbstractViewPresenter<T>> presenters = getPresenters();
        return presenters.size()==0? null: presenters.get(0);
    }

    public List<AbstractViewPresenter<T>> getPresenters() {
        return controller.getViewPresenters();
    }

    public List<AbstractViewPresenter<T>> getPresenters(Class<?>...types) {
        List<AbstractViewPresenter<T>> presenters = getPresenters();
        List<AbstractViewPresenter<T>> matches =
                new ArrayList<AbstractViewPresenter<T>>(presenters.size());
        for(AbstractViewPresenter<T> presenter: presenters) {
            if(ObjectUtil.instanceOf(presenter, types)) {
                matches.add(presenter);
            }
        }
        return matches;
    }

    public Set<AbstractUITopic> getSubtopics() {
        return getSubtopics(false);
    }

    public Set<AbstractUITopic> getSubtopics(boolean deep) {
        List<AbstractViewController<?>> controllers = getSubcontrollers(deep);
        Set<AbstractUITopic> topics = new HashSet<AbstractUITopic>();
        for(AbstractViewController<?> controller: controllers) {
            topics.addAll(controller.getTopics());
        }
        return topics;
    }

    public Set<AbstractUITopic> findTopics(Class<?>...types) {
        Set<AbstractUITopic> topics = new HashSet<AbstractUITopic>();
        collectTopics(controller, topics, types);
        return topics;
    }

    private void collectTopics(AbstractViewController<?> ctrl,
                               Set<AbstractUITopic> cache,
                               Class<?>...types) {
        Set<AbstractUITopic> topics = ctrl.getTopics(types);
        cache.addAll(topics);

        for(AbstractViewController<?> child: ctrl.getChildren()) {
            collectTopics(child, cache, types);
        }
    }
}
