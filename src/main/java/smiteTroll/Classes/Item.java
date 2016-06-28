package smiteTroll.classes;

public class Item {

    private String itemName;
    private String itemType;
    private String itemImage;

    public Item(String itemName, String itemType, String itemImage) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemImage() {
        return itemImage;
    }
}
