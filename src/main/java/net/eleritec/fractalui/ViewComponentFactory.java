package net.eleritec.fractalui;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Mar 27, 2008
 * Time: 10:55:55 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ViewComponentFactory<M> {
    public AbstractView<M> createView();

}
