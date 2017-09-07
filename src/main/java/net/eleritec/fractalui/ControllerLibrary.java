package net.eleritec.fractalui;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 24, 2008
 * Time: 4:13:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ControllerLibrary {
    public AbstractViewController<?> getController(String controllerName);
    public List<AbstractViewController<?>> getControllers();

    public static class Adapter implements ControllerLibrary {
        private ArrayList<AbstractViewController<?>> controllers =
                new ArrayList<AbstractViewController<?>>(0);

        public AbstractViewController<?> getController(String controllerName) {
            return null;
        }

        public List<AbstractViewController<?>> getControllers() {
            return controllers;
        }
    }
}
