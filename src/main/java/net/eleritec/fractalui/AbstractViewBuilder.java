package net.eleritec.fractalui;

import net.eleritec.fractalui.validation.Validator;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 8, 2008
 * Time: 9:35:21 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractViewBuilder<M> implements ViewBuilder<M> {

    public abstract AbstractViewController<M> createController();
    public abstract ViewComponentFactory<M> createViewFactory();

    public AbstractView<M> buildView() {
        AbstractViewController<M> controller = loadController();
        AbstractView<M> view = loadView();
        controller.addView(view);
        return view;
    }

    protected AbstractViewController<M> loadController() {
        AbstractViewController<M> controller = createController();
        controller.setValidator(createValidator());
        controller.setReady(true);
        return controller;
    }

    protected AbstractView<M> loadView() {
        ViewComponentFactory<M> factory = createViewFactory();
        return factory.createView();
    }

    public Validator<M> createValidator() {
        return null;
    }
}
