package pokemon.sample;

import pokemon.*;

public class MissingNo extends Pokemon {
    public MissingNo() {
        this.setPokemonKind(PokemonList.MISSING_NO); // 미싱노
        this.setAbility(Ability.PRESSURE); // 프레셔

        this.setLevel(1);
        this.setGender(Gender.NONE);
        this.setNature(Nature.HARDY);
        this.setIvs(new int[]{0, 0, 0, 0, 0, 0});
        this.setEvs(new int[]{0, 0, 0, 0, 0, 0});

        this.setItem(null);

        initPokemon();

    }
}
