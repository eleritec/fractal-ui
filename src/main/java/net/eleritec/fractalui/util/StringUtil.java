package net.eleritec.fractalui.util;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Jul 10, 2008
 * Time: 5:27:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtil {

    public static boolean isEmpty(String data) {
        return data==null || data.trim().length()==0;
    }

    public static boolean isNotEmpty(String data) {
        return !isEmpty(data);
    }
    
    public static String toString(Object obj) {
    	return obj==null? null: obj.toString();
    }
}
