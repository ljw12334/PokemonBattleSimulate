package trainer;

import pokemon.Pokemon;
import pokemon.sample.MissingNo;
import trainer.ai.BattleAi;

public class Trainer {
    private BattleAi ai;
    private String trainerClass;
    private String trainerName;
    private Pokemon[] pokemons = new Pokemon[6];
    private int prizeMoney;
    private int penaltyMoney;


    public int getPrizeMoney() {
        return prizeMoney;
    }
    public void setPrizeMoney(int prizeMoney) {
        this.prizeMoney = prizeMoney;
    }
    public int getPenaltyMoney() {
        return penaltyMoney;
    }
    public void setPenaltyMoney(int penaltyMoney) {
        this.penaltyMoney = penaltyMoney;
    }

    public BattleAi getAi() {
        return ai;
    }
    public void setAi(BattleAi ai) {
        this.ai = ai;
    }
    public String getTrainerClass() {
        return trainerClass;
    }
    public void setTrainerClass(String trainerClass) {
        this.trainerClass = trainerClass;
    }
    public String getTrainerName() {
        return trainerName;
    }
    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public Pokemon[] getPokemons() {
        return pokemons;
    }

    public void setPokemons(Pokemon[] pokemons) {
        if (pokemons == null) {
            this.pokemons[0] = new MissingNo();
        }

        if (pokemons.length < 6) {
            for (int i = 0; i < pokemons.length; i++) {
                this.pokemons[i] = pokemons[i];
            }
        } else {
            for (int i = 0; i < 6; i++) {
                this.pokemons[i] = pokemons[i];
            }
        }

    }
}
