package pokemon.items;

import main.KorJongsung;
import pokemon.Item;
import pokemon.ItemList;
import pokemon.Pokemon;

public class LifeOrb extends Item {
    // 공격시 데미지 1.3배, 공격 성공시 본인 최대체력의 1/10이 감소하는 패널티 보유
    public LifeOrb(Pokemon havingPokemon) {
        initItem(ItemList.LIFE_ORB, havingPokemon);
    }

    @Override
    public void effect() {
        int currentHp = this.getHavingPokemon().getBattleStats()[Pokemon.Stat.HP.getID()];
        int maxHp = this.getHavingPokemon().getStats()[Pokemon.Stat.HP.getID()];

        this.getHavingPokemon().setCurrentHp(currentHp - maxHp / 10);
        System.out.println(getHavingPokemon().getName() + "의 생명이 조금 깎였다!");
    }
}
