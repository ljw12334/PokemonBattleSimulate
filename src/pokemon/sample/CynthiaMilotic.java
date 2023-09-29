package pokemon.sample;

import pokemon.*;

public class CynthiaMilotic extends Pokemon {
    public CynthiaMilotic() {
        this.pokemonName = PokemonList.MILOTIC; // 밀로틱

        this.ability = Ability.MARVEL_SCALE; // 이상한비늘

        this.level = 74;
        this.gender = Gender.FEMALE;
        this.nature = Nature.BOLD;
        this.ivs = new int[]{31, 0, 31, 31, 31, 31};
        this.evs = new int[]{252, 0, 252, 0, 0, 6};

        this.moves = new MoveList[]{MoveList.RECOVER, MoveList.MIRROR_COAT,
                                    MoveList.ICE_BEAM, MoveList.SCALD};

        this.item = null;

        initPokemon();

    }
}
