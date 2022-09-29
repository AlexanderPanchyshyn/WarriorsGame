package org.example.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Rookie extends Warrior {
    @Override
    public int getStrength() {
        return 1;
    }
}

class BattleTest {

    @Test
    @DisplayName("Fight. Smoke Test")
    void smokeTest1() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();

        var res1 = Battle.fight(chuck, bruce);
        var res2 = Battle.fight(dave, carl);

        assertTrue(res1);
        assertFalse(res2);
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Fight")
    void checkFightResults(Warrior warrior1, Warrior warrior2, boolean expected) {
        var res = Battle.fight(warrior1, warrior2);
        assertEquals(expected, res);
    }

    static Stream<Arguments> checkFightResults() {
        return Stream.of(
                Arguments.of(new Warrior(),
                        new Knight(),
                        false),
                Arguments.of(new Knight(),
                        new Warrior(),
                        true),
                Arguments.of(new Defender(),
                        new Knight(),
                        false),
                Arguments.of(new Defender(),
                        new Warrior(),
                        true),
                Arguments.of(new Vampire(),
                        new Defender(),
                        false),
                Arguments.of(new Warrior(),
                        new Vampire(),
                        true),
                Arguments.of(new Lancer(),
                        new Vampire(),
                        true)
        );
    }

    @Test
    @DisplayName("Fight. W1vsW2:W1 alive")
    void fight3() {
        var bob = new Warrior();
        var mars = new Warrior();

        Battle.fight(bob, mars);
        var res = bob.isAlive();

        assertTrue(res);
    }

    @Test
    @DisplayName("Fight. K vs W. K alive")
    void fight4() {
        var zeus = new Knight();
        var godkiller = new Warrior();

        Battle.fight(zeus, godkiller);
        var res = zeus.isAlive();

        assertTrue(res);
    }

    @Test
    @DisplayName("Fight. W1 vs W2. W2 dead")
    void fight5() {
        var husband = new Warrior();
        var wife = new Warrior();

        Battle.fight(husband, wife);
        var res = wife.isAlive();

        assertFalse(res);
    }

    @Test
    @DisplayName("Fight. W vs K. K alive")
    void fight6() {
        var dragon = new Warrior();
        var knight = new Knight();

        Battle.fight(dragon, knight);
        var res = knight.isAlive();

        assertTrue(res);
    }

    @Test
    @DisplayName("Fight. (W vs K) vs W")
    void fight7() {
        var unit_1 = new Warrior();
        var unit_2 = new Knight();
        var unit_3 = new Warrior();

        Battle.fight(unit_1, unit_2);
        var res = Battle.fight(unit_2, unit_3);

        assertFalse(res);
    }

