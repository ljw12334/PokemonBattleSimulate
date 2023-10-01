package pokemon.sample;

import pokemon.*;

public class CynthiaMilotic extends Pokemon {
    public CynthiaMilotic() {
        this.setPokemonKind(PokemonList.MILOTIC); // 밀로틱
        this.setAbility(Ability.MARVEL_SCALE); // 이상한비늘

        this.setLevel(74);
        this.setGender(Gender.FEMALE);
        this.setNature(Nature.BOLD);
        this.setIvs(new int[]{31, 0, 31, 31, 31, 31});
        this.setEvs(new int[]{252, 0, 252, 0, 0, 6});

        this.setMoves(new MoveList[]{MoveList.RECOVER, MoveList.MIRROR_COAT,
                                     MoveList.ICE_BEAM, MoveList.SCALD});

        this.setItem(null);

        initPokemon();

    }
}
