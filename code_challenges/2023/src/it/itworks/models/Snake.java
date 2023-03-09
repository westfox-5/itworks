package it.itworks.models;

import it.itworks.annotations.Input;
import it.itworks.annotations.InputClass;
import it.itworks.annotations.Output;

@InputClass
public class Snake {

	@Input(position = 1)
	Integer length;
	
	@Output(position =  1)
	Integer ci; // col iniziale inserimento snake
	
	@Output(position =  2)
	Integer ri; // row iniziale inserimento snake
	 
	@Output(position = 3)
	String initialDir;
	
	@Output(position = 4)
	Integer cw;
	
	@Output(position = 5)
	Integer rw;

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
	public String getInitialDir() {
		return initialDir;
	}

	public void setInitialDir(String initialDir) {
		this.initialDir = initialDir;
	}

	public Integer getCi() {
		return ci;
	}

	public void setCi(Integer ci) {
		this.ci = ci;
	}

	public Integer getRi() {
		return ri;
	}

	public void setRi(Integer ri) {
		this.ri = ri;
	}

	public Integer getCw() {
		return cw;
	}

	public void setCw(Integer cw) {
		this.cw = cw;
	}

	public Integer getRw() {
		return rw;
	}

	public void setRw(Integer rw) {
		this.rw = rw;
	}

}
