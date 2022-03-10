package it.itworks.models;

import it.itworks.annotations.*;

import java.util.ArrayList;
import java.util.List;

@InputClass
@OutputClass
public class Demon {
    public static int count = 0;

    @Output(position = 1, newLine = true)
    private Integer id; // TODO set final

    @Input(position = 1, max = 100_000)
    private Integer staminaForKill;

    @Input(position = 2, max = 1_000_000)
    private Integer turnToRecoverStamina;

    @Input(position = 3, max = 100_000)
    private Integer staminaRecovered;

    @Input(position = 4, max = 100_000)
    private Integer numberOfFragments;

    @Input(position = 5, max = 100_000)
    @InputCollection(Fragment.class)
    private List<Fragment> fragmentsForTurn;

    private int turnDefeat;
    private double currentMaxScore;

    public Demon() {
        this.id = count++;
        this.turnDefeat = -1;
    }

    public Integer getId() {
        return id;
    }

    public Integer getStaminaForKill() {
        return staminaForKill;
    }

    public void setStaminaForKill(Integer staminaForKill) {
        this.staminaForKill = staminaForKill;
    }

    public Integer getTurnToRecoverStamina() {
        return turnToRecoverStamina;
    }

    public void setTurnToRecoverStamina(Integer turnToRecoverStamina) {
        this.turnToRecoverStamina = turnToRecoverStamina;
    }

    public Integer getStaminaRecovered() {
        return staminaRecovered;
    }

    public void setStaminaRecovered(Integer staminaRecovered) {
        this.staminaRecovered = staminaRecovered;
    }

    public Integer getNumberOfFragments() {
        return numberOfFragments;
    }

    public void setNumberOfFragments(Integer numberOfFragments) {
        this.numberOfFragments = numberOfFragments;
    }

    public List<Fragment> getFragmentsForTurn() {
        return fragmentsForTurn;
    }

    public void setFragmentsForTurn(List<Fragment> fragmentsForTurn) {
        this.fragmentsForTurn = fragmentsForTurn;
    }

    public void setTurnDefeat(int turnDefeat) {
        this.turnDefeat = turnDefeat;
    }

    public int getTurnDefeat() {
        return turnDefeat;
    }

    public double getCurrentMaxScore() {
        return currentMaxScore;
    }

    public void setCurrentMaxScore(double currentMaxScore) {
        this.currentMaxScore = currentMaxScore;
    }

    public Demon clone() {
        Demon d = new Demon();
        d.id = id;
        d.staminaForKill = staminaForKill;
        d.turnToRecoverStamina = turnToRecoverStamina;
        d.staminaRecovered = staminaRecovered;
        d.numberOfFragments = numberOfFragments;

        d.fragmentsForTurn = new ArrayList<>();
        for (Fragment f: getFragmentsForTurn()) {
            d.fragmentsForTurn.add(f);
        }

        return d;
    }
}
