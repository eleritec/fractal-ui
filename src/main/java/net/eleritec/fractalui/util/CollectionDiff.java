package net.eleritec.fractalui.util;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Jul 15, 2008
 * Time: 1:40:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class CollectionDiff {
    private ArrayList<Object> adds;
    private ArrayList<Object> removes;


    public CollectionDiff() {
        adds = new ArrayList<Object>();
        removes = new ArrayList<Object>();
    }

    public ArrayList<Object> getAdds() {
        return adds;
    }

    public ArrayList<Object> getRemoves() {
        return removes;
    }
}
