package it.itworks.models;

import it.itworks.annotations.Input;
import it.itworks.annotations.InputClass;

@InputClass
public class Snake {

	@Input(position = 1)
	Integer length;

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
	
}
