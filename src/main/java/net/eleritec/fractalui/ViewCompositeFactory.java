package net.eleritec.fractalui;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 8, 2008
 * Time: 9:41:32 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ViewCompositeFactory<M> {
    public AbstractCompositeView<M> createView();
}
