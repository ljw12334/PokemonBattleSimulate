package pokemon.sample;

import pokemon.*;

public class CynthiaRoserade extends Pokemon {
    public CynthiaRoserade() {
        this.setPokemonKind(PokemonList.ROSERADE); // 로즈레이드
        this.setAbility(Ability.POISON_POINT); // 독가시

        this.setLevel(74);
        this.setGender(Gender.FEMALE);
        this.setNature(Nature.TIMID);
        this.setIvs(31, 0, 31, 31, 31, 31);
        this.setEvs(52, 0, 0, 252, 0, 204);

        this.setMoves(new MoveList[]{MoveList.DAZZLING_GLEAM, MoveList.SHADOW_BALL,
                                     MoveList.SLUDGE_BOMB, MoveList.ENERGY_BALL});

        this.setItem(null);

        initPokemon();

    }
}
