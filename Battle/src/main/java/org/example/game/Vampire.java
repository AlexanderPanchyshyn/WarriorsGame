package org.example.game;

public class Vampire extends Warrior implements KnowsDamageDealt {
    private static final int STRENGTH = 4;
    private static final int VAMPIRISM  = 50;
    public Vampire() {
        super(40);
    }
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public void hit(CanReceiveDamage opponent) {
        int damageDealt = hitAndReportDamage(opponent);
        final int percents = 100;
        int healMyselfBy = damageDealt * VAMPIRISM / percents;
        setHealth(getHealth() + healMyselfBy);
    }
}
