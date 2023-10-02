package pokemon;

public class Item {

    private ItemList itemID;
    private String name;
    private String kind;
    private String explain;

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

    public void initItem(ItemList itemID) {
        this.itemID = itemID;
        this.name = this.itemID.getNAME();
        this.kind = this.itemID.getKIND();
        this.explain = this.itemID.getEXPLAIN();
    }
}
