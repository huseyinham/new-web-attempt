package smiteTroll.specials;

import smiteTroll.classes.God;
import smiteTroll.classes.Item;

import java.util.List;

public class SpecificItemsForGods {
    private God god;
    private List<Item> items;

    public SpecificItemsForGods(God god, List<Item> items) {
        this.god = god;
        this.items = items;
    }

    public void checkUniqueCircumstance() {
        if (god.getGodName().equals("Ratatoskr")) {
            Item acornOfYggdrasil = new Item("Acorn of Yggdrasil", "physical_boots", "resources/itemImages/OpalAcorn_T3.png");
            items.set(0, acornOfYggdrasil);
        }
    }

    public List<Item> getItems() {
        return items;
    }
}