package org.example.game;

import java.util.Optional;

public interface WarriorInArmy extends CanProcessCommand{
    Warrior getWrapped();
    WarriorInArmy getNextBehind();
    Army getArmy();

    default void processCommand(Command command, WarriorInArmy sender) {
        if (getWrapped() instanceof CanProcessCommand wrapped) {
            wrapped.processCommand(command, sender);
        }
//        Optional.ofNullable(getNextBehind())
//                .ifPresent(next -> next.processCommand(command, this));
    }

    public void receiveDamage(HasStrength damager);
}

interface CanProcessCommand {
    void processCommand(Command command, WarriorInArmy sender);
}

interface Command {}

class HealCommand implements Command {}

