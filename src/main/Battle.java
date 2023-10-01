package main;

import pokemon.Ability;
import pokemon.Pokemon;
import pokemon.Type;
import trainer.Trainer;

import pokemon.Pokemon.Stat;

public class Battle {
    private Trainer player, enemy;
    private Pokemon myPokemon, enemyPokemon;

    private byte[] myRank = new byte[]{0, 0, 0, 0, 0};
    private byte[] enemyRank = new byte[]{0, 0, 0, 0, 0};



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

    public Battle(Trainer player, Trainer enemy) {
        this.player = player;
        this.enemy = enemy;
        this.myPokemon = player.getPokemons()[0];
        this.enemyPokemon = enemy.getPokemons()[0];
    }

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

    public float isCritialHit() {
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
    public float mod2() {
        return 1;
    }
    // 특성, 도구, 열매에 의한 데미지 증감
    public float mod3() {
        return 1;
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
}
