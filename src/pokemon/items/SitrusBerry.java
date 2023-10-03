package pokemon.items;

import main.KorJongsung;
import pokemon.Item;
import pokemon.ItemList;
import pokemon.Pokemon;

public class SitrusBerry extends Item {
    // 체력이 절반 이하로 감소할시 최대체력의 1/4 회복
    public SitrusBerry(Pokemon havingPokemon) {
         initItem(ItemList.SITRUS_BERRY, havingPokemon);
     }

    @Override
    public void effect() {
         int currentHp = this.getHavingPokemon().getBattleStats()[Pokemon.Stat.HP.getID()];
         int fullHp = this.getHavingPokemon().getBaseStats()[Pokemon.Stat.HP.getID()];
         if (currentHp < fullHp / 2) {
             this.getHavingPokemon().setCurrentHp(currentHp + fullHp / 4);
             System.out.println(
                     KorJongsung.isHave(getHavingPokemon().getName(), "은 ", "는 ") +
                     KorJongsung.isHave(this.getName(), "으로 ", "로 ") + "조금 회복했다");

             this.getHavingPokemon().setItem(null);
         }
    }
}
