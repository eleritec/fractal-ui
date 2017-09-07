package net.eleritec.fractalui.resource;

import java.awt.Color;
import java.util.HashMap;

/**
 * @author cbutler
 */
public class Palette {
    private AbstractResource resource;
    private HashMap<String, Color> colors;

    public Palette() {
        this(new EmptyResource());
    }

    public Palette(AbstractResource resource) {
        this.resource = resource;
        this.colors = new HashMap<String, Color>();
    }

    public Color getColor(String key) {
        Color c = colors.get(key);
        if(c==null) {
            c = resource.getColor(key);
            colors.put(key, c);
        }
        return c;
    }


    public AbstractResource getResource() {
        return resource;
    }

    public void setResource(AbstractResource resource) {
        this.resource = resource;
        colors.clear();
    }
}
