package main;

import pokemon.*;
import trainer.Player;
import trainer.Trainer;

import pokemon.Pokemon.Stat;
import trainer.ai.BattleAi;
import trainer.ai.WildAi;

import java.util.Scanner;

public class Battle {
    private Trainer player, enemy;
    private Pokemon myPokemon, enemyPokemon;
    private String kindOfEnemy;

    private int currentTurn = 0;
    private int runTryCount = 0;

    private byte[] myRank = new byte[]{0, 0, 0, 0, 0};
    private byte[] enemyRank = new byte[]{0, 0, 0, 0, 0};

    private BattleAi.Behavior myNextBehavior;
    private BattleAi.Behavior enemyNextBehavior;


    public Battle(Trainer player, Trainer enemy) {
        this.player = player;
        this.enemy = enemy;
        this.myPokemon = player.getPokemons()[0];
        this.enemyPokemon = enemy.getPokemons()[0];

        initBattle();

        battleIntro();
    }

    // getter setter
    public BattleAi.Behavior getMyNextBehavior() { return myNextBehavior; }
    public void setMyNextBehavior(BattleAi.Behavior behavior) { this.myNextBehavior = behavior; }
    public BattleAi.Behavior getEnemyNextBehavior() { return enemyNextBehavior; }
    public void setEnemyNextBehavior(BattleAi.Behavior behavior) { this.enemyNextBehavior = behavior; }

    public int getRunTryCount() { return runTryCount; }
    public void setRunTryCount(int runTryCount) { this.runTryCount = runTryCount; }

    public Trainer getPlayer() { return player; }
    public Trainer getEnemy() { return enemy; }

    public Pokemon getMyPokemon() { return myPokemon; }
    public void setMyPokemon(Pokemon myPokemon) { this.myPokemon = myPokemon; }

    public Pokemon getEnemyPokemon() { return enemyPokemon; }
    public void setEnemyPokemon(Pokemon enemyPokemon) { this.enemyPokemon = enemyPokemon; }

    public byte[] getMyRank() { return myRank; }
    public void setMyRank(Stat stat, int rankUpDown) {
        byte temp = (byte) (this.myRank[stat.getID()] + rankUpDown);

        if (temp > 6) { temp = 6; }
        else if (temp < -6) { temp = -6; }

        this.myRank[stat.getID()] = temp;
    }

    public byte[] getEnemyRank() {
        return enemyRank;
    }

    public void setEnemyRank(byte[] enemyRank) {
        this.enemyRank = enemyRank;
    }

    // methods

    public int rankCaculate(byte rank, int stat) {
        float caculatedStat;

        switch (rank) {
            case 1  : caculatedStat = stat / 2 * 3; break;
            case 2  : caculatedStat = stat / 2 * 3; break;
            case 3  : caculatedStat = stat / 2 * 5; break;
            case 4  : caculatedStat = stat * 3;     break;
            case 5  : caculatedStat = stat / 2 * 7; break;
            case 6  : caculatedStat = stat * 4;     break;

            case -1 : caculatedStat = stat / 3 * 2; break;
            case -2 : caculatedStat = stat / 2;     break;
            case -3 : caculatedStat = stat / 5 * 2; break;
            case -4 : caculatedStat = stat / 3;     break;
            case -5 : caculatedStat = stat / 7 * 2; break;
            case -6 : caculatedStat = stat / 4;     break;

            default : caculatedStat = stat;         break;
        }

        return (int) caculatedStat;
    }
    public void resetMyRank() {
        myRank = new byte[]{0, 0, 0, 0, 0};
    }
    public void resetEnemyRank() {
        enemyRank = new byte[]{0, 0, 0, 0, 0};
    }

