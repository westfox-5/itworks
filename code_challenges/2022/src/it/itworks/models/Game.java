package it.itworks.models;

import it.itworks.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@InputClass
@OutputClass
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

    @Output(position = 1)
    @OutputCollection(Demon.class)
    private final List<Demon> defeatedDemons;

    private int totalFragments;
    private int maxScore;

    public Game()  {
        Demon.count = 0;
        this.totalFragments = 0;
        this.maxScore = -1;
        this.defeatedDemons = new ArrayList<>();

    }

    /* GAME FUNCTIONS **/

    public void defeatDemon(Demon demon, Integer turn) {
        defeatedDemons.add(demon);
        demon.setTurnDefeat(turn);
        setStamina(stamina - demon.getStaminaForKill());
        // setStaminaMax(demon.getStaminaForKill());
    }

    public void collectFragments(Integer turn) {
        for ( Demon demon : getDefeatedDemons() ) {
            int t = turn - demon.getTurnDefeat();
            if ( t < demon.getFragmentsForTurn().size() ) {
                int fragments = demon.getFragmentsForTurn().get(t).getFragment();
                this.totalFragments += fragments;
                // System.out.println("[INFO] Collected " + fragments + " fragments from demon " + demon.getId());
            }
        }
    }

    public void recoverStamina(Integer turn) {
        getDefeatedDemons().stream()
            // dopo che sono passati esattamente `turnToRecoverStamina`, recupero la stamina dal demone
            .filter(demon -> demon.getTurnToRecoverStamina() - turn + demon.getTurnDefeat() == 0)
            .forEach(demon -> {
                setStaminaMax(demon.getStaminaRecovered());
                // System.out.println("[INFO] Recovering " + demon.getStaminaRecovered() + " stamina from demon " + demon.getId());
            });
    }

    // demons which are not defeated AND there is enough stamina to kill
    public List<Demon> getKillableDemons() {
        return getDemons().stream()
                .filter(d -> d.getTurnDefeat() == -1 )
                .filter(d -> d.getStaminaForKill() <= getStamina())
                .collect(Collectors.toList());
    }

    // demons which are already defeated OR there is not enough stamina to kill
    public List<Demon> getUnkillableDemons() {
        return getDemons().stream()
                .filter(d -> d.getTurnDefeat() >= 0 || d.getStaminaForKill() > getStamina()) // defeated OR not available stamina
                .collect(Collectors.toList());
    }

    private void setStaminaMax(Integer stamina) {
        setStamina(
                Math.min(getStamina() + stamina, getMaxStamina())
        );
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

    public int getTotalFragments() {
        return this.totalFragments;
    }

    public List<Demon> getDefeatedDemons() {
        return defeatedDemons;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public Game clone() {
        Game g = new Game();
        g.stamina = stamina;
        g.maxStamina = maxStamina;
        g.maxTurns = maxTurns;
        g.numDemons = numDemons;

        g.demons = new ArrayList<>();
        for (Demon d: getDemons()) {
            g.demons.add(d.clone());
        }

        return g;
    }
}
