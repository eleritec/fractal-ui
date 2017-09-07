package net.eleritec.fractalui;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 24, 2008
 * Time: 3:15:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ViewCreationProvider<M> {
    public ViewProvider<M> getProvider(String providerName);
}
