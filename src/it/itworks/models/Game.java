package it.itworks.models;

import it.itworks.annotations.Input;
import it.itworks.annotations.InputClass;
import it.itworks.annotations.InputCollection;

import java.util.List;
import java.util.stream.Collectors;

@InputClass
public class Game {
    @Input(position = 1, max = 100_000)
    private Integer stamina;

    @Input(position = 2, max = 100_000)
    private Integer maxStamina;

    @Input(position = 3, max = 1_000_001)
    private Integer maxTurns;

    @Input(position = 4, max = 100_001)
    private Integer numDemons;

    @Input(position = 5)
    @InputCollection(Demon.class)
    private List<Demon> demons;

    public Game()  { }

    /* GAME FUNCTIONS **/

    public void defeatDemon(Demon demon, Integer turn) {
        demon.setTurnDefeat(turn);
        setStaminaMax(demon.getStaminaForKill());
    }

    public void recoverStamina(Integer turn) {
        getDemons().stream()
            // dopo che sono passati esattamente `turnToRecoverStamina`, recupero la stamina dal demone
            .filter(demon -> demon.getTurnToRecoverStamina() - turn == 0)
            .forEach(demon -> setStaminaMax(demon.getStaminaRecovered()));
    }

    public List<Demon> getDefeatedDemons(final Game game) {
        return game.getDemons().stream()
                .filter(d -> d.getStaminaForKill() <= game.getStamina())
                .collect(Collectors.toList());
    }

    public List<Demon> getUndefeatedDemons(final Game game) {
        return game.getDemons().stream()
                .filter(d -> d.getStaminaForKill() > game.getStamina())
                .collect(Collectors.toList());
    }

    private void setStaminaMax(Integer stamina) {
        setStamina(Math.max(getStamina() + stamina, getMaxStamina()));
    }

    /* GETTER & SETTER **/

    public Integer getStamina() {
        return stamina;
    }

    public void setStamina(Integer stamina) {
        this.stamina = stamina;
    }

    public Integer getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(Integer maxStamina) {
        this.maxStamina = maxStamina;
    }

    public Integer getMaxTurns() {
        return maxTurns;
    }

    public void setMaxTurns(Integer maxTurns) {
        this.maxTurns = maxTurns;
    }

    public Integer getNumDemons() {
        return numDemons;
    }

    public void setNumDemons(Integer numDemons) {
        this.numDemons = numDemons;
    }

    public List<Demon> getDemons() {
        return demons;
    }

    public void setDemons(List<Demon> demons) {
        this.demons = demons;
    }
}
