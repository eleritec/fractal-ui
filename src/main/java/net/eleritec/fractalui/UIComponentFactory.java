package net.eleritec.fractalui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import net.eleritec.fractalui.resource.AbstractResource;
import net.eleritec.fractalui.resource.EmptyResource;
import net.eleritec.fractalui.util.ComponentFactory;

import java.awt.Font;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: May 1, 2008
 * Time: 12:28:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class UIComponentFactory {
    private AbstractResource resource;

    public UIComponentFactory() {
        this(null);
    }

    public UIComponentFactory(AbstractResource resource) {
        setResource(resource);
    }

    public AbstractResource getResource() {
        return resource;
    }

    public void setResource(AbstractResource resource) {
        this.resource = resource==null? EmptyResource.RESOURCE: resource;
    }

    public JRadioButton createRadio(String name, String textKey) {
        return ComponentFactory.createRadio(name, getString(textKey));
    }

    public JButton createButton(String name, String textKey) {
        return ComponentFactory.createButton(name, getString(textKey)) ;
    }


    public JTextField createTextField(String name) {
        return ComponentFactory.createTextField(name);
    }

    public JTextField createTextField(String name, String textKey) {
        return ComponentFactory.createTextField(name, getString(textKey));
    }

    private String getString(String key) {
        return resource.getString(key);
    }

    private String getLabelString(String key) {
        return resource.getLabelString(key);
    }

    public JComboBox<?> createComboBox(String name) {
        return ComponentFactory.createComboBox(name);
    }

    public JLabel createLabel(String name, String textKey) {
        return createLabel(name, textKey, null);
    }

    public JLabel createLabel(String name, String textKey, JComponent owner) {
        return ComponentFactory.createLabel(name, getLabelString(textKey), owner);
    }

    public Border createTitledBorder(String title) {
        return createTitledBorder(title, Font.PLAIN);
    }

    public Border createTitledBorder(String title, int style) {
        TitledBorder border = new TitledBorder(getString(title));
        Font f = border.getTitleFont();
        if(style!=f.getStyle()) {
            border.setTitleFont(f.deriveFont(style));
        }
        return border;
    }

}
