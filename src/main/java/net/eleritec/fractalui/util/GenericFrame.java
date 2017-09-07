package net.eleritec.fractalui.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowListener;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Jul 10, 2008
 * Time: 4:27:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericFrame implements JContainer {
    private Container frame;

    public static GenericFrame getFrame(Container frame) {
        if(frame instanceof Frame) {
            return new GenericFrame((Frame)frame);
        }
        if(frame instanceof Dialog) {
            return new GenericFrame((Dialog)frame);
        }
        if(frame instanceof JInternalFrame) {
            return new GenericFrame((JInternalFrame)frame);
        }
        return null;
    }

    public static Container findParentContainer(Component c) {
        GenericFrame frame = findParentFrame(c);
        return frame==null? null: frame.getFrame();
    }

    public static GenericFrame findParentFrame(Component c) {
        if(c==null) {
            return null;
        }

        Container frame = SwingUtils.findFirstAncestor(c, Frame.class,
                                           Dialog.class, JInternalFrame.class);
        if(frame==null) {
            frame = c instanceof Window? (Window)c:
                    SwingUtilities.getWindowAncestor(c);
        }

        return getFrame(frame);
    }

    public static JDialog findDialog(Component c) {
        return (JDialog)findWindowContainer(c, JDialog.class);
    }

    public static JFrame findFrame(Component c) {
        return (JFrame)findWindowContainer(c, JFrame.class);
    }

    public static JInternalFrame findInternalFrame(Component c) {
        return (JInternalFrame)findWindowContainer(c, JInternalFrame.class);
    }

    private static Container findWindowContainer(Component c, Class<?> type) {
        Container container = findParentContainer(c);
        return container!=null && type.isAssignableFrom(container.getClass())?
               container: null;
    }

    public GenericFrame(Frame frame) {
        this.frame = frame;
    }

    public GenericFrame(Dialog dialog) {
        this.frame = dialog;
    }

    public GenericFrame(JInternalFrame frame) {
        this.frame = frame;
    }

    public Container getFrame() {
        return frame;
    }

    public void setTitle(String title) {
        if(frame instanceof Frame) {
            ((Frame)frame).setTitle(title);
        }
        else if(frame instanceof Dialog) {
            ((Dialog)frame).setTitle(title);
        }
        else if(frame instanceof JInternalFrame) {
            ((JInternalFrame)frame).setTitle(title);
        }
    }

    public String getTitle() {
        if(frame instanceof Frame) {
            return ((Frame)frame).getTitle();
        }
        if(frame instanceof Dialog) {
            return ((Dialog)frame).getTitle();
        }
        if(frame instanceof JInternalFrame) {
            return ((JInternalFrame)frame).getTitle();
        }
        return null;
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public boolean isVisible() {
        return frame.isVisible();
    }

    public void dispose() {
        if(frame instanceof Frame) {
            ((Frame)frame).dispose();
        }
        else if(frame instanceof Dialog) {
            ((Dialog)frame).dispose();
        }
        else if(frame instanceof JInternalFrame) {
            ((JInternalFrame)frame).dispose();
        }
    }

    public JDialog createChildDialog() {
        if(frame instanceof Frame) {
            return new JDialog((Frame)frame);
        }
        if(frame instanceof Dialog) {
            return new JDialog((Dialog)frame);
        }

        Window w = SwingUtilities.getWindowAncestor(frame);
        if(w instanceof Frame) {
            return new JDialog((Frame)w);
        }
        if(frame instanceof Dialog) {
            return new JDialog((Dialog)w);
        }

        return new JDialog();
    }

    public JComponent getContentPane() {
        Container c = null;
        if(frame instanceof JFrame) {
            c = ((JFrame)frame).getContentPane();
        }
        if(frame instanceof JDialog) {
            c = ((JDialog)frame).getContentPane();
        }
        if(frame instanceof JInternalFrame) {
            c = ((JInternalFrame)frame).getContentPane();
        }
        return c instanceof JComponent? (JComponent)c: null;
    }

    public JRootPane getRootPane() {
        if(frame instanceof JFrame) {
            return ((JFrame)frame).getRootPane();
        }
        if(frame instanceof JDialog) {
            return ((JDialog)frame).getRootPane();
        }
        if(frame instanceof JInternalFrame) {
            return ((JInternalFrame)frame).getRootPane();
        }
        return null;
    }

    public void setLocation(Point point) {
        frame.setLocation(point);
    }

    public void setLocation(int x, int y) {
        frame.setLocation(x, y);        
    }

    public int getWidth() {
        return frame.getWidth();
    }

    public int getHeight() {
        return frame.getHeight();
    }

    public Point getLocation() {
        return frame.getLocation();
    }

    public int getX() {
        return frame.getX();
    }

    public int getY() {
        return frame.getY();
    }


    public Object getClientProperty(Object key) {
        return ComponentUtil.getClientProperty(getRootPane(), key);
    }

    public void putClientProperty(Object key, Object value) {
        ComponentUtil.setClientProperty(getRootPane(), key, value);
    }

    public void addWindowListener(WindowListener listener) {
        if(frame instanceof Window) {
            ((Window)frame).addWindowListener(listener);
        }
    }

    public int getDefaultCloseOperation() {
        if(frame instanceof JFrame) {
            return ((JFrame)frame).getDefaultCloseOperation();
        }
        if(frame instanceof JDialog) {
            return ((JDialog)frame).getDefaultCloseOperation();
        }
        if(frame instanceof JInternalFrame) {
            return ((JInternalFrame)frame).getDefaultCloseOperation();
        }
        return JFrame.DO_NOTHING_ON_CLOSE;
    }

    public void setDefaultCloseOperation(int operation) {
        if(frame instanceof JFrame) {
            ((JFrame)frame).setDefaultCloseOperation(operation);
        }
        if(frame instanceof JDialog) {
            ((JDialog)frame).setDefaultCloseOperation(operation);
        }
        if(frame instanceof JInternalFrame) {
            ((JInternalFrame)frame).setDefaultCloseOperation(operation);
        }
    }
}
