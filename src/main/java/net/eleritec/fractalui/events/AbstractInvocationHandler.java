package net.eleritec.fractalui.events;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 28, 2008
 * Time: 12:09:10 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractInvocationHandler implements InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
                                                            throws Throwable {
        if(isProxyIgnored(method)) {
            return method.invoke(this, args);
        }
        return dispatch(proxy, method, args);
    }

    protected abstract Object dispatch(Object proxy, Method method,
                                       Object[] args) throws Throwable;

    protected boolean isProxyIgnored(Method method) {

        boolean ignored = "toString".equals(method.getName()) ||
               "equals".equals(method.getName()) ||
               "hashCode".equals(method.getName());

        if(!ignored) {
            String[] methodNames = getNonProxiedMethodNames();
            if(methodNames!=null) {
                for(String name: methodNames) {
                    if(name!=null && name.equals(method.getName())) {
                        return true;
                    }
                }
            }
        }

        return ignored;
    }

    protected String[] getNonProxiedMethodNames() {
        return null;
    }

    protected Method findMethod(Object listener, Method template) throws NoSuchMethodException {
        Class[] params = template.getParameterTypes();
        Class returnType = template.getReturnType();
        String name = template.getName();
        Class owner = listener.getClass();

        Method method = owner.getMethod(name, params);
        if(!isPublic(owner)) {
            Method candidate = null;
            List<Class> interfaces = getTargetInterfaces(owner);

            for(Class superType: interfaces) {
                candidate = findMethod(superType, name, params);
                if(candidate!=null) {
                    method = candidate;
                    break;
                }
            }
        }

        return returnType.equals(method.getReturnType())? method: null;
    }

    private List<Class> getTargetInterfaces(Class target) {
        ArrayList<Class> interfaces = new ArrayList<Class>();

        collectInterfaces(interfaces, target, target);
        for(Class parent=target.getSuperclass(); parent!=null;
                parent=parent.getSuperclass()) {
            collectInterfaces(interfaces, parent, target);
        }
        collectInterfaces(interfaces, getClass(), target);


        return interfaces;
    }

    private void collectInterfaces(List<Class> interfaces, Class target,
                                   Class base) {
        for(Class c: target.getInterfaces()) {
            if(c.isAssignableFrom(base) && !interfaces.contains(c)) {
                interfaces.add(c);
            }
        }
    }

    private Method findMethod(Class type, String name, Class[] params) {
        try {
            return type.getMethod(name, params);
        } catch(Exception e) {
            return null;
        }
    }

    private boolean isPublic(Class type) {
        return Modifier.isPublic(type.getModifiers());
    }
}
