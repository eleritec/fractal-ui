package net.eleritec.fractalui.launcher;

import net.eleritec.fractalui.AbstractView;
import net.eleritec.fractalui.ViewBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 8, 2008
 * Time: 10:06:32 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ViewLauncher extends AbstractLauncher {
    public abstract ViewBuilder createBuilder();

    protected AbstractView createView() {
        ViewBuilder builder = createBuilder();
        return builder.buildView();
    }

}