    @Test
    @DisplayName("Battle. Smoke Test")
    void smokeTest2() {
        var myArmy = new Army();
        myArmy.addUnits((new Knight())::clone, 3);
        var enemyArmy = new Army();
        enemyArmy.addUnits((new Warrior())::clone, 3);
        var army3 = new Army();
        army3.addUnits((new Warrior())::clone, 20);
        army3.addUnits((new Knight())::clone, 5);
        var army4 = new Army();
        army4.addUnits((new Warrior())::clone, 30);

        var res1 = Battle.fight(myArmy, enemyArmy);
        var res2 = Battle.fight(army3, army4);

        assertTrue(res1);
        assertFalse(res2);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Battle")
    void checkBattleResults(Army army_1, Army army_2, boolean expected) {
        var res = Battle.fight(army_1, army_2);
        assertEquals(expected, res);
    }

    static Stream<Arguments> checkBattleResults() {
        return Stream.of(
                Arguments.of(new Army().addUnits(Warrior::new, 1),
                        new Army().addUnits(Warrior::new, 2),
                        false),
                Arguments.of(new Army().addUnits(Warrior::new, 2),
                        new Army().addUnits(Warrior::new, 3),
                        false),
                Arguments.of(new Army().addUnits(Warrior::new, 5),
                        new Army().addUnits(Warrior::new, 7),
                        false),
                Arguments.of(new Army().addUnits(Warrior::new, 20),
                        new Army().addUnits(Warrior::new, 21),
                        true),
                Arguments.of(new Army().addUnits(Warrior::new, 10),
                        new Army().addUnits(Warrior::new, 11),
                        true),
                Arguments.of(new Army().addUnits(Warrior::new, 11),
                        new Army().addUnits(Warrior::new, 7),
                        true),
                Arguments.of(new Army().addUnits(Warrior::new, 5)
                                .addUnits(Defender::new, 4),
                        new Army().addUnits(Defender::new, 5)
                                .addUnits(Warrior::new, 4),
                        true),
                Arguments.of(new Army().addUnits(Defender::new, 5)
                                .addUnits(Warrior::new, 20),
                        new Army().addUnits(Warrior::new, 21)
                                .addUnits(Defender::new, 4),
                        true),
                Arguments.of(new Army().addUnits(Warrior::new, 10)
                                .addUnits(Defender::new, 5),
                        new Army().addUnits(Warrior::new, 5)
                                .addUnits(Defender::new, 10),
                        false),
                Arguments.of(new Army().addUnits(Defender::new, 2)
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 1),
                        new Army().addUnits(Warrior::new, 5),
                        false),
                Arguments.of(new Army().addUnits(Defender::new, 5)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Warrior::new, 7),
                        new Army().addUnits(Warrior::new, 6)
                                .addUnits(Defender::new, 6)
                                .addUnits(Vampire::new, 6),
                        false),
                Arguments.of(new Army().addUnits(Defender::new, 2)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4),
                        new Army().addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 3),
                        false),
                Arguments.of(new Army().addUnits(Defender::new, 11)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4),
                        new Army().addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 13),
                        true),
                Arguments.of(new Army().addUnits(Defender::new, 9)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 8),
                        new Army().addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 13),
                        true),
                Arguments.of(new Army().addUnits(Lancer::new, 5)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army().addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 5),
                        false),
                Arguments.of(new Army().addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army().addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        true),
                Arguments.of(new Army().addUnits(Warrior::new, 2),
                        new Army().addUnits(Lancer::new, 1)
                                .addUnits(Warrior::new, 1),
                        false),
                Arguments.of(new Army().addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Defender::new, 2),
                        new Army().addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        true),
                Arguments.of(new Army().addUnits(Lancer::new, 1)
                                .addUnits(Warrior::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Knight::new, 2),
                        new Army().addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        false)
        );
    }

    @Test
    @DisplayName("Battle with Defenders. Smoke Test")
    void smokeTest3() {
        var myArmy = new Army().addUnits(Defender::new, 1);
        var enemyArmy = new Army().addUnits(Warrior::new, 2);
        var army3 = new Army().addUnits(Warrior::new, 1)
                .addUnits(Defender::new, 1);
        var army4 = new Army().addUnits(Warrior::new, 2);

        var res1 = Battle.fight(myArmy, enemyArmy);
        var res2 = Battle.fight(army3, army4);

        assertFalse(res1);
        assertTrue(res2);
    }

    @Test
    @DisplayName("Fight. D vs R. No damage")
    void fight8() {
        var unit_1 = new Defender();
        var unit_2 = new Rookie();
        int expected = 60;

        Battle.fight(unit_1, unit_2);
        var res = unit_1.getHealth();

        assertEquals(res, expected);
    }

    @Test
    @DisplayName("Fight. (D vs R) vs W")
    void fight9() {
        var unit_1 = new Defender();
        var unit_2 = new Rookie();
        var unit_3 = new Warrior();

        Battle.fight(unit_1, unit_2);
        var res = Battle.fight(unit_1, unit_3);

        assertTrue(res);
    }

    @Test
    @DisplayName("Battle with Vampires. Smoke Test")
    void smokeTest4() {
        var myArmy = new Army().addUnits(Defender::new, 2)
                .addUnits(Vampire::new, 2)
                .addUnits(Warrior::new, 1);
        var enemyArmy = new Army().addUnits(Warrior::new, 2)
                .addUnits(Defender::new, 2)
                .addUnits(Vampire::new, 3);
        var army3 = new Army().addUnits(Warrior::new, 1)
                .addUnits(Defender::new, 4);
        var army4 = new Army().addUnits(Vampire::new, 3)
                .addUnits(Warrior::new, 2);

        var res1 = Battle.fight(myArmy, enemyArmy);
        var res2 = Battle.fight(army3, army4);

        assertFalse(res1);
        assertTrue(res2);
    }

    @Test
    @DisplayName("Battle with Vampires. Smoke Test")
    void smokeTest5() {
        var myArmy = new Army().addUnits(Defender::new, 2)
                .addUnits(Vampire::new, 2)
                .addUnits(Lancer::new, 4)
                .addUnits(Warrior::new, 1);
        var enemyArmy = new Army().addUnits(Warrior::new, 2)
                .addUnits(Lancer::new, 2)
                .addUnits(Defender::new, 2)
                .addUnits(Vampire::new, 3);
        var army3 = new Army().addUnits(Warrior::new, 1)
                .addUnits(Lancer::new, 1)
                .addUnits(Defender::new, 2);
        var army4 = new Army().addUnits(Vampire::new, 3)
                .addUnits(Warrior::new, 1)
                .addUnits(Lancer::new, 2);

        var res1 = Battle.fight(myArmy, enemyArmy);
        var res2 = Battle.fight(army3, army4);

        assertTrue(res1);
        assertFalse(res2);
    }

    @Test
    @DisplayName("Battle with Healers. Smoke Test")
    void smokeTest6() {
        var myArmy = new Army().addUnits(Defender::new, 2)
                .addUnits(Healer::new, 1)
                .addUnits(Vampire::new, 2)
                .addUnits(Lancer::new, 2)
                .addUnits(Healer::new, 1)
                .addUnits(Warrior::new, 1);
        var enemyArmy = new Army().addUnits(Warrior::new, 2)
                .addUnits(Lancer::new, 4)
                .addUnits(Healer::new, 1)
                .addUnits(Defender::new, 2)
                .addUnits(Vampire::new, 3)
                .addUnits(Healer::new, 1);
        var army3 = new Army().addUnits(Warrior::new, 1)
                .addUnits(Lancer::new, 1)
                .addUnits(Healer::new, 1)
                .addUnits(Defender::new, 2);
        var army4 = new Army().addUnits(Vampire::new, 3)
                .addUnits(Warrior::new, 1)
                .addUnits(Healer::new, 1)
                .addUnits(Lancer::new, 2);

        var res1 = Battle.fight(myArmy, enemyArmy);
        var res2 = Battle.fight(army3, army4);

        assertFalse(res1);
        assertTrue(res2);
    }

    @Test
    @DisplayName("Fight. L+H ws V")
    void fight10() {
        var unit_1 = new Lancer();
        var unit_2 = new Vampire();
        var unit_3 = new Healer();
        int health1 = 14;
        int health2 = 16;

        var res1 = Battle.fight(unit_1, unit_2);
        var res2 = unit_1.getHealth();
        unit_3.heal(unit_1);
        var res3 = unit_1.getHealth();

        assertTrue(res1);
        assertEquals(health1,res2);
        assertEquals(health2,res3);
    }
}
