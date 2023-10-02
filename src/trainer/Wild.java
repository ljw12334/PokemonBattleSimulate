package trainer;

import pokemon.Pokemon;
import pokemon.sample.CynthiaLucario;
import pokemon.sample.CynthiaSpiritomb;
import trainer.ai.WildAi;

public class Wild extends Trainer {
    public Wild() {
        this.setAi(new WildAi(this));
        this.setTrainerName("");
        this.setPokemons(new Pokemon[]{
                new CynthiaLucario()
        });
    }
    public Wild(Pokemon[] pokemons) {
        this.setAi(new WildAi(this));
        this.setTrainerName("");
        this.setPokemons(pokemons);
    }
}
