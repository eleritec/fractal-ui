package net.eleritec.fractalui.events;


import java.util.List;

import net.eleritec.fractalui.util.ObjectUtil;

import java.util.ArrayList;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Mar 31, 2008
 * Time: 1:12:31 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractUITopic extends AbstractInvocationHandler
                                                    implements UIDispatcher {
    private List subscribers;
//    private boolean synchronous;
    private Object dispatcher;

    public static Object createTopic(List delegates, Class...interfaces) {
        AbstractUITopic topic = new AbstractUITopic() {};
        for(Object listener: delegates) {
            topic.subscribe(listener);
        }
        return createTopic(topic, interfaces);
    }

    private static Object createTopic(AbstractUITopic topic, Class...interfaces) {
        ClassLoader cl = AbstractUITopic.class.getClassLoader();
        return Proxy.newProxyInstance(cl, interfaces, topic);
    }

    protected AbstractUITopic() {
        this(true);
    }

    protected AbstractUITopic(boolean synchronous) {
//        this.synchronous = synchronous;
        subscribers = new ArrayList();
        dispatcher = createDispatcher();
    }

    private Object createDispatcher() {
        Class[] interfaces = getClass().getInterfaces();
        return createTopic(this, interfaces);
    }

    public Object getDispatcher() {
        return dispatcher;
    }

    protected Object dispatch(Object proxy, Method method, Object[] args) throws Throwable {
        Object returnValue = null;
        for(Object subscriber: subscribers) {
            Method impl = findMethod(subscriber, method);
            if(impl!=null) {
                returnValue = impl.invoke(subscriber, args);
            }
        }
        return returnValue;
    }

    protected String[] getNonProxiedMethodNames() {
        return new String[] {"isSynchronous", "setSynchronous"};
    }


    public void subscribe(Object subscriber) {
        subscribe(subscriber, false);
    }

    public void subscribe(Object subscriber, boolean unique) {
        boolean add = subscriber!=null &&
                      (!unique || !subscribers.contains(subscriber));
        if(add) {
            subscribers.add(subscriber);
        }
    }

    public void unsubscribe(Object subscriber) {
        if(subscriber!=null) {
            subscribers.remove(subscriber);
        }
    }


    public boolean isSynchronous() {
//        return synchronous;
        return true;
    }

    public void setSynchronous(boolean synchronous) {
//        this.synchronous = synchronous;
    }

    public boolean isImplementor(Class...types) {
        return ObjectUtil.instanceOf(this, types);
    }

    public final List<Object> getSubscribers() {
        return new ArrayList<Object>(subscribers);
    }
}
