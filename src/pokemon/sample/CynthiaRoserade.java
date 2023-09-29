package pokemon.sample;

import pokemon.*;

public class CynthiaRoserade extends Pokemon {
    public CynthiaRoserade() {
        this.pokemonName = PokemonList.ROSERADE; // 로즈레이드

        this.ability = Ability.POISON_POINT; // 독가시

        this.level = 74;
        this.gender = Gender.FEMALE;
        this.nature = Nature.TIMID;
        this.ivs = new int[]{31, 0, 31, 31, 31, 31};
        this.evs = new int[]{52, 0, 0, 252, 0, 204};

        this.moves = new MoveList[]{MoveList.DAZZLING_GLEAM, MoveList.SHADOW_BALL,
                                    MoveList.SLUDGE_BOMB, MoveList.ENERGY_BALL};

        this.item = null;

        initPokemon();

    }
}
