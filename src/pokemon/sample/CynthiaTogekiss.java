package pokemon.sample;

import pokemon.*;

public class CynthiaTogekiss extends Pokemon {
    public CynthiaTogekiss() {
        this.pokemonName = PokemonList.TOGEKISS; // 토게키스

        this.ability = Ability.SERENE_GRACE; // 하늘의은총

        this.level = 76;
        this.gender = Gender.MALE;
        this.nature = Nature.TIMID;
        this.ivs = new int[]{31, 0, 31, 31, 31, 31};
        this.evs = new int[]{52, 0, 0, 252, 0, 204};

        this.moves = new MoveList[]{MoveList.AIR_SLASH, MoveList.DAZZLING_GLEAM,
                                    MoveList.AURA_SPHERE, MoveList.THUNDER_WAVE};

        this.item = null;

        initPokemon();

    }
}
