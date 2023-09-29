package pokemon.sample;

import pokemon.*;

public class CynthiaSpiritomb extends Pokemon {
    public CynthiaSpiritomb() {
        this.pokemonName = PokemonList.SPIRITOMB; // 화강돌

        this.ability = Ability.PRESSURE; // 프레셔

        this.level = 74;
        this.gender = Gender.FEMALE;
        this.nature = Nature.QUIET;
        this.ivs = new int[]{31, 31, 31, 31, 31, 31};
        this.evs = new int[]{252, 0, 0, 252, 0, 6};

        this.moves = new MoveList[]{MoveList.SHADOW_BALL, MoveList.DARK_PULSE,
                                    MoveList.WILL_O_WISP, MoveList.SUCKER_PUNCH};

        this.item = null;

        initPokemon();

    }
}
