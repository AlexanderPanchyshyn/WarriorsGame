package org.example.game;

public class Healer extends Warrior implements CanProcessCommand {
    private static final int STRENGTH = 0;
    private static final int HEALING_VALUE = 2;

    public Healer() {
        super(60);
    }

    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public void hit(CanReceiveDamage opponent) {
        // Nothing
    }

    public void heal(Warrior damagedWarrior) {
        damagedWarrior.setHealth(damagedWarrior.getHealth() + HEALING_VALUE);
    }

    public void processCommand(Command command, WarriorInArmy sender) {
        if (command instanceof HealCommand) {
            heal(sender.getWrapped());
        }
    }
}
