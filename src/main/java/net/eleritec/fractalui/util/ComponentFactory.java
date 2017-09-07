package net.eleritec.fractalui.util;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Jul 10, 2008
 * Time: 4:42:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class ComponentFactory {

    public static JRadioButton createRadio(String name) {
		return createRadio(name, "");
	}

	public static JRadioButton createRadio(String name, String text) {
		JRadioButton btn = new JRadioButton(text);
		btn.setName(name);
		return btn;
	}

	public static JTextField createTextField(String name) {
		return createTextField(name, "");
	}

	public static JTextField createTextField(String name, String text) {
		JTextField txt = new JTextField();
		txt.setName(name);
		return txt;
	}

    public static JButton createButton(String name, String text) {
        JButton button = new JButton(text);
        button.setName(name);
        return button;
    }

    public static JComboBox<?> createComboBox(String name) {
        JComboBox<?> combo = new JComboBox<>();
        combo.setName(name);
        return combo;
    }

    public static JLabel createLabel(String name, String text) {
        return createLabel(name, text, null);
    }

    public static JLabel createLabel(String name, String text, Component owner) {
        JLabel label = new JLabel(text);
        label.setName(name);
        label.setLabelFor(owner);
        return label;
    }
}
