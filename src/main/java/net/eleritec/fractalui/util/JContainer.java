package net.eleritec.fractalui.util;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Jul 16, 2008
 * Time: 10:25:20 AM
 * To change this template use File | Settings | File Templates.
 */
public interface JContainer {
    public void putClientProperty(Object key, Object value);
    public Object getClientProperty(Object key);
}