    public float isCriticalHit() {
        if (Math.random() * 24 + 1 >= 24) {
            return 1.5f;
        } else {
            return 1;
        }
    }
    // 상태이상, 벽깔이, 날씨, 특성에 의한 데미지 증감
    public float mod1() {
        return 1;
    }
    // 도구에 의한 데미지 증가
    public float mod2(Pokemon attackPokemon) {
        if (attackPokemon.getItem() == null) return 1;
        if (attackPokemon.getItem().getItemID() == ItemList.LIFE_ORB) return 1.3f;
        return 1;
    }
    // 특성, 도구, 열매에 의한 데미지 증감
    public float mod3(Pokemon attackPokemon, Pokemon targetPokemon, MoveList sMove, float typeCaculate) {
        // (Mod3 = [[하드록]]·[[필터]] × [[달인의띠]] × [[색안경]] × 타입별위력반감[[열매]])
        float result = 1;
        float caTargetAbility = 1;
        float caAttackItem = 1;
        float caAttackAbility = 1;
        float caBerry = 1;
        ItemList tItem = null;
        ItemList aItem = null;

        Ability tAbility = targetPokemon.getAbility();
        Ability aAbility = attackPokemon.getAbility();
        if (targetPokemon.getItem() != null)
            tItem = targetPokemon.getItem().getItemID();
        if (attackPokemon.getItem() != null)
            aItem = attackPokemon.getItem().getItemID();
        Type sMoveType = sMove.getType();

        // 하드록, 필터
        if (tAbility == Ability.SOLID_ROCK || tAbility == Ability.FILTER && typeCaculate > 1) caTargetAbility = 0.75f;
        // 달인의띠
        if (aItem == ItemList.EXPERT_BELT && typeCaculate > 1) caAttackItem = 1.2f;
        // 색안경
        if (aAbility == Ability.TINTED_LENS && typeCaculate < 1) caAttackAbility = 2;
        // 반감열매들
        if (tItem != null) {
            if (((tItem.getBERRY_ID() >= 36 && tItem.getBERRY_ID() <= 51) || tItem.getBERRY_ID() == 65) && typeCaculate > 1) {
                if (tItem.getTYPE() == sMoveType.getNAME()) {
                    caBerry = 0.5f;
                    System.out.println(KorJongsung.isHave(tItem.getNAME(), "이 ", "가 ") + "기술의 위력을 약하게 했다!");
                    targetPokemon.setItem(null);
                }
            }
            if (tItem == ItemList.CHILAN_BERRY && sMoveType == Type.NORMAL) {
                caBerry = 0.5f;
                System.out.println(KorJongsung.isHave(tItem.getNAME(), "이 ", "가 ") + "기술의 위력을 약하게 했다!");
                targetPokemon.setItem(null);
            }
        }

        result = caTargetAbility * caAttackItem * caAttackAbility * caBerry;

        return result;
    }
    public float isSameType(Type attackType, Pokemon attackPokemon) {
        if (attackType == attackPokemon.getType1() || attackType == attackPokemon.getType2()) {
            // 기술을 쓰는 포켓몬의 특성이 적응력이면 자속보정치 2.0
            if (attackPokemon.getAbility() == Ability.ADAPTABILITY) return 2;
            else return 1.5f;
        } else {
            return 1;
        }
    }
    public float typeCaculate(Type attackType, Type targetType) {
        // 효과가 굉장함
        if (isContain(attackType.getSUPER_EFFECTIVE(), targetType)) return 2;
        // 효과가 별로임
        else if (isContain(attackType.getNOT_VERY_EFFECTIVE(), targetType)) return 0.5f;
        // 효과가 없음
        else if (isContain(attackType.getNO_EFFECT(), targetType)) return 0;
        // 무상성
        else return 1;
    }
    public boolean isContain(String[] attackType, Type targetType) {
        for (String type : attackType) {
            if (type.equals(targetType.getNAME())) {
                return true;
            }
        }
        return false;
    }

    public int damageCaculate(Pokemon attackPokemon, Pokemon targetPokemon, MoveList sMove, float isCritical) {
        // (데미지 = (((((((레벨 × 2 ÷ 5) + 2) × 위력 × (특수)공격 ÷ 50) ÷ (특수)방어) × Mod1) + 2) × [[급소]] × Mod2 ×  랜덤수 ÷ 100)
        //           × 자속보정 × 타입상성1 × 타입상성2 × Mod3)
        int damage;

        int level = attackPokemon.getLevel();
        int power = sMove.getPower();
        int attack;
        int defense;
        // Same Type Attack Bonus, 자속보정
        float stab = isSameType(sMove.getType(), attackPokemon);
        float type1 = typeCaculate(sMove.getType(), targetPokemon.getType1());
        float type2 = typeCaculate(sMove.getType(), targetPokemon.getType2());
        float mod1 = mod1();
        float mod2 = mod2(attackPokemon);
        float mod3 = mod3(attackPokemon, targetPokemon, sMove, type1 * type2);
        float critical = isCritical;
        int randomInt = (int) ((Math.random() * 38 + 217) * 100) / 255;

        if (sMove.getKind() == MoveList.Kind.PHYSICAL) {
            attack = attackPokemon.getBattleStats()[Stat.ATTACK.getID()];
            defense = targetPokemon.getBattleStats()[Stat.DEFENSE.getID()];

            damage = (int) ((int) (((int) ((((level * 2 / 5) + 2) * power * attack / 50) / defense * mod1) + 2) * critical * mod2 * randomInt / 100) * stab * type1 * type2 * mod3);

        } else if (sMove.getKind() == MoveList.Kind.SPECIAL) {
            attack = attackPokemon.getBattleStats()[Stat.SP_ATTACK.getID()];
            defense = targetPokemon.getBattleStats()[Stat.SP_DEFENSE.getID()];

            damage = (int) ((int) (((int) ((((level * 2 / 5) + 2) * power * attack / 50) / defense * mod1) + 2) * critical * mod2 * randomInt / 100) * stab * type1 * type2 * mod3);

        } else {
            damage = 0;
        }

        if (type1 * type2 != 0 && damage <= 0) damage = 1;
        return damage;
    }

