package trainer;

import pokemon.Pokemon;
import pokemon.sample.*;

public class Player extends Trainer {
    public Player() {
        this.setTrainerName("난천");
        this.setPokemons(new Pokemon[]{
                new CynthiaSpiritomb(), new CynthiaRoserade(), new CynthiaTogekiss(),
                new CynthiaLucario(), new CynthiaMilotic(), new CynthiaGarchomp()
        });
    }
    public Player(String name, Pokemon[] pokemons) {
        this.setTrainerName(name);
        this.setPokemons(pokemons);
    }
}
