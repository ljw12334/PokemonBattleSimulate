package pokemon.sample;

import pokemon.*;
import pokemon.items.SitrusBerry;

public class CynthiaGarchomp extends Pokemon {
    public CynthiaGarchomp() {
        this.setPokemonKind(PokemonList.GARCHOMP); // 한카리아스
        this.setAbility(Ability.ROUGH_SKIN); // 까칠한피부

        this.setLevel(78);
        this.setGender(Gender.FEMALE);
        this.setNature(Nature.JOLLY);
        this.setIvs(31, 31, 31, 0, 31, 31);
        this.setEvs(52, 252, 0, 0, 0, 204);

        this.setMoves(new MoveList[]{MoveList.DRAGON_CLAW, MoveList.EARTHQUAKE,
                                     MoveList.SWORDS_DANCE, MoveList.POISON_JAB});

        this.setItem(new SitrusBerry());

        initPokemon();

    }
}
