package org.example;

import org.example.game.*;


public class Main {
    public static void main(String[] args) {
        var army1 = new Army().addUnits(new Seducer()::clone, 1).addUnits(new Warrior()::clone, 1);
        var army2 = new Army().addUnits(new Warrior()::clone, 1);

        var res = Battle.fight(army1, army2);

        System.out.println(res);
    }
}