package test;

import main.Battle;
import pokemon.MoveList;
import pokemon.Pokemon;
import trainer.Cynthia;
import trainer.Player;
import trainer.Wild;
import pokemon.Pokemon.Stat;
import trainer.ai.BattleAi;

import java.util.Scanner;

public class BattleTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Battle battle = new Battle(new Player(), new Wild());

        Pokemon myPokemon = battle.getMyPokemon();
        Pokemon enemyPokemon = battle.getEnemyPokemon();


//        myPokemon.setPp(0, 0);
//        myPokemon.setPp(0, 1);
//        myPokemon.setPp(0, 2);
//        myPokemon.setPp(1, 3);
//
//        enemyPokemon.setPp(0, 0);
//        enemyPokemon.setPp(0, 1);
//        enemyPokemon.setPp(0, 2);
//        enemyPokemon.setPp(1, 3);

        // 포켓몬 스탯 초기화

        System.out.println("앗! 야생 " + enemyPokemon.getName() + "이(가) 나타났다!");
        System.out.println("가랏! " + myPokemon.getName() + "!");

        int myHpBar, enemyHpBar;

        int select;
        while (true) {
            // 스탯에 랭크 업다운 반영
            battle.turnCounter();
            System.out.print("\n");

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
                if (i + 1 <= myHpBar) System.out.print("=");
                else System.out.print(" ");
            }
            System.out.println("]");
            System.out.println(myPokemon.getBattleStats()[Stat.HP.getID()] +  " / " + myPokemon.getStats()[Stat.HP.getID()] + "\n");

            // 시작
            System.out.print(myPokemon.getName() + "은(는) 무엇을 할까?" + "\n1)싸운다    2)포켓몬    3)가방    4)도망간다 : ");
            select = sc.nextInt();


            // 내 턴
            if(select == 1) {
                // 싸운다
                while (true) {
                    boolean isSelected = true;
                    MoveList sMove = MoveList.STRUGGLE;

                    if (!myPokemon.isNoPp()) {
                        isSelected = false;
                        while (true) {
                            System.out.println();
                            for (int i = 0; i < myPokemon.getMoves().length; i++) {
                                System.out.print((i + 1) + ")" + myPokemon.getMoves()[i].getName() +
                                        " [pp " + myPokemon.getPp()[i] + " / " + myPokemon.getMoves()[i].getPp() + "]    ");
                            }
                            System.out.print((myPokemon.getMoves().length + 1) + ")돌아간다 : ");
                            select = sc.nextInt();

                            // 기술 선택
                            if (select == 1) {
                                if (myPokemon.getPp()[0] <= 0) {
                                    System.out.println("pp가 부족해 사용할 수 없다!\n");
                                } else {
                                    sMove = myPokemon.getMoves()[0];
                                    isSelected = true;
                                }
                            } else if (select == 2) {
                                if (myPokemon.getMoves().length < 2) break;
                                if (myPokemon.getPp()[1] <= 0) {
                                    System.out.println("pp가 부족해 사용할 수 없다!\n");
                                } else {
                                    sMove = myPokemon.getMoves()[1];
                                    isSelected = true;
                                }
                            } else if (select == 3) {
                                if (myPokemon.getMoves().length < 3) break;
                                if (myPokemon.getPp()[2] <= 0) {
                                    System.out.println("pp가 부족해 사용할 수 없다!\n");
                                } else {
                                    sMove = myPokemon.getMoves()[2];
                                    isSelected = true;
                                }
                            } else if (select == 4) {
                                if (myPokemon.getMoves().length < 4) break;
                                if (myPokemon.getPp()[3] <= 0) {
                                    System.out.println("pp가 부족해 사용할 수 없다!\n");
                                } else {
                                    sMove = myPokemon.getMoves()[3];
                                    isSelected = true;
                                }
                            } else {
                                break;
                            }

                            if (isSelected) break;
                        }
                    }

                    if (!isSelected) break;

                    if (sMove != MoveList.STRUGGLE)
                        myPokemon.setPp(myPokemon.getPp()[select - 1] - 1, select - 1);

                    // (데미지 = (((((((레벨 × 2 ÷ 5) + 2) × 위력 × (특수)공격 ÷ 50) ÷ (특수)방어) × Mod1) + 2) × [[급소]] ×
                    //           Mod2 ×  랜덤수 ÷ 100) × 자속보정 × 타입상성1 × 타입상성2 × Mod3)

                    int damage = 0;
                    float type1 = battle.typeCaculate(sMove.getType(), enemyPokemon.getType1());
                    float type2 = battle.typeCaculate(sMove.getType(), enemyPokemon.getType2());
                    float critical = battle.isCriticalHit();

                    int accuraryRate = 100 - (int) (Math.random() * 100 + 1);

                    if (sMove.getAccurary() >= accuraryRate || sMove.getAccurary() == -1) {
                        if (sMove.getKind() == MoveList.Kind.PHYSICAL) {
                            damage = battle.damageCaculate(myPokemon, enemyPokemon, sMove, critical);
                        } else if (sMove.getKind() == MoveList.Kind.SPECIAL) {
                            damage = battle.damageCaculate(myPokemon, enemyPokemon, sMove, critical);
                        } else if (sMove.getKind() == MoveList.Kind.STATUS) {

                        } else {
                            System.out.println("기술의 유형이 올바르지 않습니다!");
                            break;
                        }
                        System.out.println(myPokemon.getName() + "의 " + sMove.getName() + "!");
                        if (type1 * type2 == 0) {
                            System.out.println("효과가 없는 것 같다...");
                        } else if (type1 * type2 > 1 && sMove.getKind() != MoveList.Kind.STATUS) {
                            System.out.println("효과가 굉장했다!");
                        } else if (type1 * type2 < 1 && sMove.getKind() != MoveList.Kind.STATUS) {
                            System.out.println("효과가 별로인 것 같다...");
                        }
                        if (critical > 1 && type1 * type2 != 0 && sMove.getKind() != MoveList.Kind.STATUS) {
                            System.out.println("급소에 맞았다!");
                        }

                        int newHp = enemyPokemon.getBattleStats()[Stat.HP.getID()] - damage;
                        if (newHp < 0) newHp = 0;
                        enemyPokemon.setCurrentHp(newHp);

                        System.out.println("damage : " + damage);
                        break;
                    } else {
                        System.out.println(myPokemon.getName() + "의 " + sMove.getName() + "!");
                        System.out.println("그러나 " + myPokemon.getName() + "의 공격은 빗나갔다!");
                        break;
                    }


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

                        battle.setMyPokemon(changePokemon);
                        myPokemon = battle.getMyPokemon();
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
                int runRate, mySpeed, enemySpeed;

                mySpeed = myPokemon.getBattleStats()[Stat.SPEED.getID()];
                if (enemyPokemon.getBattleStats()[Stat.SPEED.getID()] == 0) {
                    // 적 스피드가 0일 시 계산값 1로 고정
                    enemySpeed = 1;
                } else {
                    enemySpeed = enemyPokemon.getBattleStats()[Stat.SPEED.getID()];
                }

                runRate = 128 * mySpeed / enemySpeed + 30 * battle.getRunTryCount();

                int temp = (int) (Math.random() * 256);
                System.out.print(runRate % 256 + " > " + temp + " ? ");

                if (runRate % 256 > temp) {
                    System.out.println("true!");
                    System.out.println("무사히 도망쳤다!");
                    break;
                } else {
                    System.out.println("false!");
                    System.out.println("도망칠 수 없었다!");
                    battle.setRunTryCount(battle.getRunTryCount() + 1);
                }
            }

            // 상대의 턴
            BattleAi.Behavior nextBehavior =  battle.getEnemy().getAi().whatToDo();
            MoveList sMove = MoveList.STRUGGLE;

            if (nextBehavior == BattleAi.Behavior.BATTLE) {
                if (!enemyPokemon.isNoPp()) {
                    int enemySelect = battle.getEnemy().getAi().selectMove(enemyPokemon);
                    sMove =  enemyPokemon.getMoves()[enemySelect];

                    enemyPokemon.setPp(enemyPokemon.getPp()[enemySelect] - 1, enemySelect);
                }

                int damage = 0;
                float type1 = battle.typeCaculate(sMove.getType(), myPokemon.getType1());
                float type2 = battle.typeCaculate(sMove.getType(), myPokemon.getType2());
                float critical = battle.isCriticalHit();

                int accuraryRate = 100 - (int) (Math.random() * 100 + 1);

                if (sMove.getAccurary() >= accuraryRate || sMove.getAccurary() == -1) {
                    if (sMove.getKind() == MoveList.Kind.PHYSICAL) {
                        damage = battle.damageCaculate(myPokemon, enemyPokemon, sMove, critical);
                    } else if (sMove.getKind() == MoveList.Kind.SPECIAL) {
                        damage = battle.damageCaculate(myPokemon, enemyPokemon, sMove, critical);
                    } else if (sMove.getKind() == MoveList.Kind.STATUS) {

                    } else {
                        System.out.println("기술의 유형이 올바르지 않습니다!");

                    }
                    System.out.println("야생 " + enemyPokemon.getName() + "의 " + sMove.getName() + "!");
                    if (type1 * type2 == 0) {
                        System.out.println("효과가 없는 것 같다...");
                    } else if (type1 * type2 > 1 && sMove.getKind() != MoveList.Kind.STATUS) {
                        System.out.println("효과가 굉장했다!");
                    } else if (type1 * type2 < 1 && sMove.getKind() != MoveList.Kind.STATUS) {
                        System.out.println("효과가 별로인 것 같다...");
                    }
                    if (critical > 1 && type1 * type2 != 0 && sMove.getKind() != MoveList.Kind.STATUS) {
                        System.out.println("급소에 맞았다!");
                    }

                    int newHp = myPokemon.getBattleStats()[Stat.HP.getID()] - damage;
                    if (newHp < 0) newHp = 0;
                    myPokemon.setCurrentHp(newHp);

                    System.out.println("damage : " + damage);
                } else {
                    System.out.println("야생 " + enemyPokemon.getName() + "의 " + sMove.getName() + "!");
                    System.out.println("그러나 야생 " + enemyPokemon.getName() + "의 공격은 빗나갔다!");
                }
            } else if (nextBehavior == BattleAi.Behavior.POKEMON) {

            } else if (nextBehavior == BattleAi.Behavior.BAG) {

            } else if (nextBehavior == BattleAi.Behavior.RUN) {

            } else {

            }
        }
    }
}
