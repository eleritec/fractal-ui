package net.eleritec.fractalui.util;

import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Jul 10, 2008
 * Time: 4:05:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class ComponentUtil {
    public static JComponent findMatchingComponent(Object key, Object value,
                                                   JComponent...components) {
        for(JComponent component: components) {
            Object found = getClientProperty(component, key);
            if(ObjectUtil.equals(found, value)) {
                return component;
            }
        }
        return null;
    }

    public static void setClientProperty(JContainer container, Object key,
                                   Object value) {
        if(container!=null && key!=null) {
            container.putClientProperty(key, value);
        }
    }


    public static void setClientProperty(JComponent component, Object key,
                                   Object value) {
        if(component!=null && key!=null) {
            component.putClientProperty(key, value);
        }
    }

    public static Object getClientProperty(JComponent component, Object key) {
        return component==null || key==null? null:
               component.getClientProperty(key);
    }

    public static Object getClientProperty(JContainer container, Object key) {
        return container==null || key==null? null:
               container.getClientProperty(key);
    }

    public static boolean getBoolean(JComponent component, Object key) {
        Object obj = getClientProperty(component, key);
        return obj instanceof Boolean && (Boolean)obj;
    }

    public static boolean getBoolean(JContainer container, Object key) {
        Object obj = getClientProperty(container, key);
        return obj instanceof Boolean && (Boolean)obj;
    }

    public static void setText(JTextComponent component, String text) {
        if(component!=null) {
            component.setText(text);
        }
    }

    public static void setSelected(AbstractButton button, boolean selected) {
        if(button!=null) {
            button.setSelected(selected);
        }
    }

    public static AbstractButton getSelected(AbstractButton...buttons) {
        for(AbstractButton button: buttons) {
            if(button.isSelected()) {
                return button;
            }
        }
        return null;
    }

    public static void normalize(JButton...buttons) {
        Dimension d = new Dimension();
        for(JButton button: buttons) {
            d.width = Math.max(d.width, button.getPreferredSize().width);
            d.height = Math.max(d.height, button.getPreferredSize().height);
        }

        for(JButton button: buttons) {
            button.setSize(d);
        }
    }

    public static void normalizePreferred(JButton...buttons) {
        Dimension d = new Dimension();
        for(JButton button: buttons) {
            d.width = Math.max(d.width, button.getPreferredSize().width);
            d.height = Math.max(d.height, button.getPreferredSize().height);
        }

        for(JButton button: buttons) {
            button.setPreferredSize(d);
        }
    }

    public static String[] getComponentNames(JComponent[] components) {
        java.util.List<String> names = new ArrayList<String>(components.length);
        for(JComponent jc: components) {
            String name = jc==null? null: jc.getName();
            if(name!=null) {
                names.add(name);
            }
        }
        return names.toArray(new String[0]);
    }


    public static HashMap<String, JComponent> getComponentsByName(
                                Collection<? extends JComponent> components) {
        HashMap<String, JComponent> componentsByName =
                                            new HashMap<String, JComponent>();
        for(JComponent jc: components) {
            String key = jc==null? null: jc.getName();
            if(key!=null) {
                componentsByName.put(key, jc);
            }
        }
        return componentsByName;
    }

    public static HashMap<String, JComponent> getComponentsByName(
                                JComponent[] components) {
        java.util.List<JComponent> componentList = Arrays.asList(components);
        return getComponentsByName(componentList);
    }

    public static java.util.List<JComponent> getComponentsByName(JComponent[] components, String...names) {
        HashMap<String, JComponent> nameMap = getComponentsByName(components);
        ArrayList<JComponent> list = new ArrayList<JComponent>(names.length);
        for(String name: names) {
            JComponent jc = nameMap.get(name);
            if(jc!=null) {
                list.add(jc);
            }
        }
        return list;
    }

    public static java.util.List<JComponent> sortByName(Collection<? extends JComponent> components,
                                                        java.util.List<String> template) {
        JComponent[] comps = components.toArray(new JComponent[0]);
        String[] temp = template.toArray(new String[0]);

        JComponent[] sorted = sortByName(comps, temp);
        return Arrays.asList(sorted);
    }

    public static JComponent[] sortByName(JComponent[] components, String[] template) {
        HashMap<String, JComponent> nameMap = getComponentsByName(components);
        String[] names = getComponentNames(components);

        CollectionUtil.sort(names, template);

        JComponent[] sorted = new JComponent[names.length];
        for(int i=0; i<names.length; i++) {
            sorted[i] = nameMap.get(names[i]);
        }
        return sorted;
    }

    public static void addMouseMotionListener(JComponent component,
                                              MouseMotionListener listener) {
        if(component!=null && listener!=null) {
            component.addMouseMotionListener(listener);
        }
    }

    public static void removeMouseMotionListener(JComponent component,
                                              MouseMotionListener listener) {
        if(component!=null && listener!=null) {
            component.removeMouseMotionListener(listener);
        }
    }

    public static void addTableModelListener(TableModel model,
                                              TableModelListener listener) {
        if(model!=null && listener!=null) {
            model.addTableModelListener(listener);
        }
    }

    public static void removeTableModelListener(TableModel model,
                                              TableModelListener listener) {
        if(model!=null && listener!=null) {
            model.removeTableModelListener(listener);
        }
    }
}
