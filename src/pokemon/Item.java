package pokemon;

public class Item {

    private ItemList itemID;
    private String name;
    private String kind;
    private String explain;

    private Pokemon havingPokemon;

    public ItemList getItemID() {
        return itemID;
    }
    public String getName() {
        return name;
    }
    public String getKind() {
        return kind;
    }
    public String getExplain() {
        return explain;
    }
    public Pokemon getHavingPokemon() {
        return havingPokemon;
    }
    public void setHavingPokemon(Pokemon havingPokemon) {
        this.havingPokemon = havingPokemon;
    }

    public void initItem(ItemList itemID, Pokemon havingPokemon) {
        this.itemID = itemID;
        this.name = this.itemID.getNAME();
        this.kind = this.itemID.getKIND();
        this.explain = this.itemID.getEXPLAIN();
        this.havingPokemon = havingPokemon;
    }
    public void effect() {

    }
}
