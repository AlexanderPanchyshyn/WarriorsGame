package org.example.game;

import java.util.Random;
import java.util.function.Supplier;

public class Seducer extends Warrior {
    private static final int STRENGTH = 3;
    private static final int SEDUCE_VALUE = 10;

    public Seducer() { super(40); }

    public int getStrength() {
        return STRENGTH;
    }
    private boolean seduce(Warrior opponent, Army myArmy, Army enemyArmy) {
        Random randomInt = new Random();
        int randomResult = randomInt.nextInt(100);
//        System.out.println(opponent.getClass());

        if (randomResult <= SEDUCE_VALUE){
            int opponentStrength = opponent.getStrength();

            myArmy.addSeducedUnit(opponent.clone());
            enemyArmy.removeSeducedUnit();
        }

        return randomResult <= SEDUCE_VALUE;
    }

    @Override
    public void hit(CanReceiveDamage opponent) {
        if (opponent instanceof Warrior enemy){
            if (/*this instanceof WarriorInArmy myInArmy && */opponent instanceof WarriorInArmy enemyInArmy){
                var attemptToSeduce = seduce(enemy, enemyInArmy.getArmy(), enemyInArmy.getArmy());
                if (attemptToSeduce) {
                    System.out.println("Yes");
                } else {
                    super.hit(opponent);
                }
            }
        } else {
            super.hit(opponent);
        }
    }
}