    public void turnCounter() {
        reflectStats();
        this.currentTurn++;
    }
    public void reflectStats() {
        this.myPokemon.setBattleStats(
                this.rankCaculate(this.getMyRank()[0], myPokemon.getStats()[Stat.ATTACK.getID()]),
                this.rankCaculate(this.getMyRank()[1], myPokemon.getStats()[Stat.DEFENSE.getID()]),
                this.rankCaculate(this.getMyRank()[2], myPokemon.getStats()[Stat.SP_ATTACK.getID()]),
                this.rankCaculate(this.getMyRank()[3], myPokemon.getStats()[Stat.SP_DEFENSE.getID()]),
                this.rankCaculate(this.getMyRank()[4], myPokemon.getStats()[Stat.SPEED.getID()])
        );
        this.enemyPokemon.setBattleStats(
                this.rankCaculate(this.getEnemyRank()[0], enemyPokemon.getStats()[Stat.ATTACK.getID()]),
                this.rankCaculate(this.getEnemyRank()[1], enemyPokemon.getStats()[Stat.DEFENSE.getID()]),
                this.rankCaculate(this.getEnemyRank()[2], enemyPokemon.getStats()[Stat.SP_ATTACK.getID()]),
                this.rankCaculate(this.getEnemyRank()[3], enemyPokemon.getStats()[Stat.SP_DEFENSE.getID()]),
                this.rankCaculate(this.getEnemyRank()[4], enemyPokemon.getStats()[Stat.SPEED.getID()])
        );
    }

    public void initTrainerPokemons(Trainer trainer) {
        for (int i = 0; i < trainer.getPokemons().length; i++) {
            if (trainer.getPokemons()[i] == null) break;

            trainer.getPokemons()[i].setCurrentHp(trainer.getPokemons()[i].getStats()[Stat.HP.getID()]);
            trainer.getPokemons()[i].setBattleStats(
                    trainer.getPokemons()[i].getStats()[Stat.ATTACK.getID()],
                    trainer.getPokemons()[i].getStats()[Stat.DEFENSE.getID()],
                    trainer.getPokemons()[i].getStats()[Stat.SP_ATTACK.getID()],
                    trainer.getPokemons()[i].getStats()[Stat.SP_DEFENSE.getID()],
                    trainer.getPokemons()[i].getStats()[Stat.SPEED.getID()]
            );
        }
    }

    public void initBattle() {
        initTrainerPokemons(this.player);
        initTrainerPokemons(this.enemy);

        this.currentTurn = 1;
    }

