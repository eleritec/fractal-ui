package net.eleritec.fractalui.resource;

import javax.swing.*;

import net.eleritec.fractalui.UIComponentFactory;

import java.awt.Color;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: May 1, 2008
 * Time: 10:49:01 AM
 * To change this template use File | Settings | File Templates.
 */
public interface AbstractResource {
    public char getCharacter(String key, char defaultValue);
    public char getCharacter(String key);
    public String getString(String key);
    public String getString(String key, String defaultValue);
    public String getPatternString(String key, Object... arguments);
    public String getLabelString(String key);
    public String getButtonString(String key);
    public String[] getStrings(String key);

    public UIComponentFactory getFactory();

    public Color getColor(String key);
    public Icon getIcon(String key);
}
