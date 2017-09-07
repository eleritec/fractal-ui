package net.eleritec.fractalui.dialog;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: May 27, 2008
 * Time: 7:50:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class OptionPaneModel {
    private String title;
    private String message;
    private Icon icon;
    private int optionType;
    private String[] options;
    private String defaultOption;

    public OptionPaneModel() {
        optionType = JOptionPane.DEFAULT_OPTION;
    }


    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOptionType() {
        return optionType;
    }

    public void setOptionType(int optionType) {
        this.optionType = optionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDefaultOption() {
        return defaultOption;
    }

    public void setDefaultOption(String defaultOption) {
        this.defaultOption = defaultOption;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