    public boolean isFasterThanEnemy(int myMoveIndex, int enemyMoveIndex) {
        // 행동의 우선도 : 도망 > 교체 = 가방 > 배틀
        //
        // 각 선택의 우선도에 따라 선공 / 후공을 정한다.
        //
        //      - 1. 행동의 우선도를 계산한다.
        //        - 1.1. (배틀) 같을 경우, 기술의 우선도를 계산한다.
        //
        //      - 2. 같을 경우, 포켓몬의 스피드 실능치를 계산한다.
        //      - 3. 여전히 같다면, 랜덤으로 선공 / 후공을 정한다.

        if (this.myNextBehavior == BattleAi.Behavior.BATTLE) {
            if (this.enemyNextBehavior == BattleAi.Behavior.BATTLE) {
                // 내행동: 배틀 | 상대행동: 배틀
                if (this.myPokemon.getMoves()[myMoveIndex].getPriority() > this.enemyPokemon.getMoves()[enemyMoveIndex].getPriority()) {
                    // 기술 우선도 높음
                    return true;

                } else if (this.myPokemon.getMoves()[myMoveIndex].getPriority() < this.enemyPokemon.getMoves()[enemyMoveIndex].getPriority()) {
                    // 기술 우선도 낮음
                    return false;

                } else {
                    // 기술 우선도 같음
                    if (this.myPokemon.getBattleStats()[Stat.SPEED.getID()] > this.enemyPokemon.getBattleStats()[Stat.SPEED.getID()]) {
                        return true;
                    } else if (this.myPokemon.getBattleStats()[Stat.SPEED.getID()] < this.enemyPokemon.getBattleStats()[Stat.SPEED.getID()]) {
                        return false;
                    } else {
                        int random = (int) (Math.random() * 2);
                        if (random == 0) return true;
                        else return false;
                    }
                }
            } else {
                // 내행동: 배틀 | 상대행동: 교체, 가방
                return false;
            }
        } else if (this.myNextBehavior == BattleAi.Behavior.RUN) {
            // 내행동: 도망 | 상대는 일반적으론 도망치지 않음
            return true;
        } else {
            if (this.enemyNextBehavior == BattleAi.Behavior.BATTLE) {
                // 내행동: 교체, 가방 | 상대행동: 배틀
                return true;
            } else {
                // 내행동: 교체, 가방 | 상대행동: 교체, 가방
                if (this.myPokemon.getBattleStats()[Stat.SPEED.getID()] > this.enemyPokemon.getBattleStats()[Stat.SPEED.getID()]) {
                    return true;
                } else if (this.myPokemon.getBattleStats()[Stat.SPEED.getID()] < this.enemyPokemon.getBattleStats()[Stat.SPEED.getID()]) {
                    return false;
                } else {
                    int random = (int) (Math.random() * 2);
                    if (random == 0) return true;
                    else return false;
                }
            }
        }
    }

    // 배틀 진행 ==================================================================================================
    public void battleIntro() {
        if (this.enemy.getAi().getAiName() == "wild") {
            this.kindOfEnemy = "야생 ";
            System.out.println("앗! " + kindOfEnemy +
                    KorJongsung.isHave(this.enemyPokemon.getName(), "이 ", "가 ")  + "나타났다!");

        } else {
            this.kindOfEnemy = "상대 ";
            System.out.println(this.enemy.getTrainerClass() + " " +
                    KorJongsung.isHave(this.enemy.getTrainerName(), "이 ", "가 ") + "승부를 걸어왔다!");

            System.out.println(this.enemy.getTrainerClass() + " " +
                    KorJongsung.isHave(this.enemy.getTrainerName(), "은 ", "는 ") +
                    KorJongsung.isHave(this.enemyPokemon.getName(), "을 ", "를 ") + "내보냈다!");
        }
        System.out.println("가랏! " + this.myPokemon.getName() + "!");

        this.currentTurn = 1;
    }

