package trainer;

import pokemon.Pokemon;
import pokemon.sample.*;

public class Cynthia extends Trainer {
    public Cynthia() {
        setTrainerName("난천");

        this.setPokemons(new Pokemon[]{
                new CynthiaSpiritomb(), new CynthiaRoserade(), new CynthiaTogekiss(),
                new CynthiaLucario(), new CynthiaMilotic(), new CynthiaGarchomp()
        });
    }
}
