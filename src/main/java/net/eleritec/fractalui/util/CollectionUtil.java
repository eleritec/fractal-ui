package net.eleritec.fractalui.util;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Jul 10, 2008
 * Time: 4:03:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class CollectionUtil {

    public static boolean inBounds(Collection<?> collection, int index) {
        return collection!=null && index>-1 && index<collection.size();
    }

    public static boolean inBounds(Object[] collection, int index) {
        return collection!=null && index>-1 && index<collection.length;
    }

    public static Object get(List<?> list, int index) {
        return inBounds(list, index)? list.get(index): null;
    }

    public static Object get(Object[] list, int index) {
        return inBounds(list, index)? list[index]: null; 
    }

    public static boolean hasData(Collection<?> collection) {
        return collection!=null && collection.size()>0;
    }

    public static void sort(Object[] data, Object[] template) {
        final HashMap<Object, Integer> indices = new HashMap<Object, Integer>();
        for(int i=0; i<template.length; i++) {
            if(template[i]!=null) {
                indices.put(template[i], i);
            }
        }

        for(int i=0; i<data.length; i++) {
            int index = template.length + i;
            if(data[i]!=null && !indices.containsKey(data[i])) {
                indices.put(data[i], index);
            }
        }

        Comparator<Object> c = new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                Integer index1 = o1==null? null: indices.get(o1);
                Integer index2 = o2==null? null: indices.get(o2);

                if(index1==index2) {
                    return 0;
                }

                if(index1==null) {
                    return 1;
                }

                if(index2==null) {
                    return -1;
                }

                return index1 - index2;
            }
        };

        Arrays.sort(data, c);
    }

//    public static List<Integer> asList(int[] data) {
//        ArrayList<Integer> list = new ArrayList<Integer>(data.length);
//        for(int element: data) {
//            list.add(element);
//        }
//        return list;
//    }
//
//    public static Integer[] toWrapper(int[] data) {
//        Integer[] wrapper = new Integer[data.length];
//        for(int i=0; i<data.length; i++) {
//            wrapper[i] = data[i];
//        }
//        return wrapper;
//    }
//
//    public static int[] toPrimitive(Integer[] data) {
//        int[] primitive = new int[data.length];
//        for(int i=0; i<data.length; i++) {
//            primitive[i] = data[i];
//        }
//        return primitive;
//    }
//
//    public static int[] toPrimitive(List<Integer> data) {
//        Integer[] array = data.toArray(new Integer[0]);
//        return toPrimitive(array);
//    }
//
//    public static <T extends Comparable<? super T>> void sort(List<T> list,
//                                                            boolean reverse) {
//        if(reverse) {
//            Collections.sort(list, Collections.reverseOrder());
//        }
//        else {
//            Collections.sort(list);
//        }
//    }
//
    public static void intersect(Collection<? extends Object> dataset,
                                 Collection<? extends Object> constraints,
                                                                boolean keep) {
        if(dataset!=null && constraints!=null) {
            if(keep) {
                dataset.retainAll(constraints);
            }
            else {
                dataset.removeAll(constraints);
            }
        }
    }

    public static CollectionDiff getDiffs(Collection<? extends Object> dataset,
                                 Collection<? extends Object> baseline) {

        CollectionDiff diff = new CollectionDiff();
        for(Object item: baseline) {
            if(!dataset.contains(item)) {
                diff.getAdds().add(item);
            }
        }

        for(Object item: dataset) {
            if(!baseline.contains(item)) {
                diff.getRemoves().add(item);
            }
        }
        return diff;
    }

}
