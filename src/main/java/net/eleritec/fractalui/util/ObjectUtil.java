package net.eleritec.fractalui.util;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Jul 10, 2008
 * Time: 3:59:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ObjectUtil {

    public static boolean equals(Object o1, Object o2) {
        if(o1==o2) {
            return true;
        }

        if(o1==null || o2==null) {
            return false;
        }

        return o1.equals(o2);

    }

    /**
     * Determines whether the given object is an instanceof of one of the
     * specified types.
     * @param object the object whose type we wish to check.
     * @param types the types we wish to check against
     * @return true if the supplied object is an instance of one of the
     * specified types; otherwise false.
     */
    public static boolean instanceOf(Object object, Class<?>...types) {
        if(object==null || types==null || types.length==0) {
            return false;
        }

        Class<?> target = object.getClass();
        for(Class<?> type: types) {
            if(type.isAssignableFrom(target)) {
                return true;
            }
        }

        return false;
    }

    public static boolean getBoolean(Object obj) {
        if(obj==null) {
            return false;
        }

        if(obj instanceof Boolean) {
            return (Boolean)obj;
        }

        if(obj instanceof String) {
            return "true".equalsIgnoreCase(obj.toString().trim());
        }

        return false;
    }
}
