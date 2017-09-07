package net.eleritec.fractalui.validation.report;

import java.util.List;

import net.eleritec.fractalui.validation.ValidationMessage;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: May 27, 2008
 * Time: 10:13:29 AM
 * To change this template use File | Settings | File Templates.
 */
public interface MessageReporter {
    public ValidationMessage reportMessage(Object message);
    public List<ValidationMessage> reportMessages(List<? extends Object> messages);
}
