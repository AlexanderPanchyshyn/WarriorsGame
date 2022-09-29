package org.example.game;

interface CanReceiveDamage extends HasHealth {
    public void receiveDamage(HasStrength damager);
}
