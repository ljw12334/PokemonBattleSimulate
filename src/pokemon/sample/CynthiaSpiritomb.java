package pokemon.sample;

import pokemon.*;

public class CynthiaSpiritomb extends Pokemon {
    public CynthiaSpiritomb() {
        this.setPokemonKind(PokemonList.SPIRITOMB); // 화강돌
        this.setAbility(Ability.PRESSURE); // 프레셔

        this.setLevel(74);
        this.setGender(Gender.FEMALE);
        this.setNature(Nature.QUIET);
        this.setIvs(new int[]{31, 31, 31, 31, 31, 31});
        this.setEvs(new int[]{252, 0, 0, 252, 0, 6});

        this.setMoves(new MoveList[]{MoveList.SHADOW_BALL, MoveList.DARK_PULSE,
                                     MoveList.WILL_O_WISP, MoveList.SUCKER_PUNCH});

        this.setItem(null);

        initPokemon();

    }
}
