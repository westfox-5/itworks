package it.itworks.example;

import it.itworks.annotations.Input;
import it.itworks.annotations.InputClass;
import it.itworks.annotations.Output;
import it.itworks.annotations.OutputClass;

@InputClass
@OutputClass
public class Antenna extends Element {
    @Input(position = 3, max = 300)
    @Output(position = 3, newLine = true)
    private Integer velocity;

    public Antenna() {
    }

    public Integer getVelocity() {
        return velocity;
    }

    public void setVelocity(Integer velocity) {
        this.velocity = velocity;
    }
}
