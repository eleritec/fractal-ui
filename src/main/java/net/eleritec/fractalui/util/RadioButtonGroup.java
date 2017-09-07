package net.eleritec.fractalui.util;

import javax.swing.*;

import net.eleritec.fractalui.AbstractViewPresenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

/**
 * @author cbutler
 */
public class RadioButtonGroup extends ButtonGroup {
	private static final long serialVersionUID = -7239432213276835647L;

	private static final String GROUP_KEY = "RadioButtonGroup.GROUP_KEY";

    private String type;
    private ActionListener refresher;
    private AbstractViewPresenter<?> presenter;

    public RadioButtonGroup(String type, AbstractViewPresenter<?> presenter) {
        this.refresher = createRefresher();
        this.type = type;
        this.presenter = presenter;
    }

    private ActionListener createRefresher() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                presenter.refreshModel();
            }
        };
    }

    public void add(AbstractButton b) {
        if(b!=null) {
            super.add(b);
            ComponentUtil.setClientProperty(b, GROUP_KEY, this);
            b.addActionListener(refresher);
        }
    }

    public JRadioButton replace(JRadioButton oldButton, JRadioButton newButton,
                                Object value) {
        remove(oldButton);
        add(newButton, value);
        return newButton;
    }

    public void add(AbstractButton b, Object value) {
        add(b);
        ComponentUtil.setClientProperty(b, type, value);
    }


    public void remove(AbstractButton b) {
        if(b!=null) {
            b.removeActionListener(refresher);
            ComponentUtil.setClientProperty(b, type, null);
            ComponentUtil.setClientProperty(b, GROUP_KEY, null);
            super.remove(b);
        }
    }

    public Object getSelectedValue() {
        for(Enumeration<AbstractButton> en=getElements(); en.hasMoreElements();) {
            AbstractButton button = en.nextElement();
            if(button.isSelected()) {
                return ComponentUtil.getClientProperty(button, type);
            }
        }
        return null;
    }

    public AbstractButton getButton(Object value) {
        for(Enumeration<AbstractButton> en=getElements(); en.hasMoreElements();) {
            AbstractButton button = en.nextElement();
            Object obj = ComponentUtil.getClientProperty(button, type);
            if(ObjectUtil.equals(value, obj)) {
                return button;
            }
        }
        return null;
    }

    public void select(Object value) {
        AbstractButton button = getButton(value);
        if(button!=null) {
            button.setSelected(true);
        }
    }


}
