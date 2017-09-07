package net.eleritec.fractalui.resource;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import net.eleritec.fractalui.util.ResourceUtil;

public class IconResource extends ResourceCache<Icon> {

	public void setIcon(Object key, Icon icon) {
		set(key, icon);
	}
	
	public Icon getIcon(Object key) {
		return get(key);
	}
	
	public void loadIcon(Object key, String iconUri) {
		try {
			ImageIcon icon = loadIcon(iconUri);
			if(icon!=null) {
				setIcon(key, icon);
			}
		}
		catch(Exception e) {
			// silent fail
		}
	}
	
	public static ImageIcon loadIcon(String imageUri) {
		try {
			return new ImageIcon(ImageIO.read(ResourceUtil.findResource(imageUri)));
		}
		catch(Exception e) {
			// silent fail
			return null;
		}
	}
}
