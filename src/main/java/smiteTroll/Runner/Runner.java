package smiteTroll.Runner;

import smiteTroll.Classes.God;
import smiteTroll.Repositories.GodRepository;
import smiteTroll.Classes.Item;
import smiteTroll.Repositories.ItemRepository;

import java.io.IOException;
import java.sql.SQLException;

public class Runner {

    public static void main(String args[]) throws SQLException, ClassNotFoundException, IOException {
        GodRepository godRepo = new GodRepository();
        God result = godRepo.getNewGod();
        System.out.println(result.getGodName() + "\t" + result.getGodType());
        ItemRepository itemRepo = new ItemRepository();
        Item itemResult = itemRepo.getItems(result.getGodType());
        System.out.println(itemResult.getItemName() + "\t" + itemResult.getItemType());

    }


    //obtain all the items that can be used on that god i.e. physical and neutral items for a physical god
    //randomly choose 6 items out of those items available


}
