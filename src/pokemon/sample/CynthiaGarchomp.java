package pokemon.sample;

import pokemon.*;
import pokemon.items.SitrusBerry;

public class CynthiaGarchomp extends Pokemon {
    public CynthiaGarchomp() {
        this.pokemonName = PokemonList.GARCHOMP; // 한카리아스

        this.ability = Ability.ROUGH_SKIN; // 까칠한피부

        this.level = 78;
        this.gender = Gender.FEMALE;
        this.nature = Nature.JOLLY;
        this.ivs = new int[]{31, 31, 31, 0, 31, 31};
        this.evs = new int[]{52, 252, 0, 0, 0, 204};

        this.moves = new Move[]{};
        this.item = new SitrusBerry();

        initPokemon();

    }
}
