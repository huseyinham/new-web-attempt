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
import java.util.ArrayList;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private GodRepository godRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private RelicRepository relicRepository;

    private Integer rerollAmount = 3;

    @RequestMapping("/")
    public String handleRoot(Model m, HttpSession session) throws SQLException, IOException, ClassNotFoundException {
        return handleIndex(m, session);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String handleIndex(Model m, HttpSession session) throws SQLException, IOException, ClassNotFoundException {
        rerollAmount = 3;
        Sessions sessions = new Sessions(session, rerollAmount);
        sessions.setRerollAmount(rerollAmount);
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
        Sessions sessions = new Sessions(session, rerollAmount);
        sessions.setRerollAmount(rerollAmount);
        if (outOfRerolls(sessions.getRerollAmount())) {
            getBuildForGod(m, sessions, sessions.getCurrentGod());
            return "index";
        }
        God god = godRepository.reRoll(sessions.getCurrentGod());
        rerollAmount --;
        sessions.setRerollAmount(rerollAmount);
        getBuildForGod(m, sessions, god);
        return "index";
    }

    @RequestMapping(value = "/itemOneReroll", method = RequestMethod.POST)
    public String handleItemOneRerolls(Model m, HttpSession session) throws SQLException, IOException, ClassNotFoundException {
        Sessions sessions = new Sessions(session, rerollAmount);
        sessions.setRerollAmount(rerollAmount);
        if (outOfRerolls(sessions.getRerollAmount())) {
            getBuildForGod(m, sessions, sessions.getCurrentGod());
            return "index";
        }
        Item item = itemRepository.reRoll(sessions.getCurrentGod(), sessions.getFirstItem(),sessions.getItems());
        rerollAmount --;
        List<Item> items = new ArrayList<>();
        items.add(item);
        items.add(sessions.getSecondItem());
        items.add(sessions.getThirdItem());
        items.add(sessions.getFourthItem());
        items.add(sessions.getFifthItem());
        items.add(sessions.getSixthItem());
        sessions.setItems(items);
        getBuildForGod(m, sessions, sessions.getCurrentGod());
        return "index";
    }

    @RequestMapping(value = "/itemTwoReroll", method = RequestMethod.POST)
    public String handleItemTwoRerolls(Model m, HttpSession session) throws SQLException, IOException, ClassNotFoundException {
        Sessions sessions = new Sessions(session, rerollAmount);
        sessions.setRerollAmount(rerollAmount);
        if (outOfRerolls(sessions.getRerollAmount())) {
            getBuildForGod(m, sessions, sessions.getCurrentGod());
            return "index";
        }
        Item item = itemRepository.reRoll(sessions.getCurrentGod(), sessions.getSecondItem(),sessions.getItems());
        rerollAmount --;
        List<Item> items = new ArrayList<>();
        items.add(sessions.getFirstItem());
        items.add(item);
        items.add(sessions.getThirdItem());
        items.add(sessions.getFourthItem());
        items.add(sessions.getFifthItem());
        items.add(sessions.getSixthItem());
        sessions.setItems(items);
        getBuildForGod(m, sessions, sessions.getCurrentGod());
        return "index";
    }

    @RequestMapping(value = "/itemThreeReroll", method = RequestMethod.POST)
    public String handleItemThreeRerolls(Model m, HttpSession session) throws SQLException, IOException, ClassNotFoundException {
        Sessions sessions = new Sessions(session, rerollAmount);
        sessions.setRerollAmount(rerollAmount);
        if (outOfRerolls(sessions.getRerollAmount())) {
            getBuildForGod(m, sessions, sessions.getCurrentGod());
            return "index";
        }
        Item item = itemRepository.reRoll(sessions.getCurrentGod(), sessions.getThirdItem(),sessions.getItems());
        rerollAmount --;
        List<Item> items = new ArrayList<>();
        items.add(sessions.getFirstItem());
        items.add(sessions.getSecondItem());
        items.add(item);
        items.add(sessions.getFourthItem());
        items.add(sessions.getFifthItem());
        items.add(sessions.getSixthItem());
        sessions.setItems(items);
        getBuildForGod(m, sessions, sessions.getCurrentGod());
        return "index";
    }

    @RequestMapping(value = "/itemFourReroll", method = RequestMethod.POST)
    public String handleItemFourRerolls(Model m, HttpSession session) throws SQLException, IOException, ClassNotFoundException {
        Sessions sessions = new Sessions(session, rerollAmount);
        sessions.setRerollAmount(rerollAmount);
        if (outOfRerolls(sessions.getRerollAmount())) {
            getBuildForGod(m, sessions, sessions.getCurrentGod());
            return "index";
        }
        Item item = itemRepository.reRoll(sessions.getCurrentGod(), sessions.getFourthItem(),sessions.getItems());
        rerollAmount --;
        List<Item> items = new ArrayList<>();
        items.add(sessions.getFirstItem());
        items.add(sessions.getSecondItem());
        items.add(sessions.getThirdItem());
        items.add(item);
        items.add(sessions.getFifthItem());
        items.add(sessions.getSixthItem());
        sessions.setItems(items);
        getBuildForGod(m, sessions, sessions.getCurrentGod());
        return "index";
    }

    @RequestMapping(value = "/itemFiveReroll", method = RequestMethod.POST)
    public String handleItemFiveRerolls(Model m, HttpSession session) throws SQLException, IOException, ClassNotFoundException {
        Sessions sessions = new Sessions(session, rerollAmount);
        sessions.setRerollAmount(rerollAmount);
        if (outOfRerolls(sessions.getRerollAmount())) {
            getBuildForGod(m, sessions, sessions.getCurrentGod());
            return "index";
        }
        Item item = itemRepository.reRoll(sessions.getCurrentGod(), sessions.getFifthItem(),sessions.getItems());
        rerollAmount --;
        List<Item> items = new ArrayList<>();
        items.add(sessions.getFirstItem());
        items.add(sessions.getSecondItem());
        items.add(sessions.getThirdItem());
        items.add(sessions.getFourthItem());
        items.add(item);
        items.add(sessions.getSixthItem());
        sessions.setItems(items);
        getBuildForGod(m, sessions, sessions.getCurrentGod());
        return "index";
    }

    @RequestMapping(value = "/itemSixReroll", method = RequestMethod.POST)
    public String handleItemSixRerolls(Model m, HttpSession session) throws SQLException, IOException, ClassNotFoundException {
        Sessions sessions = new Sessions(session, rerollAmount);
        sessions.setRerollAmount(rerollAmount);
        if (outOfRerolls(sessions.getRerollAmount())) {
            getBuildForGod(m, sessions, sessions.getCurrentGod());
            return "index";
        }
        Item item = itemRepository.reRoll(sessions.getCurrentGod(), sessions.getSixthItem(),sessions.getItems());
        rerollAmount --;
        List<Item> items = new ArrayList<>();
        items.add(sessions.getFirstItem());
        items.add(sessions.getSecondItem());
        items.add(sessions.getThirdItem());
        items.add(sessions.getFourthItem());
        items.add(sessions.getFifthItem());
        items.add(item);
        sessions.setItems(items);
        getBuildForGod(m, sessions, sessions.getCurrentGod());
        return "index";
    }

    @RequestMapping(value = "/relicOneReroll", method = RequestMethod.POST)
    public String handleRelicOneRerolls(Model m, HttpSession session) throws SQLException, IOException, ClassNotFoundException {
        Sessions sessions = new Sessions(session, rerollAmount);
        sessions.setRerollAmount(rerollAmount);
        if (outOfRerolls(sessions.getRerollAmount())) {
            getBuildForGod(m, sessions, sessions.getCurrentGod());
            return "index";
        }
        Relic relic = relicRepository.reRollRelic(sessions.getFirstRelic(), sessions.getSecondRelic());
        rerollAmount --;
        List<Relic> relics = new ArrayList<>();
        relics.add(relic);
        relics.add(sessions.getSecondRelic());
        sessions.setRelics(relics);
        getBuildForGod(m, sessions, sessions.getCurrentGod());
        return "index";
    }

    @RequestMapping(value = "/relicTwoReroll", method = RequestMethod.POST)
    public String handleRelicTwoRerolls(Model m, HttpSession session) throws SQLException, IOException, ClassNotFoundException {
        Sessions sessions = new Sessions(session, rerollAmount);
        sessions.setRerollAmount(rerollAmount);
        if (outOfRerolls(sessions.getRerollAmount())) {
            getBuildForGod(m, sessions, sessions.getCurrentGod());
            return "index";
        }
        Relic relic = relicRepository.reRollRelic(sessions.getSecondRelic(), sessions.getFirstRelic());
        rerollAmount --;
        List<Relic> relics = new ArrayList<>();
        relics.add(sessions.getFirstRelic());
        relics.add(relic);
        sessions.setRelics(relics);
        getBuildForGod(m, sessions, sessions.getCurrentGod());
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

    private boolean outOfRerolls(Integer rerollAmount){
        return rerollAmount == 0;
    }
}