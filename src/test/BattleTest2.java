package test;

import main.Battle;
import main.ConsoleTextColor;
import pokemon.MoveList;
import pokemon.Pokemon;
import trainer.Cynthia;
import trainer.Player;
import trainer.Wild;
import trainer.ai.BattleAi;

import java.util.Scanner;

public class BattleTest2 {
    public static void main(String[] args) {
        // 1. 각 트레이너가 이번 턴의 행동을 고른다. (행동의 우선도 : 도망 > 교체 = 가방 > 배틀)
        //      -야생이 아닌 트레이너와의 배틀에서는 도망칠 수 없음.
        //
        // 2. 각 행동에서의 세부 선택을 한다. (배틀의 경우 기술 고르기, 교체의 경우 포켓몬 고르기, 가방의 경우 도구 선택 등)
        //
        // 3. 각 선택의 우선도에 따라 선공 / 후공을 정한다.
        //      - 1. 행동의 우선도를 계산한다.
        //      - 2. (배틀) 같을 경우, 기술의 우선도를 계산한다.
        //      - 3. 같을 경우, 포켓몬의 스피드 실능치를 계산한다.
        //      - 4. 여전히 같다면, 랜덤으로 선공 / 후공을 정한다.
        //
        // 4. 각 행동과 세부 행동을 순서대로 처리한다.
        //
        // 5. 배틀 중 어느 한쪽이 쓰러졌고, 파티에 싸울 수 있는 포켓몬이 남은 경우, 해당 턴의 모든 처리를 완료한 후 교체한다. (턴 소모 X)

        Scanner sc = new Scanner(System.in);

        Battle b = new Battle(new Player(), new Wild());

        int select;
        int myMoveIndex = -1;
        int enemyMoveIndex = -1;
        while (true) {
            b.drawHpBar();
            select = sc.nextInt();

            if (select == 1) {
                myMoveIndex = b.selectMove(sc);

                // 돌아간다를 고른 경우
                if (myMoveIndex == 5) continue;

                b.setMyNextBehavior(BattleAi.Behavior.BATTLE);

            } else if (select == 2) {
                b.setMyNextBehavior(BattleAi.Behavior.POKEMON);

            } else if (select == 3) {
                b.setMyNextBehavior(BattleAi.Behavior.BAG);

            } else {
                b.setMyNextBehavior(BattleAi.Behavior.RUN);

            }

            b.setEnemyNextBehavior(b.getEnemy().getAi().whatToDo());
            if (b.getEnemyNextBehavior() == BattleAi.Behavior.BATTLE) {
                enemyMoveIndex = b.getEnemy().getAi().selectMove(b.getEnemyPokemon());
            }

            // 선턴
            if (b.isFasterThanEnemy(myMoveIndex, enemyMoveIndex)) {

                // 내 행동
                if (b.getMyNextBehavior() == BattleAi.Behavior.BATTLE) { // 내가 배틀을 골라서 선턴을 가지는 경우는 상대도 배틀을 골랐을때뿐임
                    b.attack(b.getMyPokemon(), b.getEnemyPokemon(), myMoveIndex);

                } else if (b.getMyNextBehavior() == BattleAi.Behavior.POKEMON) {

                } else if (b.getMyNextBehavior() == BattleAi.Behavior.BAG) {

                } else if (b.getMyNextBehavior() == BattleAi.Behavior.RUN) {
                    if (b.isRun()) {
                        System.out.println("무사히 도망쳤다!");
                        break;
                    } else {
                        System.out.println("도망칠 수 없었다!");
                    }
                } else {
                    System.out.println(ConsoleTextColor.FONT_RED + "myNextBehavior: 잘못된 행동 값!" + ConsoleTextColor.RESET);
                    break;
                }

                // 상대 행동
                if (b.getEnemyNextBehavior() == BattleAi.Behavior.BATTLE) {
                    b.attack(b.getEnemyPokemon(), b.getMyPokemon(), enemyMoveIndex);

                } else if (b.getEnemyNextBehavior() == BattleAi.Behavior.POKEMON) {

                } else if (b.getEnemyNextBehavior() == BattleAi.Behavior.BAG) {

                } else if (b.getEnemyNextBehavior() == BattleAi.Behavior.RUN) {

                } else {
                    System.out.println(ConsoleTextColor.FONT_RED + "enemyNextBehavior: 잘못된 행동 값!" + ConsoleTextColor.RESET);
                    break;
                }
            }

            // 후턴
            else {
                // 상대 행동
                if (b.getEnemyNextBehavior() == BattleAi.Behavior.BATTLE) {
                    b.attack(b.getEnemyPokemon(), b.getMyPokemon(), enemyMoveIndex);

                } else if (b.getEnemyNextBehavior() == BattleAi.Behavior.POKEMON) {

                } else if (b.getEnemyNextBehavior() == BattleAi.Behavior.BAG) {

                } else if (b.getEnemyNextBehavior() == BattleAi.Behavior.RUN) {

                } else {
                    System.out.println(ConsoleTextColor.FONT_RED + "enemyNextBehavior: 잘못된 행동 값!" + ConsoleTextColor.RESET);
                    break;
                }

                // 내 행동
                if (b.getMyNextBehavior() == BattleAi.Behavior.BATTLE) {
                    b.attack(b.getMyPokemon(), b.getEnemyPokemon(), myMoveIndex);

                } else if (b.getMyNextBehavior() == BattleAi.Behavior.POKEMON) {

                } else if (b.getMyNextBehavior() == BattleAi.Behavior.BAG) {

                } else if (b.getMyNextBehavior() == BattleAi.Behavior.RUN) {

                    // 도망을 골랐는데 후턴을 가질 순 없음
                    System.out.println(ConsoleTextColor.FONT_RED + "경고: 올바르지 않은 상황" + ConsoleTextColor.RESET);

                    if (b.isRun()) {
                        System.out.println("무사히 도망쳤다!");
                        break;
                    } else {
                        System.out.println("도망칠 수 없었다!");
                    }
                } else {
                    System.out.println(ConsoleTextColor.FONT_RED + "myNextBehavior: 잘못된 행동 값!" + ConsoleTextColor.RESET);
                    break;
                }
            }

        }
    }
}
