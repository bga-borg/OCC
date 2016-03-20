package com.bg.thsb.helper;

/**
 * GenericBox
 *
 */
public class BoxedValue<T> {

	private T value;

	public T getValue(){
		return value;
	}

	public void setValue(T value){
		this.value = value;
	}
}
