package com.global.datastructure;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Shiva Krishna Komuravelly
 *
 */
public class SortedArrayList<T> extends ArrayList<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1321849292399973056L;

	@SuppressWarnings("unchecked")
	public void insertSorted(T value) {
		add(value);
		Comparable<T> cmp = (Comparable<T>) value;
		for (int i = size() - 1; i > 0 && cmp.compareTo(get(i - 1)) < 0; i--)
			Collections.swap(this, i, i - 1);
	}
}
