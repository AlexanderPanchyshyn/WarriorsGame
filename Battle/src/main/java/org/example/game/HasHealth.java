package org.example.game;

interface HasHealth {
    int getHealth();
    default boolean isAlive() {
        return getHealth() > 0;
    }
}
