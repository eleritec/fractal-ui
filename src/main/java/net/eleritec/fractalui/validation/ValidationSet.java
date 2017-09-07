package net.eleritec.fractalui.validation;


import java.util.List;

import net.eleritec.fractalui.util.CollectionUtil;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: cbutler
 * Date: May 27, 2008
 * Time: 7:07:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ValidationSet {
    private List<? extends Object> errors;
    private List<? extends Object> warnings;

    public ValidationSet() {
        setErrors(null);
        setWarnings(null);
    }

    public List<? extends Object> getErrors() {
        return errors;
    }

    public void setErrors(List<? extends Object> errors) {
        this.errors = errors==null? new ArrayList<Object>(0): errors;
    }

    public List<? extends Object> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<? extends Object> warnings) {
        this.warnings = warnings==null? new ArrayList<Object>(0): warnings;
    }

    public void add(ValidationSet messages) {
        if(messages!=null) {
            add(errors, messages.errors);
            add(warnings, messages.warnings);
        }
    }

    private void add(List<? extends Object> cache,
                                            List<? extends Object> messages) {
        if(cache!=null && messages!=null) {
            @SuppressWarnings("unchecked")
			List<Object> list = (List<Object>)cache;
            list.addAll(messages);
        }
    }

    public boolean hasErrors() {
        return CollectionUtil.hasData(errors);
    }

    public boolean hasWarnings() {
        return CollectionUtil.hasData(warnings);
    }

    public boolean isValid() {
        return !hasErrors() && !hasWarnings();
    }

    public void clear() {
        errors.clear();
        warnings.clear();
    }

    public void addError(Object error) {
        addItem(errors, error);
    }

    public void addWarning(Object warning) {
        addItem(warnings, warning);
    }

    private void addItem(List<? extends Object> list, Object item) {
        if(list!=null & item!=null) {
            @SuppressWarnings("unchecked")
			List<Object> items = (List<Object>)list;
            items.add(item);
        }
    }
}
