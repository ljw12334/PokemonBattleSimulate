package pokemon.sample;

import pokemon.*;

public class CynthiaTogekiss extends Pokemon {
    public CynthiaTogekiss() {
        this.setPokemonKind(PokemonList.TOGEKISS); // 토게키스
        this.setAbility(Ability.SERENE_GRACE); // 하늘의은총

        this.setLevel(76);
        this.setGender(Gender.MALE);
        this.setNature(Nature.TIMID);
        this.setIvs(new int[]{31, 0, 31, 31, 31, 31});
        this.setEvs(new int[]{52, 0, 0, 252, 0, 204});

        this.setMoves(new MoveList[]{MoveList.AIR_SLASH, MoveList.DAZZLING_GLEAM,
                                     MoveList.AURA_SPHERE, MoveList.THUNDER_WAVE});

        this.setItem(null);

        initPokemon();

    }
}
