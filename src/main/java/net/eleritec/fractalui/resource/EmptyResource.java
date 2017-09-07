package net.eleritec.fractalui.resource;

import javax.swing.*;

import net.eleritec.fractalui.UIComponentFactory;

import java.awt.Color;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: May 1, 2008
 * Time: 11:01:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class EmptyResource implements AbstractResource {
    public static final EmptyResource RESOURCE = new EmptyResource();
    private UIComponentFactory factory;
    private Icon emptyIcon;

    public EmptyResource() {
        factory = new UIComponentFactory(this);
        emptyIcon = new ImageIcon();
    }

    public static boolean isEmpty(AbstractResource resource) {
        return resource==null || resource==RESOURCE;
    }

    public char getCharacter(String key, char defaultValue) {
        return defaultValue;
    }

    public char getCharacter(String key) {
        return 0;
    }

    public String getString(String key) {
        return key;
    }

    public String getString(String key, String defaultValue) {
        return defaultValue;
    }

    public String getPatternString(String key, Object... arguments) {
        return key;
    }

    public String getLabelString(String key) {
        return key;
    }

    public String getButtonString(String key) {
        return key;
    }

    public String[] getStrings(String key) {
        return new String[0];
    }

    public UIComponentFactory getFactory() {
        return factory; 
    }

    public Color getColor(String key) {
        return Color.BLACK;
    }

    public Icon getIcon(String key) {
        return emptyIcon;
    }
}
