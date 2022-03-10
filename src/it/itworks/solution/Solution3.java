package it.itworks.solution;

import it.itworks.models.Demon;
import it.itworks.models.Game;

import java.util.Comparator;
import java.util.TreeSet;

public class Solution3 extends Solution {
    public Demon getNextDemon(Game game, int tMaxCurrent) {
        TreeSet<Demon> treeSet = new TreeSet<>(
                Comparator.comparingDouble(Demon::getCurrentMaxScore).reversed()
        );

        for ( Demon demon : game.getKillableDemons()) {
            int maxScore = maxScore(demon, tMaxCurrent);
            demon.setCurrentMaxScore(maxScore);

            treeSet.add(demon);
        }

        return treeSet.isEmpty() ? null: treeSet.iterator().next();
    }

    private int maxScore(Demon d, int tMax) {
        /*int score = 0;
        int n = Math.max(d.getNumberOfFragments(), tMax);
        for ( int i = 0; i < n; i++ ) {
            int fragment = d.getFragmentsForTurn().get(i).getFragment();
            score += fragment;
        }

        return score;*/

        //System.out.println("Demon " + d.getId() + ": " + d.getTurnToRecoverStamina());
        return d.getTurnToRecoverStamina();
    }


}
