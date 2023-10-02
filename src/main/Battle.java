package main;

import pokemon.*;
import trainer.Player;
import trainer.Trainer;

import pokemon.Pokemon.Stat;
import trainer.ai.WildAi;

public class Battle {
    private Trainer player, enemy;
    private Pokemon myPokemon, enemyPokemon;

    private int currentTurn = 0;
    private int runTryCount = 0;

    private byte[] myRank = new byte[]{0, 0, 0, 0, 0};
    private byte[] enemyRank = new byte[]{0, 0, 0, 0, 0};

    public Battle(Trainer player, Trainer enemy) {
        this.player = player;
        this.enemy = enemy;
        this.myPokemon = player.getPokemons()[0];
        this.enemyPokemon = enemy.getPokemons()[0];

        initBattle();

        battleIntro();
    }

    // getter setter
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

    // 배틀 진행 ==================================================================================================
    public void battleIntro() {
        if (this.enemy.getAi().getAiName() == "wild") {
            System.out.println("앗! 야생 " +
                    KorJongsung.isHave(this.enemyPokemon.getName(), "이 ", "가 ")  + "나타났다!");

        } else {
            System.out.println(this.enemy.getTrainerClass() + " " +
                    KorJongsung.isHave(this.enemy.getTrainerName(), "이 ", "가 ") + "승부를 걸어왔다!");

            System.out.println(this.enemy.getTrainerClass() + " " +
                    KorJongsung.isHave(this.enemy.getTrainerName(), "은 ", "는 ") +
                    KorJongsung.isHave(this.enemyPokemon.getName(), "을 ", "를 ") + "내보냈다!");
        }
        System.out.println("가랏! " + this.myPokemon.getName() + "!");
    }
}
