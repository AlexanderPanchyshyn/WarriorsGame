package org.example.game;


public class Warrior implements Cloneable, HasHealth, HasStrength, CanReceiveDamage{
    private static final int STRENGTH = 5;
    private int health;
    private final int initialHealth;

    protected Warrior(int health) {
        initialHealth = this.health = health;
    }

    public Warrior() {
        this(50);
    }
    @Override
    public int getStrength() {
        return STRENGTH;
    }
    @Override
    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = Math.min(initialHealth, health);
    }

    public void receiveDamage(HasStrength damager) {
        setHealth(getHealth() - damager.getStrength());
    }

    @Override
    public Warrior clone() {
        try {
            return (Warrior) super.clone();
        } catch (CloneNotSupportedException e) {
        }
        throw new IllegalStateException("Never should get here!");
    }
}
