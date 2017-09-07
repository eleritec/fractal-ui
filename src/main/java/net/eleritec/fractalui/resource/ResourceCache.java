package net.eleritec.fractalui.resource;


import static net.eleritec.fractalui.util.StringUtil.isEmpty;
import static net.eleritec.fractalui.util.StringUtil.isNotEmpty;

import java.util.HashMap;
import java.util.Map;

import net.eleritec.fractalui.util.StringUtil;


public class ResourceCache<T> {

	private Map<String, T> cache = new HashMap<>();
	
	public void set(Object key, T item) {
		set(StringUtil.toString(key), item);
	}
	
	public void set(String key, T item) {
		if(isNotEmpty(key)) {
			if(item==null) {
				cache.remove(key);
			}
			else {
				cache.put(key, item);
			}
		}
	}
	
	public T get(Object key) {
		return get(StringUtil.toString(key));
	}
	
	public T get(String key) {
		return isEmpty(key)? null: cache.get(key);
	}
}
