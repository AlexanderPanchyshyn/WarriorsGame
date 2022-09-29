package org.example.game;

public class Defender extends Warrior{
    private static final int STRENGTH = 3;
    private static final int ARMOR = 2;
    public Defender() {
        super(60);
    }
    public int getStrength() {
        return STRENGTH;
    }
    protected int getArmor() {
        return ARMOR;
    }

    @Override
    public void receiveDamage(HasStrength damager){
        int reducedDamage = Math.max(0, damager.getStrength() - getArmor());
        super.receiveDamage(() -> reducedDamage);
    }
}
