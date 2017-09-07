package net.eleritec.fractalui.defaults;

import net.eleritec.fractalui.AbstractViewController;

/**
 * @author cbutler
 */
public abstract class DefaultCompositeController<T> extends AbstractViewController<T> {

    protected abstract void updateChildModels();
    
    public void fireModelChanged(Object source) {
        super.fireModelChanged(source);
        updateChildModels();
    }


}
