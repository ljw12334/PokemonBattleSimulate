package trainer.ai;

import pokemon.MoveList;
import pokemon.Pokemon;
import trainer.Trainer;

import java.util.Random;

public class BattleAi {
    public enum Behavior { BATTLE, POKEMON, BAG, RUN }
    Trainer trainer;
    String aiName;


    public String getAiName() {
        return aiName;
    }


    // 기본적으로 야생 포켓몬의 행동방식을 가지고 있음
    public int selectMove(Pokemon pokemon) {
        int randomMove;
        while (true) {
            randomMove =  (int) (Math.random() * pokemon.getMoves().length);
            if (pokemon.getPp()[randomMove] != 0) break;
        }
        return randomMove;
    }

    public Behavior whatToDo() {
        Behavior nextBehavior = Behavior.BATTLE;
        return nextBehavior;
    }
}