    public void drawHpBar() {
        int myHpBar;
        int enemyHpBar;
        int hpBarLength = 30;

        System.out.println(this.enemyPokemon.getName() + " " + this.enemyPokemon.getGender().getSYMBOL() + " Lv." + this.enemyPokemon.getLevel());

        enemyHpBar = (int) ((float) this.enemyPokemon.getBattleStats()[Stat.HP.getID()] / (float) this.enemyPokemon.getStats()[Stat.HP.getID()] * hpBarLength);
        // 체력바 색상 변경 | 절반 이상 : 녹색 | 절반 미만 : 노랑 | 1/4 미만 : 빨강
        if (enemyHpBar >= (hpBarLength / 2))
            System.out.print("[HP][" + ConsoleTextColor.FONT_GREEN);
        else if (enemyHpBar >= (hpBarLength / 4))
            System.out.print("[HP][" + ConsoleTextColor.FONT_YELLOW);
        else
            System.out.print("[HP][" + ConsoleTextColor.FONT_RED);

        if (enemyHpBar == 0 && this.enemyPokemon.getBattleStats()[Stat.HP.getID()] > 0) enemyHpBar = 1;
        for (int i = 0; i < hpBarLength; i++) {
            if (i + 1 <= enemyHpBar) System.out.print("=");
            else System.out.print(" ");
        }
//            System.out.println("] " + enemyPokemon.getBattleStats()[Stat.HP.getID()] +  " / " + enemyPokemon.getStats()[Stat.HP.getID()] + "\n");
        System.out.println(ConsoleTextColor.RESET + "]\n");

        // 내 포켓몬 정보 출력
        System.out.println(this.myPokemon.getName() + " " + this.myPokemon.getGender().getSYMBOL() + " Lv." + this.myPokemon.getLevel());

        myHpBar = (int) ((float) this.myPokemon.getBattleStats()[Stat.HP.getID()] / (float) this.myPokemon.getStats()[Stat.HP.getID()] * hpBarLength);
        if (myHpBar >= (hpBarLength / 2))
            System.out.print("[HP][" + ConsoleTextColor.FONT_GREEN);
        else if (myHpBar >= (hpBarLength / 4))
            System.out.print("[HP][" + ConsoleTextColor.FONT_YELLOW);
        else
            System.out.print("[HP][" + ConsoleTextColor.FONT_RED);

        if (myHpBar == 0 && this.myPokemon.getBattleStats()[Stat.HP.getID()] > 0) myHpBar = 1;
        for (int i = 0; i < hpBarLength; i++) {
            if (i + 1 <= myHpBar) System.out.print("=");
            else System.out.print(" ");
        }
        System.out.println(ConsoleTextColor.RESET + "] " +
                this.myPokemon.getBattleStats()[Stat.HP.getID()] +  " / " + this.myPokemon.getStats()[Stat.HP.getID()] + "\n");

        // 시작
        System.out.print(
                KorJongsung.isHave(this.myPokemon.getName(), "은 ", "는 ") + "무엇을 할까?" +
                "\n1)싸운다    2)포켓몬    3)가방    4)도망간다\n: ");
    }

    public int selectMove(Scanner sc) {
        while (true) {
            if (this.myPokemon.isNoPp()) return -1;

            while (true) {
                System.out.println();
                for (int i = 0; i < this.myPokemon.getMoves().length; i++) {
                    if (this.myPokemon.getPp()[i] <= 0) {
                        System.out.print((i + 1) + ")" +
                                ConsoleTextColor.FONT_RED + this.myPokemon.getMoves()[i].getName() + ConsoleTextColor.RESET);
                        System.out.println(" [PP " +
                                ConsoleTextColor.FONT_RED + this.myPokemon.getPp()[i] + " / " + this.myPokemon.getMoves()[i].getPp() + ConsoleTextColor.RESET + "]    ");

                    } else if (this.myPokemon.getPp()[i] <= this.myPokemon.getMoves()[i].getPp() / 2) {
                            System.out.print((i + 1) + ")" + this.myPokemon.getMoves()[i].getName());
                            System.out.print(" [PP " +
                                    ConsoleTextColor.FONT_YELLOW + this.myPokemon.getPp()[i] + " / " + this.myPokemon.getMoves()[i].getPp() + ConsoleTextColor.RESET + "]    ");

                    } else {
                        System.out.print((i + 1) + ")" + this.myPokemon.getMoves()[i].getName());
                        System.out.print(" [PP " + this.myPokemon.getPp()[i] + " / " + this.myPokemon.getMoves()[i].getPp() + "]    ");
                    }
                }
                System.out.print((this.myPokemon.getMoves().length + 1) + ")돌아간다\n: ");
                int select = sc.nextInt();

                // 기술 선택
                switch (select) {
                    case 1:
                        if (this.myPokemon.getPp()[0] <= 0) System.out.println("PP가 부족해 사용할 수 없다!\n");
                        else return 0;
                        break;
                    case 2:
                        if (this.myPokemon.getMoves().length < 2) break;
                        if (this.myPokemon.getPp()[1] <= 0) System.out.println("PP가 부족해 사용할 수 없다!\n");
                        else return 1;
                        break;
                    case 3:
                        if (this.myPokemon.getMoves().length < 3) break;
                        if (this.myPokemon.getPp()[2] <= 0) System.out.println("PP가 부족해 사용할 수 없다!\n");
                        else return 2;
                        break;
                    case 4:
                        if (this.myPokemon.getMoves().length < 4) break;
                        if (this.myPokemon.getPp()[3] <= 0) System.out.println("PP가 부족해 사용할 수 없다!\n");
                        else return 3;
                        break;
                    default:
                        return 5;
                }
            }
        }
    }

