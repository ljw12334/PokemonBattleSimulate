package test;

import main.Battle;
import trainer.Cynthia;
import trainer.Player;
import trainer.Wild;

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



    }
}
