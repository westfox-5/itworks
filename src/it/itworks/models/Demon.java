package it.itworks.models;

import it.itworks.annotations.Input;
import it.itworks.annotations.InputClass;
import it.itworks.annotations.InputCollection;

import java.util.List;

@InputClass
public class Demon {
    private static Integer count;
    private final Integer id;

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

    public Demon() {
        this.id = count++;
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
        turnDefeat = turnDefeat;
    }

    public int getTurnDefeat() {
        return turnDefeat;
    }
}
