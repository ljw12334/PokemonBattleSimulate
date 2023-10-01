package trainer.ai;

import pokemon.MoveList;
import pokemon.Pokemon;
import trainer.Trainer;

import java.util.Random;

public class BattleAi {
    public enum Behavior { BATTLE, POKEMON, BAG, RUN }
    Trainer trainer;

    public int selectMove(Pokemon pokemon) {
        int randomMove;
        while (true) {
            randomMove =  (int) (Math.random() * 4);
            if (pokemon.getPp()[randomMove] != 0) break;
        }
        return randomMove;
    }

    public Behavior whatToDo() {
        Behavior nextBehavior = Behavior.BATTLE;
        return nextBehavior;
    }
}
