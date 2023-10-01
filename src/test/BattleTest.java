package test;

import main.Battle;
import pokemon.MoveList;
import pokemon.Pokemon;
import trainer.Player;
import trainer.Wild;
import pokemon.Pokemon.Stat;

import java.util.Scanner;

public class BattleTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Battle battle = new Battle(new Player(), new Wild());

        Pokemon myPokemon = battle.getMyPokemon();
        Pokemon enemyPokemon = battle.getEnemyPokemon();

        enemyPokemon.setCurrentHp(enemyPokemon.getStats()[Stat.HP.getID()]);

        // 포켓몬 스탯 초기화
        for (int i = 0; i < battle.getPlayer().getPokemons().length; i++) {
            battle.getPlayer().getPokemons()[i].setCurrentHp(battle.getPlayer().getPokemons()[i].getStats()[Stat.HP.getID()]);
            battle.getPlayer().getPokemons()[i].setBattleStats(
                    battle.getPlayer().getPokemons()[i].getStats()[Stat.ATTACK.getID()],
                    battle.getPlayer().getPokemons()[i].getStats()[Stat.DEFENSE.getID()],
                    battle.getPlayer().getPokemons()[i].getStats()[Stat.SP_ATTACK.getID()],
                    battle.getPlayer().getPokemons()[i].getStats()[Stat.SP_DEFENSE.getID()],
                    battle.getPlayer().getPokemons()[i].getStats()[Stat.SPEED.getID()]
            );
        }

        System.out.println("앗! 야생 " + enemyPokemon.getName() + "이(가) 나타났다!");
        System.out.println("가랏! " + myPokemon.getName() + "!");

        int runTryCount = 0;
        int myHpBar, enemyHpBar;

        int select;
        while (true) {
            // 스탯에 랭크 업다운 반영
            myPokemon.setBattleStats(
                    battle.rankCaculate(battle.getMyRank()[0], myPokemon.getStats()[Stat.ATTACK.getID()]),
                    battle.rankCaculate(battle.getMyRank()[1], myPokemon.getStats()[Stat.DEFENSE.getID()]),
                    battle.rankCaculate(battle.getMyRank()[2], myPokemon.getStats()[Stat.SP_ATTACK.getID()]),
                    battle.rankCaculate(battle.getMyRank()[3], myPokemon.getStats()[Stat.SP_DEFENSE.getID()]),
                    battle.rankCaculate(battle.getMyRank()[4], myPokemon.getStats()[Stat.SPEED.getID()])
            );
            enemyPokemon.setBattleStats(
                    battle.rankCaculate(battle.getEnemyRank()[0], enemyPokemon.getStats()[Stat.ATTACK.getID()]),
                    battle.rankCaculate(battle.getEnemyRank()[1], enemyPokemon.getStats()[Stat.DEFENSE.getID()]),
                    battle.rankCaculate(battle.getEnemyRank()[2], enemyPokemon.getStats()[Stat.SP_ATTACK.getID()]),
                    battle.rankCaculate(battle.getEnemyRank()[3], enemyPokemon.getStats()[Stat.SP_DEFENSE.getID()]),
                    battle.rankCaculate(battle.getEnemyRank()[4], enemyPokemon.getStats()[Stat.SPEED.getID()])
            );
            System.out.println("\n");

            // 적 포켓몬 정보 출력
            System.out.print("　　　　　　　　　　　　　　　");
            System.out.println(enemyPokemon.getName() + " " + enemyPokemon.getGender().getSYMBOL() + " Lv." + enemyPokemon.getLevel());
            System.out.print("　　　　　　　　　　　　　　　");
            System.out.print("[HP][");

            enemyHpBar = (int) ((float) enemyPokemon.getBattleStats()[Stat.HP.getID()] / (float) enemyPokemon.getStats()[Stat.HP.getID()] * 20);
            if (enemyHpBar == 0 && enemyPokemon.getBattleStats()[Stat.HP.getID()] > 0) enemyHpBar = 1;

            for (int i = 0; i < 20; i++) {
                if (i + 1 <= enemyHpBar) System.out.print("=");
                else System.out.print(" ");
            }
            System.out.println("]");
            System.out.print("　　　　　　　　　　　　　　　");
            System.out.println(enemyPokemon.getBattleStats()[Stat.HP.getID()] +  " / " + enemyPokemon.getStats()[Stat.HP.getID()]);

            // 내 포켓몬 정보 출력
            System.out.println(myPokemon.getName() + " " + myPokemon.getGender().getSYMBOL() + " Lv." + myPokemon.getLevel());
            System.out.print("[HP][");

            myHpBar = (int) ((float) myPokemon.getBattleStats()[Stat.HP.getID()] / (float) myPokemon.getStats()[Stat.HP.getID()] * 20);
            for (int i = 0; i < 20; i++) {
                if (i <= myHpBar) System.out.print("=");
                else System.out.print(" ");
            }
            System.out.println("]");
            System.out.println(myPokemon.getBattleStats()[Stat.HP.getID()] +  " / " + myPokemon.getStats()[Stat.HP.getID()] + "\n");

            // 시작
            System.out.print(myPokemon.getName() + "은(는) 무엇을 할까?" + "\n1)싸운다    2)포켓몬    3)가방    4)도망간다 : ");
            select = sc.nextInt();

            if(select == 1) {
                // 싸운다
                while (true) {
                    System.out.println();
                    for (int i = 0; i < myPokemon.getMoves().length; i++) {
                        System.out.print((i + 1) + ")" + myPokemon.getMoves()[i].getName() + "    ");
                    }
                    System.out.print((myPokemon.getMoves().length + 1) + ")돌아간다 : ");
                    select = sc.nextInt();

                    // 기술 선택
                    MoveList sMove;
                    if (select == 1) {
                        sMove = myPokemon.getMoves()[0];
                    } else if (select == 2) {
                        if (myPokemon.getMoves().length < 2) break;
                        sMove = myPokemon.getMoves()[1];
                    } else if (select == 3) {
                        if (myPokemon.getMoves().length < 3) break;
                        sMove = myPokemon.getMoves()[2];
                    } else if (select == 4) {
                        if (myPokemon.getMoves().length < 4) break;
                        sMove = myPokemon.getMoves()[3];
                    } else {
                        break;
                    }

                    // (데미지 = (((((((레벨 × 2 ÷ 5) + 2) × 위력 × (특수)공격 ÷ 50) ÷ (특수)방어) × Mod1) + 2) × [[급소]] ×
                    //           Mod2 ×  랜덤수 ÷ 100) × 자속보정 × 타입상성1 × 타입상성2 × Mod3)

                    int damage;

                    int level = myPokemon.getLevel();
                    int power = sMove.getPower();
                    int attack;
                    int defense;
                    float stab = battle.isSameType(sMove.getType(), myPokemon);
                    float type1 = battle.typeCaculate(sMove.getType(), enemyPokemon.getType1());
                    float type2 = battle.typeCaculate(sMove.getType(), enemyPokemon.getType2());
                    float mod1 = battle.mod1();
                    float mod2 = battle.mod2();
                    float mod3 = battle.mod3();
                    float critical = battle.isCritialHit();
                    int randomInt = (int) ((Math.random() * 38 + 217) * 100) / 255;

                    if (sMove.getKind() == MoveList.Kind.PHYSICAL) {
                        attack = myPokemon.getBattleStats()[Stat.ATTACK.getID()];
                        defense = enemyPokemon.getBattleStats()[Stat.DEFENSE.getID()];

                        damage = (int) ((int) (((int) ((((level * 2 / 5) + 2) * power * attack / 50) / defense * mod1) + 2) * critical * mod2 * randomInt / 100) * stab * type1 * type2 * mod3);

                    } else if (sMove.getKind() == MoveList.Kind.SPECIAL) {
                        attack = myPokemon.getBattleStats()[Stat.SP_ATTACK.getID()];
                        defense = enemyPokemon.getBattleStats()[Stat.SP_DEFENSE.getID()];

                        damage = (int) ((int) (((int) ((((level * 2 / 5) + 2) * power * attack / 50) / defense * mod1) + 2) * critical * mod2 * randomInt / 100) * stab * type1 * type2 * mod3);

                    } else if (sMove.getKind() == MoveList.Kind.STATUS) {
                        break;
                    } else {
                        System.out.println("기술의 유형이 올바르지 않습니다!");
                        break;
                    }
                    System.out.println(myPokemon.getName() + "의 " + sMove.getName() + "!");
                    if (type1 * type2 == 0) {
                        System.out.println("효과가 없는 것 같다...");
                    } else if (type1 * type2 > 1) {
                        if (damage <= 0) damage = 1;
                        System.out.println("효과가 굉장했다!");
                    } else if (type1 * type2 < 1) {
                        if (damage <= 0) damage = 1;
                        System.out.println("효과가 별로인 것 같다...");
                    }

                    int newHp = enemyPokemon.getBattleStats()[Stat.HP.getID()] - damage;
                    if (newHp < 0) newHp = 0;
                    enemyPokemon.setCurrentHp(newHp);

                    System.out.println("damage : " + damage);
                    break;
                }
            }

            // 포켓몬
            else if (select == 2) {
                while (true) {
                    System.out.println();
                    for (int i = 0; i < battle.getPlayer().getPokemons().length; i++) {
                        System.out.print((i + 1) + ")" + battle.getPlayer().getPokemons()[i].getName() + "    ");
                    }
                    System.out.print((battle.getPlayer().getPokemons().length + 1) + ")돌아간다 : ");
                    select = sc.nextInt();

                    // 포켓몬 선택
                    Pokemon changePokemon;
                    boolean isChanged = false;
                    if (select == 1) {
                        // 교대 포켓몬 체크
                        changePokemon = battle.getPlayer().getPokemons()[0];
                        if (changePokemon == myPokemon) {
                            System.out.println("이미 배틀에 나가 있습니다!");
                        } else {
                            isChanged = true;
                        }
                    } else if (select == 2) {
                        // 길이 체크
                        if (battle.getPlayer().getPokemons().length < 2) break;

                        changePokemon = battle.getPlayer().getPokemons()[1];
                        if (changePokemon == myPokemon) {
                            System.out.println("이미 배틀에 나가 있습니다!");
                        } else {
                            isChanged = true;
                        }
                    } else if (select == 3) {
                        if (battle.getPlayer().getPokemons().length < 3) break;

                        changePokemon = battle.getPlayer().getPokemons()[2];
                        if (changePokemon == myPokemon) {
                            System.out.println("이미 배틀에 나가 있습니다!");
                        } else {
                            isChanged = true;
                        }
                    } else if (select == 4) {
                        if (battle.getPlayer().getPokemons().length < 4) break;

                        changePokemon = battle.getPlayer().getPokemons()[3];
                        if (changePokemon == myPokemon) {
                            System.out.println("이미 배틀에 나가 있습니다!");
                        } else {
                            isChanged = true;
                        }
                    } else if (select == 5) {
                        if (battle.getPlayer().getPokemons().length < 5) break;

                        changePokemon = battle.getPlayer().getPokemons()[4];
                        if (changePokemon == myPokemon) {
                            System.out.println("이미 배틀에 나가 있습니다!");
                        } else {
                            isChanged = true;
                        }
                    } else if (select == 6) {
                        if (battle.getPlayer().getPokemons().length < 6) break;

                        changePokemon = battle.getPlayer().getPokemons()[5];
                        if (changePokemon == myPokemon) {
                            System.out.println("이미 배틀에 나가 있습니다!");
                        } else {
                            isChanged = true;
                        }
                    } else {
                        break;
                    }

                    if (isChanged) {
                        System.out.println(myPokemon.getName() + "! 돌아와!");

                        myPokemon = changePokemon;
                        battle.resetMyRank();

                        System.out.println("가랏! " + myPokemon.getName() + "!");
                        break;
                    }
                }
            }

            // 가방
            else if (select == 3) {

            }

            // 도망친다
            else {
                // 도망 확률 계산
                int runRate, speedCalc;

                // 내 포켓몬 스피드 / 야생 포켓몬 스피드 (소수점 버림)
                if (enemyPokemon.getStats()[Stat.SPEED.getID()] == 0) {
                    // 적 스피드가 0일 시 계산값 1로 고정
                    speedCalc = 1;
                } else {
                    speedCalc = myPokemon.getStats()[Stat.SPEED.getID()] / enemyPokemon.getStats()[Stat.SPEED.getID()];
                }

                runRate = 128 * speedCalc + 30 * runTryCount;

                int temp = (int) (Math.random() * 256);
                System.out.print(runRate + " > " + temp + " ? ");

                if (runRate % 256 > temp) {
                    System.out.println("true!");
                    System.out.println("무사히 도망쳤다!");
                    break;
                } else {
                    System.out.println("false!");
                    System.out.println("도망칠 수 없다!");
                    runTryCount++;
                }
            }
        }
    }
}
