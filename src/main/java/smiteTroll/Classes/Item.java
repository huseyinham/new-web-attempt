package smiteTroll.classes;

public class Item {

    private String itemName;
    private String itemType;

    public Item(String itemName, String itemType) {
        this.itemName = itemName;
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }
}
