package org.example.game;

public interface KnowsDamageDealt extends HasStrength {
    default int hitAndReportDamage(CanReceiveDamage opponent) {
        int opponentHealthBeforeAttack = opponent.getHealth();
        HasStrength.super.hit(opponent);
        int opponentHealthAfterAttack = opponent.getHealth();
        int damageDealt = opponentHealthBeforeAttack - opponentHealthAfterAttack;
        return damageDealt;
    }
}
