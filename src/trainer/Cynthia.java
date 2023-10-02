package trainer;

import pokemon.Pokemon;
import pokemon.sample.*;
import trainer.ai.NormalAi;

public class Cynthia extends Trainer {
    public Cynthia() {
        this.setAi(new NormalAi(this));
        this.setTrainerClass("챔피언");
        this.setTrainerName("난천");

        this.setPokemons(new Pokemon[]{
                new CynthiaSpiritomb(), new CynthiaRoserade(), new CynthiaTogekiss(),
                new CynthiaLucario(), new CynthiaMilotic(), new CynthiaGarchomp()
        });
    }
}
