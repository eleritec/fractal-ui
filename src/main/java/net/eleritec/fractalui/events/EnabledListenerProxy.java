package net.eleritec.fractalui.events;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

/**
 * @author cbutler
 */
public class EnabledListenerProxy extends AbstractInvocationHandler implements
                                                                 Enabler {
    private boolean enabled;
    private Object delegate;

    public static void setEnabled(Object object, boolean enabled) {
        if(object instanceof EnabledListenerProxy) {
            ((EnabledListenerProxy)object).setEnabled(enabled);
        }
    }

    public static Object getProxy(Object delegate, Class...interfaces) {
        ClassLoader cl = EnabledListenerProxy.class.getClassLoader();
        EnabledListenerProxy proxy = new EnabledListenerProxy(delegate);
        Class[] classes = getInterfaces(interfaces, Enabler.class);
        return Proxy.newProxyInstance(cl, classes, proxy);
    }

    private static Class[] getInterfaces(Class[] interfaces, Class...required) {
        ArrayList<Class> classes = new ArrayList<Class>();
        for(Class c: interfaces) {
            classes.add(c);
        }

        for(Class c: required) {
            if(!classes.contains(c)) {
                classes.add(c);
            }
        }

        return classes.toArray(new Class[0]);
    }

    public EnabledListenerProxy(Object delegate) {
        this.delegate = delegate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Object getDelegate() {
        return delegate;
    }

    protected String[] getNonProxiedMethodNames() {
        return new String[] {"isEnabled", "setEnabled"};
    }

    protected Object dispatch(Object proxy, Method method, Object[] args) throws Throwable {
        if(isEnabled()) {
            Method impl = findMethod(delegate, method);
            return impl==null? null: impl.invoke(delegate, args);
        }
        return null;
    }
}
