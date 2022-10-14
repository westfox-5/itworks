package it.itworks.solution;

import it.itworks.models.Demon;
import it.itworks.models.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public abstract class Solution {
    public void solve(Game game) {
        int tMax = game.getMaxTurns();


        int maxScore = 0;

        for ( Demon d : game.getDemons() ) {
            int demonMaxScore = 0;

            int n = Math.min(game.getMaxTurns(), d.getNumberOfFragments());
            for ( int i = 0; i < n; i++ ) {
                int fragment = d.getFragmentsForTurn().get(i).getFragment();
                demonMaxScore += fragment;
            }

            if ( maxScore < demonMaxScore ) {
                maxScore = demonMaxScore;
            }
        }

        game.setMaxScore(maxScore);

        Map<Integer, Integer> staminaToReturn = new HashMap<>();

        for ( int t = 0; t < tMax; t++ ) {
            int tMaxCurrent = tMax - t;

            // 1. recupero stamina
            game.recoverStamina(t);

            // 2. selezione demone da uccidere
            /*getNextDemon();
            TreeSet<Demon> demons = getCurrentMaxScore(game, tMaxCurrent);
            if ( !demons.isEmpty() ) {
                Demon demon = demons.iterator().next();
                game.defeatDemon(demon, t);
                System.out.println(demon.getId());
            }*/

            Demon demon = getNextDemon(game, tMaxCurrent);
            if ( demon != null ) {
                game.defeatDemon(demon, t);
                // System.out.println("turno [" + t + "]: killed demon: " + demon.getId() + ", stamina: "+ game.getStamina());
            }

            // 3. colleziono i frammenti
            game.collectFragments(t);
        }
    }

    public abstract Demon getNextDemon(Game game, int tMaxCurrent);
}
