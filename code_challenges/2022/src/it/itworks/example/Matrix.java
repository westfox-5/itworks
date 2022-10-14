package it.itworks.example;

import it.itworks.annotations.*;

import java.util.List;

@InputClass
@OutputClass
public class Matrix {
    @Input(position = 1, max = 600_000, min = 0)
    private Integer rows;

    @Input(position = 2, max = 600_000, min = 0)
    private Integer cols;

    @Input(position = 3)
    @Output(position = 1, newLine = true)
    private Integer numAntennas;

    @Input(position = 4)
    @InputCollection(Antenna.class)
    @Output(position = 2)
    @OutputCollection(Antenna.class)
    private List<Antenna> antennas;

    @Input(position = 5)
    private Integer numBuildings;

    @Input(position = 6)
    @InputCollection(Building.class)
    private List<Building> buildings;

    public Matrix() {
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public Integer getNumAntennas() {
        return numAntennas;
    }

    public void setNumAntennas(Integer numAntennas) {
        this.numAntennas = numAntennas;
    }

    public List<Antenna> getAntennas() {
        return antennas;
    }

    public void setAntennas(List<Antenna> antennas) {
        this.antennas = antennas;
    }

    public Integer getNumBuildings() {
        return numBuildings;
    }

    public void setNumBuildings(Integer numBuildings) {
        this.numBuildings = numBuildings;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }
}
