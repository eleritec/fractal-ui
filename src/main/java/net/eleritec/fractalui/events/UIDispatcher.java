package net.eleritec.fractalui.events;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 1, 2008
 * Time: 2:52:39 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UIDispatcher {
    public boolean isSynchronous();
    public void setSynchronous(boolean synchronous);
}
