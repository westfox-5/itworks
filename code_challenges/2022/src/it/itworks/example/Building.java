package it.itworks.example;

import it.itworks.annotations.Input;
import it.itworks.annotations.InputClass;

@InputClass
public class Building extends Element {
    @Input(position = 3)
    private Integer dimension;

    public Building() {
    }

    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }
}
