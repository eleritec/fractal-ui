package net.eleritec.fractalui.launcher;

import javax.swing.*;

import net.eleritec.fractalui.AbstractView;
import net.eleritec.fractalui.util.SwingUtils;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 24, 2008
 * Time: 1:00:56 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractLauncher<M> implements Runnable {
    private int closeOperation;
    private boolean standalone;
    protected JFrame frame;



    protected abstract AbstractView<M> createView();

    public static <M> JFrame launch(AbstractLauncher<M> launcher) {
        launcher.initializeEnvironment();
        return launchImpl(launcher);
    }

    protected static <M> JFrame launchImpl(AbstractLauncher<M> launcher) {
        if(EventQueue.isDispatchThread()) {
            launcher.run();
            return launcher.frame;
        }

        try {
            EventQueue.invokeAndWait(launcher);
            return launcher.frame;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected AbstractLauncher() {
        closeOperation = JFrame.EXIT_ON_CLOSE;
        standalone = true;
    }

    protected void initializeEnvironment() {

    }

    private void initializeConfigSystem() {

    }

    public Dimension getInitialSize() {
        return new Dimension(800, 600);
    }

    public Point getInitialLocation() {
        Dimension size = getInitialSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = screenSize.width/2 - size.width/2;
        int y = screenSize.height/2 - size.height/2;

        return new Point(x, y);
    }

    public String getTitle(AbstractView<M> view) {
        String title = "";
        if(view!=null) {
            title = view.getClass().getSimpleName();
        }
        return title;
    }

    private void initApplication() {
        if(isStandalone()) {
            initializeConfigSystem();
            initializeLookAndFeel();
        }
    }

    public void run() {
        initApplication();

        AbstractView<M> view = initializeMVC();

        Dimension d = getInitialSize();
        frame = SwingUtils.realizeInFrame(view, d.width, d.height,
                                          closeOperation);
        initPacking();
        setFrameDefaults(view);
    }

    protected void initPacking() {
        PackPolicy policy = getPackPolicy();
        if(policy!=null) {
            Dimension size = frame.getSize();
            frame.pack();
            switch(policy) {
                case VERTICAL:
                    frame.setSize(size.width, frame.getHeight());
                    frame.validate();
                    break;
                case HORIZONTAL:
                    frame.setSize(frame.getWidth(), size.height);
                    frame.validate();
                    break;
                default:
                	break;
            }
        }
    }


    protected AbstractView<M> initializeMVC() {
        AbstractView<M> view = createView();
        initializeModel();
        initializeView(view);
        initializeTopics();
        return view;
    }

    protected void setFrameDefaults(AbstractView<M> view) {
        String title = getTitle(view);
        frame.setTitle(title);

        Point location = getInitialLocation();
        if(location!=null) {
            frame.setLocation(location);
        }

        initializeFrame();
    }

    protected void initializeLookAndFeel() {
        SwingUtils.setSystemLookAndFeel();
    }

    protected void initializeFrame() {

    }

    protected void initializeView(AbstractView<M> view) {

    }

    protected void initializeModel() {
    }

    protected void initializeTopics() {

    }


    public int getCloseOperation() {
        return closeOperation;
    }

    public void setCloseOperation(int closeOperation) {
        this.closeOperation = closeOperation;
    }


    public boolean isStandalone() {
        return standalone;
    }

    public void setStandalone(boolean standalone) {
        this.standalone = standalone;
    }

    public PackPolicy getPackPolicy() {
        return null;
    }
}