    public void attack(Pokemon attackPokemon, Pokemon targetPokemon, int moveIndex) {
        String aPokemonName;
        if (attackPokemon == this.myPokemon) aPokemonName = attackPokemon.getName();
        else aPokemonName = this.kindOfEnemy + attackPokemon.getName();

        MoveList sMove = MoveList.STRUGGLE;
        if (moveIndex != -1) {
            sMove = attackPokemon.getMoves()[moveIndex];
            attackPokemon.setPp(attackPokemon.getPp()[moveIndex] - 1, moveIndex);
        }

        // (데미지 = (((((((레벨 × 2 ÷ 5) + 2) × 위력 × (특수)공격 ÷ 50) ÷ (특수)방어) × Mod1) + 2) × [[급소]] ×
        //           Mod2 ×  랜덤수 ÷ 100) × 자속보정 × 타입상성1 × 타입상성2 × Mod3)

        int damage = 0;
        float type1 = typeCaculate(sMove.getType(), targetPokemon.getType1());
        float type2 = typeCaculate(sMove.getType(), targetPokemon.getType2());
        float critical = isCriticalHit();

        // 명중판정용 난수 발생
        int accuraryRate = 100 - (int) (Math.random() * 100 + 1);

        // 명중판정, -1 : 필중기
        if (sMove.getAccurary() >= accuraryRate || sMove.getAccurary() == -1) {

            if (sMove.getKind() == MoveList.Kind.PHYSICAL) {
                // 물리공격
                damage = damageCaculate(attackPokemon, targetPokemon, sMove, critical);
            } else if (sMove.getKind() == MoveList.Kind.SPECIAL) {
                // 특수공격
                damage = damageCaculate(attackPokemon, targetPokemon, sMove, critical);
            } else if (sMove.getKind() == MoveList.Kind.STATUS) {
                // 변화기

            } else {
                System.out.println(ConsoleTextColor.FONT_RED + "기술의 유형이 올바르지 않습니다!" + ConsoleTextColor.RESET);
            }
            System.out.println(aPokemonName + "의 " + sMove.getName() + "!");

            System.out.println("상성 : " + type1 * type2 + "배");

            if (type1 * type2 == 0) {
                System.out.println("효과가 없는 것 같다...");
            } else if (type1 * type2 > 1 && sMove.getKind() != MoveList.Kind.STATUS) {
                System.out.println("효과가 굉장했다!");
            } else if (type1 * type2 < 1 && sMove.getKind() != MoveList.Kind.STATUS) {
                System.out.println("효과가 별로인 것 같다...");
            }
            if (critical > 1 && type1 * type2 != 0 && sMove.getKind() != MoveList.Kind.STATUS) {
                System.out.println(ConsoleTextColor.FONT_RED + "급소에 맞았다!" + ConsoleTextColor.RESET);
            }

            int newHp = targetPokemon.getBattleStats()[Stat.HP.getID()] - damage;
            if (newHp < 0) newHp = 0;
            targetPokemon.setCurrentHp(newHp);

            System.out.println("damage : " + damage);
        } else {
            System.out.println(aPokemonName + "의 " + sMove.getName() + "!");
            System.out.println("그러나 " + attackPokemon.getName() + "의 공격은 빗나갔다!");
        }
    }
    public boolean isRun() {
        // 도망 확률 계산
        int runRate, mySpeed, enemySpeed;

        mySpeed = this.myPokemon.getBattleStats()[Stat.SPEED.getID()];
        if (this.enemyPokemon.getBattleStats()[Stat.SPEED.getID()] == 0) {
            // 적 스피드가 0일 시 계산값 1로 고정
            enemySpeed = 1;
        } else {
            enemySpeed = this.enemyPokemon.getBattleStats()[Stat.SPEED.getID()];
        }

        runRate = 128 * mySpeed / enemySpeed + 30 * this.getRunTryCount();

        int temp = (int) (Math.random() * 256);
        System.out.print(runRate % 256 + " > " + temp + " ? "); // 디버그용

        if (runRate % 256 > temp) {
            System.out.println("true!"); // 디버그용
            return true;
        } else {
            System.out.println("false!"); // 디버그용
            this.setRunTryCount(this.getRunTryCount() + 1);
            return false;
        }
    }
}
