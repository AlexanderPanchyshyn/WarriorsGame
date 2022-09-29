package org.example.game;

public class Battle {
    public static boolean fight(Warrior warrior1, Warrior warrior2) {
        while(warrior1.isAlive() && warrior2.isAlive()) {
            warrior1.hit(warrior2);
            System.out.println(warrior1.getHealth() + ":" + warrior2.getHealth());
            if (!warrior2.isAlive()) { break; }
            warrior2.hit(warrior1);
            System.out.println(warrior1.getHealth() + ":" + warrior2.getHealth());
        }
        return warrior1.isAlive();
    }

    public static boolean fight(Army army1, Army army2){
        var it1 = army1.firstAlive();
        var it2 = army2.firstAlive();
        while (it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }
        return it1.hasNext();
    }

    public static boolean straightFight(Army army1, Army army2) {

        while (true) {
            var it1 = army1.iterator();
            var it2 = army2.iterator();

            if (!it1.hasNext()) {
                return false;
            }
            if (!it2.hasNext()) {
                return true;
            }

            while (it1.hasNext() && it2.hasNext()) {
                fight(it1.next(), it2.next());
            }

            army1.removeDeadBodies();
            army2.removeDeadBodies();
        }
    }
}
