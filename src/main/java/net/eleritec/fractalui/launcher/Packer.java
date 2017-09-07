package net.eleritec.fractalui.launcher;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Jul 15, 2008
 * Time: 5:01:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class Packer {
    private Component component;
    private PackPolicy policy;

    public Packer(Component component) {
        this(component, null);
    }

    public Packer(Component component, PackPolicy policy) {
        this.component = component;
        setPolicy(policy);
    }

    public Component getComponent() {
        return component;
    }

    public PackPolicy getPolicy() {
        return policy;
    }

    public void setPolicy(PackPolicy policy) {
        this.policy = policy==null? PackPolicy.BOTH: policy;
    }

    public void pack() {
        Window w = SwingUtilities.getWindowAncestor(component);
        if(w==null) {
            return;
        }

        Dimension size = w.getSize();
        w.pack();

        switch(policy) {
            case VERTICAL:
                w.setSize(size.width, w.getHeight());
                break;
            case HORIZONTAL:
                w.setSize(w.getWidth(), size.height);
                break;
        }
        w.validate();
    }

    public void packLater() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                pack();
            }
        });
    }
}
