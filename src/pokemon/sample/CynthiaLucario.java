package pokemon.sample;

import pokemon.*;

public class CynthiaLucario extends Pokemon {
    public CynthiaLucario() {
        this.pokemonName = PokemonList.LUCARIO; // 루카리오

        this.ability = Ability.INNER_FOCUS; // 정신력

        this.level = 76;
        this.gender = Gender.MALE;
        this.nature = Nature.TIMID;
        this.ivs = new int[]{31, 0, 31, 31, 31, 31};
        this.evs = new int[]{52, 0, 0, 252, 0, 204};

        this.moves = new MoveList[]{MoveList.AURA_SPHERE, MoveList.DRAGON_PULSE,
                                    MoveList.FLASH_CANNON, MoveList.PSYCHIC};

        this.item = null;

        initPokemon();

    }
}
