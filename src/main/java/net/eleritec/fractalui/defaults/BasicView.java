package net.eleritec.fractalui.defaults;

import javax.swing.*;

import net.eleritec.fractalui.AbstractView;
import net.eleritec.fractalui.AbstractViewPresenter;

import java.awt.FlowLayout;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: Apr 24, 2008
 * Time: 2:51:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class BasicView<T> extends AbstractView<T> {

	private static final long serialVersionUID = -6946119253849142723L;

	private JLabel label;
    private String text;

    public BasicView() {
        this("Basic View");
    }

    public BasicView(String text) {
        super(false);
        this.text = text;
        initializeView();
    }

    protected void createComponents() {
        label = new JLabel(this.text);
    }

    protected void initializeLayout() {
        setLayout(new FlowLayout());
        add(label);
    }

    protected AbstractViewPresenter<T> createPresenter() {
        return null;
    }
}
