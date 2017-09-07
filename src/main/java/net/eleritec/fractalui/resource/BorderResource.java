package net.eleritec.fractalui.resource;

import javax.swing.border.Border;

public class BorderResource extends ResourceCache<Border> {

	public Border getBorder(Object key) {
		return get(key);
	}
	
	public void setBorder(Object key, Border border) {
		set(key, border);
	}
}
