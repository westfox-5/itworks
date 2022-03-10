package it.itworks.solution;

import it.itworks.models.Game;
import it.itworks.models.Demon;

import java.util.*;
import java.util.stream.Collectors;

public class Solution1 implements Solution {
    public void solve(Game game) {
        int tMax = game.getMaxTurns();


        //LinkedHashMap preserve the ordering of elements in which they are inserted
        // LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();



        // reverseSortedMap.get

        Map<Integer, Integer> staminaToReturn = new HashMap<>();

        for ( int i = 0; i < tMax; i++ ) {
            int tMaxCurrent = tMax - i;
            LinkedHashMap<Demon, Integer> demonScore = getCurrentMaxScore(game, tMaxCurrent);

        }
    }

    private LinkedHashMap<Demon, Integer> getCurrentMaxScore(Game game, int tMaxCurrent) {
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
        int n = Math.max(d.getNumberOfFragments(), tMax);
        for ( int i = 0; i < n; i++ ) {
            int fragment = d.getFragmentsForTurn().get(i).getFragment();
            score += fragment;
        }

        return score;
    }


}
