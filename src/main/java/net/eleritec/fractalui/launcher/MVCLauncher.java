package net.eleritec.fractalui.launcher;

import javax.swing.*;

import net.eleritec.fractalui.AbstractView;
import net.eleritec.fractalui.AbstractViewController;
import net.eleritec.fractalui.defaults.DefaultViewBuilder;
import net.eleritec.fractalui.resource.AbstractResource;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 24, 2008
 * Time: 1:00:39 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class MVCLauncher<M>extends AbstractLauncher<M> {
    protected DefaultViewBuilder<M> builder;
    private M customDataModel;

    public static <M> JFrame displayView(MVCLauncher<M> launcher) {
        return displayView(launcher, null);
    }

    public static <M> JFrame displayView(MVCLauncher<M> launcher, M dataModel) {
        launcher.setCloseOperation(JFrame.HIDE_ON_CLOSE);
        launcher.setCustomDataModel(dataModel);
        launcher.setStandalone(false);
        return launch(launcher);
    }

    protected abstract DefaultViewBuilder<M> createBuilder();
    protected abstract M createModel();

    public Object getCustomDataModel() {
        return customDataModel;
    }

    public void setCustomDataModel(M customDataModel) {
        this.customDataModel = customDataModel;
    }

    protected DefaultViewBuilder<M> getBuilder() {
        if(builder==null) {
            builder = createBuilder();
        }
        return builder;
    }

    protected AbstractView<M> createView() {
        return getView();
    }

    protected AbstractView<M> getView() {
        return getBuilder().getViewComponent();
    }

    protected AbstractViewController<M> getController() {
        return getBuilder().getViewController();
    }

    protected Object getDataModel() {
        return getController().getDataModel();
    }

    protected void initializeModel() {
        AbstractViewController<M> controller = getController();
        M model = customDataModel;

        if(model==null) {
            model = createModel();
        }

        if(model!=null && !controller.isValidModel(model)) {
            throw new RuntimeException("Invalid Data Model " +
               model.getClass() + " for Controller " + controller.getClass());
        }

        boolean setData = model!=null || controller.getDataModel()==null;
        if(setData) {
            controller.setDataModel(model);
        }
    }

    protected AbstractResource getResource() {
        return getView().getResource();
    }

    protected String getString(String key) {
        return getResource().getString(key);
    }
}
