package net.eleritec.fractalui.validation.report;

import net.eleritec.fractalui.validation.ValidationMessage;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: May 27, 2008
 * Time: 8:23:14 PM
 * To change this template use File | Settings | File Templates.
 */
public interface MessageReport {
    public String getTitle();
    public String getDetails();
    public ValidationMessage getMessage();
}
