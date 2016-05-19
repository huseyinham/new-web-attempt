package smiteTroll.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smiteTroll.Classes.God;
import smiteTroll.Classes.Item;
import smiteTroll.Classes.Relic;
import smiteTroll.Repositories.GodRepository;
import smiteTroll.Repositories.ItemRepository;
import smiteTroll.Repositories.RelicRepository;

import javax.servlet.http.HttpSession;
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
    public String handleRoot(Model m, HttpSession session) throws SQLException, IOException, ClassNotFoundException {
        return handleIndex(m, session);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String handleIndex(Model m, HttpSession session) throws SQLException, IOException, ClassNotFoundException {

        Sessions sessions = new Sessions(session);
        God god = godRepository.getNewGod();
        List<Item> items = itemRepository.getItems(god.getGodType());
        sessions.setItems(items);
        List<Relic> relics = relicRepository.getRelics();
        sessions.setRelics(relics);
        getBuildForGod(m, sessions, god);
        return "index";
    }

    @RequestMapping(value = "/godReroll", method = RequestMethod.POST)
    public String handleGodRerolls(Model m, HttpSession session) throws SQLException, IOException, ClassNotFoundException {

        Sessions sessions = new Sessions(session);
        God god = godRepository.reRoll(sessions.getCurrentGod());
        getBuildForGod(m, sessions, god);
        return "index";
    }

    @RequestMapping(value = "/relicReroll", method = RequestMethod.POST)
    public String handleRelicRerolls(Model m, HttpSession session) throws SQLException, IOException, ClassNotFoundException {

        Sessions sessions = new Sessions(session);
        Relic relic = relicRepository.reRollRelic(sessions.getRelics());
        getBuildForGod(m, sessions, god);
        return "index";
    }

    private void getBuildForGod(Model m, Sessions sessions, God god) throws SQLException, ClassNotFoundException, IOException {

        sessions.setCurrentGod(god);
        m.addAttribute("godName", god.getGodName());
        List<Item> items = sessions.getItems();
        m.addAttribute("items", items);
        List<Relic> relics = sessions.getRelics();
        m.addAttribute("relics", relics);
    }

}



