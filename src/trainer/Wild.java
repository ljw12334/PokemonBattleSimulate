package trainer;

import pokemon.Pokemon;
import pokemon.sample.CynthiaSpiritomb;

public class Wild extends Trainer {
    public Wild() {
        this.setTrainerName("");
        this.setPokemons(new Pokemon[]{
                new CynthiaSpiritomb()
        });
    }
    public Wild(Pokemon[] pokemons) {
        this.setTrainerName("");
        this.setPokemons(pokemons);
    }
}
