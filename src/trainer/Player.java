package trainer;

import pokemon.Pokemon;
import pokemon.sample.*;

public class Player extends Trainer {
    public Player() {
        this.setTrainerClass("챔피언");
        this.setTrainerName("난천");
        this.setPokemons(new Pokemon[]{
                new CynthiaSpiritomb(), new CynthiaRoserade(), new CynthiaTogekiss(),
                new CynthiaLucario(), new CynthiaMilotic(), new CynthiaGarchomp()
        });
    }
    public Player(String name, Pokemon[] pokemons) {
        this.setTrainerClass("포켓몬 트레이너");
        this.setTrainerName(name);
        this.setPokemons(pokemons);
    }
}
