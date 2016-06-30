/*package smiteTroll.runner;

import smiteTroll.classes.God;
import smiteTroll.classes.Relic;
import smiteTroll.repositories.GodRepository;
import smiteTroll.classes.Item;
import smiteTroll.repositories.ItemRepository;
import smiteTroll.repositories.RelicRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Runner {

    public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException {
        GodRepository godRepo = new GodRepository();
        God godResult = godRepo.getNewGod();
        System.out.println(godResult.getGodName() + "\t" + godResult.getGodType() + "\n");

        ItemRepository itemRepo = new ItemRepository();
        List<Item> itemResult = itemRepo.getItems(godResult.getGodType());
        for(Item item : itemResult){
            System.out.println(item.getItemName() + "\t" + item.getItemType());
        }

        System.out.print("\n");

        RelicRepository relicRepo = new RelicRepository();
        List<Relic> relicResult = relicRepo.getRelics();
        for(Relic relic : relicResult){
            System.out.println(relic.getRelicName());
        }
    }
}*/
