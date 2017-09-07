package net.eleritec.fractalui.dialog;

import javax.swing.*;

import net.eleritec.fractalui.AbstractUI;
import net.eleritec.fractalui.AbstractView;
import net.eleritec.fractalui.AbstractViewController;
import net.eleritec.fractalui.util.SwingUtils;

import java.awt.*;
import java.awt.event.*;

/**
 * @author cbutler
 */
public abstract class AbstractDialogLauncher<T> {
    protected AbstractUI<T> ui;
    protected Component owner;
    private String title;

    protected abstract T createModel();

    protected AbstractUI<T> createDefaultUI() {
        return null;
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

    public JDialog launchDialog() {
        JDialog dialog = createDialog();
        dialog.setVisible(true);
        return dialog;
    }

    protected JDialog createDialog() {
        AbstractView<T> view = initializeMVC();

        Dimension d = getInitialSize();
        JDialog dialog = SwingUtils.placeInDialog(view, owner, d.width,
                                                        d.height, isModal());
        setFrameDefaults(dialog);
        return dialog;
    }

    protected AbstractView<T> initializeMVC() {
        AbstractView<T> view = ui.getView();
        loadModel();
        initializeView(view);
        initializeListeners();
        return view;
    }

    protected void setFrameDefaults(JDialog dialog) {
        dialog.setTitle(getTitle());
        if(owner!=null) {
        	dialog.setLocationRelativeTo(owner);
        }

        Point location = getInitialLocation();
        if(location!=null) {
            dialog.setLocation(location);
        }

        dialog.addWindowListener(createDialogCloseListener());

        initializeFrame(dialog);
    }

    protected void loadModel() {
        T model = createModel();
        AbstractViewController<T> controller = ui.getController();
//        if(model!=null && !controller.isValidModel(model)) {
//            throw new RuntimeException("Invalid Data Model " +
//               model.getClass() + " for Controller " + controller.getClass());
//        }
        controller.setDataModel(model);
    }

    protected ActionListener createCancelAction() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtils.disposeFrame(ui.getView());
            }
        };
    }

    protected void onDialogClosed() {
    }


    protected void initializeFrame(JDialog dialog) {

    }

    protected void initializeView(AbstractView<T> view) {

    }

    protected void initializeListeners() {

    }

    public String getTitle() {
        return getString(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    protected String getString(String key) {
        return ui==null? key: ui.getResource().getString(key);
    }

    public AbstractUI<T> getUi() {
        return ui;
    }

    public void setUi(AbstractUI<T> ui) {
        this.ui = ui;
    }

    public Component getOwner() {
        return owner;
    }

    public void setOwner(Container owner) {
        this.owner = owner;
    }

    public boolean isModal() {
        return true;
    }

    protected WindowListener createDialogCloseListener() {
        return new WindowAdapter() {

            public void windowClosed(WindowEvent e) {
                onDialogClosed();
            }

            public void windowClosing(WindowEvent e) {
                onDialogClosed();
            }
        };
    }
}
