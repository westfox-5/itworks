package it.itworks.solution;

import it.itworks.models.Game;
import it.itworks.models.Demon;

import java.util.*;
import java.util.stream.Collectors;

public class Solution1 extends Solution {

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


        /*List<Demon> demons = game.getDemons();
        Map<Demon, Integer> mapMaxScore = new HashMap<>();
        for ( Demon demon : demons ) {
            if ( game.getStamina() >= demon.getStaminaForKill() ) {
                int maxScore = maxScore(demon, tMaxCurrent);
                mapMaxScore.put(demon, maxScore);
            }
        }

        LinkedHashMap<Demon, Integer> reverseSortedMap = new LinkedHashMap<>();

        mapMaxScore.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        return reverseSortedMap;*/
    }

    private int maxScore(Demon d, int tMax) {
        int score = 0;
        int n = Math.min(d.getNumberOfFragments(), tMax);
        for ( int i = 0; i < n; i++ ) {
            int fragment = d.getFragmentsForTurn().get(i).getFragment();
            score += fragment;
        }

        return score;
    }


}
