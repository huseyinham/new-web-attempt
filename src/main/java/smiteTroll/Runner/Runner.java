package smiteTroll.Runner;

import smiteTroll.Classes.God;
import smiteTroll.Classes.Relic;
import smiteTroll.Repositories.GodRepository;
import smiteTroll.Classes.Item;
import smiteTroll.Repositories.ItemRepository;
import smiteTroll.Repositories.RelicRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Runner {

    public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException {
        GodRepository godRepo = new GodRepository();
        God result = godRepo.getNewGod();
        System.out.println(result.getGodName() + "\t" + result.getGodType() + "\n");

        ItemRepository itemRepo = new ItemRepository();
        List<Item> itemResult = itemRepo.getItems(result.getGodType());
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
}
