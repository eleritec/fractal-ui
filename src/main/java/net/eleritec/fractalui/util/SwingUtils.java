package net.eleritec.fractalui.util;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Jul 10, 2008
 * Time: 4:25:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class SwingUtils {

    /**
     * Places the specified container within a JFrame as a content pane, sizes
     * the frame to the specified dimensions, and makes the frame visible.
     * @param component the component to install within a JFrame
     * @param width the desired width of the containing JFrame
     * @param height the desired height of the containing JFrame
     * @param closeOperation the default close operation for the JFrame
     * @return a JFrame using the specified Container as its content pane
     */
    public static JFrame realizeInFrame(Component component, int width,
                                            int height, int closeOperation) {
        JFrame frame = new JFrame();
        Container contentPane = createContainer(component);
        frame.setContentPane(contentPane);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(closeOperation);
        frame.setVisible(true);
        return frame;
    }

    /**
     * Places the specified container within a JFrame as a content pane, sizes
     * the frame to the specified dimensions, and makes the frame visible. Sets
     * the JFrame's default close operation to JFrame.DO_NOTHING_ON_CLOSE.
     * @param component the component to install within a JFrame
     * @param width the desired width of the containing JFrame
     * @param height the desired height of the containing JFrame
     * @return a JFrame using the specified Container as its content pane
     * @see #realizeInFrame(java.awt.Component, int, int, int)
     */
    public static JFrame realizeInFrame(Component component, int width,
                                            int height) {
        return realizeInFrame(component, width, height,
                                  JFrame.DO_NOTHING_ON_CLOSE);
    }

    public static JDialog showInDialog(Container container, Component parent,
                                       int width, int height) {
        return showInDialog(container, parent, width, height, false);
    }

    public static JDialog showInDialog(Component component, Component parent,
                                       int width, int height, boolean modal) {
        JDialog dialog = placeInDialog(component, parent, width, height, modal);
        dialog.setVisible(true);
        return dialog;
    }

    public static JDialog placeInDialog(Component component, Component parent,
                                       int width, int height, boolean modal) {
        GenericFrame frame = GenericFrame.findParentFrame(parent);
        JDialog dialog = frame==null? new JDialog(): frame.createChildDialog();
        dialog.setSize(width, height);
        Container contentPane = createContainer(component);
        dialog.setContentPane(contentPane);
        dialog.setModal(modal);
        return dialog;
    }

    private static Container createContainer(Component c) {
        if(c instanceof Container) {
            return (Container)c;
        }

        JPanel p = new JPanel(new BorderLayout());
        p.add(c, BorderLayout.CENTER);
        return p;
    }

    /**
     * Given a component within the AWT container hierarchy, returns the nearest ancestor
     * container that is an instance of one of the supplied classes.
     * @param c the Component whose ancestor we wish to find
     * @param ancestorTypes an array of Classes among which Component ancestors will be checked.
     * @return a Container that is an instance of one of the supplied classes.
     */
    public static Container findFirstAncestor(Component c, Class<?>...ancestorTypes) {
        for(Container parent=c.getParent(); parent!=null; parent=parent.getParent()) {
            if(isAmongTypes(parent, ancestorTypes)) {
                return parent;
            }
        }
        return null;
    }

    private static boolean isAmongTypes(Object obj, Class<?>...types) {
        if(obj!=null) {
            Class<?> clazz = obj.getClass();
            for(Class<?> type: types) {
                if(type.isAssignableFrom(clazz)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sets the current default look and feel to the native systems look and feel
     * if there is one.  Otherwise the default cross platform look and feel is used.
     *
     * @see javax.swing.UIManager#getSystemLookAndFeelClassName()
     * @see javax.swing.UIManager#setLookAndFeel(String)
     *
     */
    public static void setSystemLookAndFeel() {
        String className = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(className);
        } catch(Exception e) {
            className = UIManager.getCrossPlatformLookAndFeelClassName();
            try {
                UIManager.setLookAndFeel(className);
            } catch(Exception ex) {
                // should never happen, there is always an x-platform LaF
                ex.printStackTrace();
            }
        }
    }

    /**
     * Finds the parent Frame, Dialog, or JInternalFrame that contains the
     * specified Component and calls its dispose() method.  If the Component
     * is not inside of a Frame, Dialog, or JInternalFrame, this method does
     * nothing.
     * @param component the Component whose parent frame we wish to dispose.
     */
    public static void disposeFrame(Component component) {
        if(component!=null) {
            GenericFrame frame = GenericFrame.findParentFrame(component);
            if(frame!=null) {
                frame.setVisible(false);
                frame.dispose();
            }
        }
    }

    /**
     * Sets the preferred-width for the specified Component, preserving its
     * current preferred-height.
     * @param component This Component whose preferred-width should be set.
     * @param width The preferred width to set.
     * @see #setPreferredHeight(javax.swing.JComponent, int)
     * @see javax.swing.JComponent#setPreferredSize(java.awt.Dimension)
     */
    public static void setPreferredWidth(JComponent component, int width) {
		Dimension d = component.getPreferredSize();
		d.width = width;
		component.setPreferredSize(d);
	}

    /**
     * Sets the minimum-width for the specified Component, preserving its
     * current minimum-height.
     * @param component This Component whose minimum-width should be set.
     * @param width The minimum width to set.
     * @see javax.swing.JComponent#setMinimumSize(java.awt.Dimension)
     */
    public static void setMinimumWidth(JComponent component, int width) {
		Dimension d = component.getMinimumSize();
		d.width = width;
		component.setMinimumSize(d);
	}

    public static void setMinimumHeight(JComponent component, int height) {
		Dimension d = component.getMinimumSize();
		d.height = height;
		component.setMinimumSize(d);
    }

    public static void setMaximumHeight(JComponent component, int height) {
		Dimension d = component.getMaximumSize();
		d.height = height;
		component.setMaximumSize(d);        
    }

    /**
     * Sets the preferred-height for the specified Component, preserving its
     * current preferred-width.
     * @param component This Component whose preferred-width should be set.
     * @param height The preferred height to set.
     * @see #setPreferredWidth(javax.swing.JComponent, int)
     * @see javax.swing.JComponent#setPreferredSize(java.awt.Dimension)
     */
    public static void setPreferredHeight(JComponent component, int height) {
		Dimension d = component.getPreferredSize();
		d.height = height;
		component.setPreferredSize(d);
	}

    public static void setFont(JComponent comp, int style) {
        Font f = comp.getFont().deriveFont(style);
        comp.setFont(f);
    }

    public static void setFont(JComponent comp, int style, float size) {
        Font f = comp.getFont().deriveFont(style, size);
        comp.setFont(f);
    }

    public static int getMinHeaderTextWidth(JTable table, int column) {
        String text = table.getColumnName(column);
        return getTextWidth(table, text);
    }

    public static void lockColumnsToHeaderText(JTable table, int... columns) {
        for(int column: columns) {
            int width = getMinHeaderTextWidth(table, column);
            TableColumn tc = table.getColumnModel().getColumn(column);
            tc.setMaxWidth(width);
            tc.setMinWidth(width);
            tc.setPreferredWidth(width);
        }
    }

    public static void lockColumnToText(JTable table, int column, String text) {
        int width = getTextWidth(table, text);
        TableColumn tc = table.getColumnModel().getColumn(column);
        tc.setMaxWidth(width);
        tc.setMinWidth(width);
        tc.setPreferredWidth(width);
    }


    private static int getTextWidth(JTable table, String text) {
        if(text==null) {
            text = "";
        }
        Font f = table.getTableHeader().getFont();
        int w = table.getTableHeader().getFontMetrics(f).stringWidth(text) + 14;

        return Math.max(w, 20);
    }

    public static void addFrameHeight(final Component component, final int height, boolean later) {
        if(later) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    addFrameHeight(component, height);
                }
            });
        }
        else {
            addFrameHeight(component, height);
        }
    }

    public static void addFrameHeight(Component component, int height) {
        Window w = component instanceof Window? (Window)component:
                SwingUtilities.getWindowAncestor(component);
        if(w!=null) {
            Dimension d = w.getSize();
            d.height += height;
            w.setSize(d);
            w.validate();
        }
    }

    public static Rectangle getRowBounds(JTable table, int row) {
        if(table==null || row >= table.getRowCount()) {
            return null;
        }

        int top = 0;
        for(int i=0; i<row; i++) {
            top += table.getRowHeight(i);
        }

        int height = table.getRowHeight(row);
        int width = table.getWidth();
        return new Rectangle(0, top, width, height);
    }

    public static void scrollToRow(JTable table, int row) {
        Rectangle rect = getRowBounds(table, row);
        if(rect!=null) {
            table.scrollRectToVisible(rect);
        }
    }
}
