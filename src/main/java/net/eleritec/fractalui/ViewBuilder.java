package net.eleritec.fractalui;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 8, 2008
 * Time: 10:50:33 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ViewBuilder<M> {
    public AbstractView<M> buildView();
}
