package it.itworks.example;

import it.itworks.annotations.Input;
import it.itworks.annotations.InputClass;
import it.itworks.annotations.Output;

@InputClass
public class Element {
    @Input(position = 1)
    @Output(position = 1)
    protected Integer x;

    @Input(position = 2)
    @Output(position = 2)
    protected Integer y;

    public Element() {
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
