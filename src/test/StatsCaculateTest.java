package test;

import pokemon.Pokemon;
import pokemon.sample.CynthiaGarchomp;

public class StatsCaculateTest {
    public static void main(String[] args) {
        Pokemon pokemon = new CynthiaGarchomp();


        System.out.print(pokemon.name + " ");

        if (pokemon.gender == Pokemon.Gender.MALE)
            System.out.print("♂ ");
        else if (pokemon.gender == Pokemon.Gender.FEMALE)
            System.out.print("♀ ");
        else
            System.out.print("  ");

        System.out.println("Lv." + pokemon.level);
        System.out.println("체력　　 : " + pokemon.stats[0] +
                "\n공격　　 : " + pokemon.stats[1] +
                "\n방어　　 : " + pokemon.stats[2] +
                "\n특수공격 : " + pokemon.stats[3] +
                "\n특수방어 : " + pokemon.stats[4] +
                "\n스피드　 : " + pokemon.stats[5]);
        System.out.println("성격　　 : " + pokemon.nature.getName());
        System.out.println("특성　　 : " + pokemon.ability.getName());
        System.out.println("지닌물건 : " + pokemon.item.name);

    }
}
