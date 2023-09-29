package test;

import pokemon.MoveList;
import pokemon.Pokemon;
import pokemon.sample.*;

public class StatsCaculateTest {
    public static void main(String[] args) {
        Pokemon[] pokemon = new Pokemon[]{new CynthiaSpiritomb(), new CynthiaRoserade(), new CynthiaTogekiss(),
                                            new CynthiaLucario(), new CynthiaMilotic(), new CynthiaGarchomp()};


        for (Pokemon i : pokemon) {
            System.out.print(i.name + " ");

            if (i.gender == Pokemon.Gender.MALE)
                System.out.print("♂ ");
            else if (i.gender == Pokemon.Gender.FEMALE)
                System.out.print("♀ ");
            else
                System.out.print("  ");

            System.out.println("Lv." + i.level);
            System.out.println("체력　　 : " + i.stats[0] +
                    "\n공격　　 : " + i.stats[1] +
                    "\n방어　　 : " + i.stats[2] +
                    "\n특수공격 : " + i.stats[3] +
                    "\n특수방어 : " + i.stats[4] +
                    "\n스피드　 : " + i.stats[5]);
            System.out.println("성격　　 : " + i.nature.getName());
            System.out.println("특성　　 : " + i.ability.getName());

            if (i.item == null) {
                System.out.println("지닌물건 : 없음");
            } else {
                System.out.println("지닌물건 : " + i.item.name + "");
            }

            System.out.print("기술배치 : ");

            for (MoveList j : i.moves) {
                System.out.print(j.getName() + "   ");
            }

            System.out.println("\n");
        }

    }
}
