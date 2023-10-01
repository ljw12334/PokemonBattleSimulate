package pokemon.sample;

import pokemon.*;

public class CynthiaLucario extends Pokemon {
    public CynthiaLucario() {
        this.setPokemonKind(PokemonList.LUCARIO); // 루카리오
        this.setAbility(Ability.INNER_FOCUS); // 정신력

        this.setLevel(76);
        this.setGender(Gender.MALE);
        this.setNature(Nature.TIMID);
        this.setIvs(31, 0, 31, 31, 31, 31);
        this.setEvs(52, 0, 0, 252, 0, 204);

        this.setMoves(new MoveList[]{MoveList.AURA_SPHERE, MoveList.DRAGON_PULSE,
                                     MoveList.FLASH_CANNON, MoveList.PSYCHIC});

        this.setItem(null);

        initPokemon();

    }
}
