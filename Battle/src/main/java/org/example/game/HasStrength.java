package org.example.game;

@FunctionalInterface
interface HasStrength {
    int getStrength();
    default void hit(CanReceiveDamage opponent) {
        opponent.receiveDamage(this);
    }
}
