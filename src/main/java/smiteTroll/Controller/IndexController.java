package smiteTroll.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import smiteTroll.Classes.God;
import smiteTroll.Classes.Item;
import smiteTroll.Classes.Relic;
import smiteTroll.Repositories.GodRepository;
import smiteTroll.Repositories.ItemRepository;
import smiteTroll.Repositories.RelicRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private GodRepository godRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private RelicRepository relicRepository;

    @RequestMapping("/")
    public String handleIndex(Model m) throws SQLException, IOException, ClassNotFoundException {
        God god = godRepository.getNewGod();
        m.addAttribute("godName", god.getGodName());

        List<Item> items = itemRepository.getItems(god.getGodType());
        m.addAttribute("items", items);

        List<Relic> relics = relicRepository.getRelics();
        m.addAttribute("relics", relics);

        return "index";
    }

}



