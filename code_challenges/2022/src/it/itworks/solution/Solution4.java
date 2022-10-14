package it.itworks.solution;

import it.itworks.models.Demon;
import it.itworks.models.Game;

import java.util.Comparator;
import java.util.TreeSet;

public class Solution4 extends Solution {

    public double S_MUL; // score
    public double SK_MUL; // stamina for kill
    public double SR_MUL; // stamina recovered
    public double TR_MUL; // stamina for kill

    public Solution4(double smul, double skmul, double srmul, double trmul) {
        this.S_MUL = smul;
        this.SK_MUL = skmul;
        this.SR_MUL = srmul;
        this.TR_MUL = trmul;
    }

    public Demon getNextDemon(Game game, int tMaxCurrent) {
        TreeSet<Demon> treeSet = new TreeSet<>(
                Comparator.comparingDouble(Demon::getCurrentMaxScore).reversed()
        );

        for (Demon demon : game.getKillableDemons()) {
            double maxScore = maxScore(game, demon, tMaxCurrent);
            demon.setCurrentMaxScore(maxScore);

            treeSet.add(demon);
        }

        return treeSet.isEmpty() ? null : treeSet.iterator().next();
    }

    private double maxScore(Game game, Demon d, int tMax) {
        int score = 0;
        int n = Math.min(d.getNumberOfFragments(), tMax);
        for (int i = 0; i < n; i++) {
            int fragment = d.getFragmentsForTurn().get(i).getFragment();
            score += fragment;
        }

        int staminaForKill = d.getStaminaForKill();
        int turnToRecoverStamina = d.getTurnToRecoverStamina();
        int staminaRecovered = d.getStaminaRecovered();

        double ratioScore = (double) score / (double)  game.getMaxScore() * S_MUL;

        double ratioStaminaForKill = (double) staminaForKill / (double) game.getMaxStamina() * SK_MUL;

        double ratioStaminaRecovered = (double) staminaRecovered / (double) game.getMaxStamina() * SR_MUL;

        //double ratioStaminaRecovered = (double) staminaRecovered / (double) turnToRecoverStamina;
        // double ratioTurnToRecover = 1 / ((double) turnToRecoverStamina / (double) game.getMaxTurns()) * TR_MUL;
        double ratioTurnToRecover = 1 - (double) turnToRecoverStamina / (double) game.getMaxTurns() * TR_MUL;

        double ratioStaminaRecoveredPerTurn = (double) staminaRecovered / (double) turnToRecoverStamina;

        double val = ratioScore + ratioStaminaForKill + ratioStaminaRecovered + ratioTurnToRecover;

        val = ratioScore + ratioStaminaForKill + ratioStaminaRecovered + ratioTurnToRecover;
        //System.out.println("Demon " + d.getId() + ":\t" + ratioScore + "\t" + ratioStaminaForKill + "\t" + ratioStaminaRecovered + "\t" + ratioTurnToRecover);

        return val;
        //return ratioScore + ratioStaminaForKill + ratioStaminaRecoveredPerTurn;
        // return ratioScore + ratioStaminaForKill + ratioStaminaRecovered + ratioTurnToRecover;
    }


}
