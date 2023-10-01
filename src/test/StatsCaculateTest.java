package test;

import pokemon.MoveList;
import pokemon.Pokemon;
import pokemon.sample.*;
import trainer.Cynthia;
import trainer.Trainer;

public class StatsCaculateTest {
    public static void main(String[] args) {
        Trainer trainer = new Cynthia();


        Pokemon[] pokemon = trainer.getPokemons();


        for (Pokemon i : pokemon) {
            System.out.print(i.getName() + " ");

            if (i.getGender() == Pokemon.Gender.MALE)
                System.out.print("♂ ");
            else if (i.getGender() == Pokemon.Gender.FEMALE)
                System.out.print("♀ ");
            else
                System.out.print("  ");

            System.out.println("Lv." + i.getLevel());
            System.out.println("체력　　 : " + i.getStats()[0] +
                    "\n공격　　 : " + i.getStats()[1] +
                    "\n방어　　 : " + i.getStats()[2] +
                    "\n특수공격 : " + i.getStats()[3] +
                    "\n특수방어 : " + i.getStats()[4] +
                    "\n스피드　 : " + i.getStats()[5]);
            System.out.println("성격　　 : " + i.getNature().getName());
            System.out.println("특성　　 : " + i.getAbility().getName());

            if (i.getItem() == null) {
                System.out.println("지닌물건 : 없음");
            } else {
                System.out.println("지닌물건 : " + i.getItem().name);
            }

            System.out.print("기술배치 : ");

            for (MoveList j : i.getMoves()) {
                System.out.print(j.getName() + "   ");
            }

            System.out.println("\n");
        }

    }
}
