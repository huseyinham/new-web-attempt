package smiteTroll.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smiteTroll.classes.God;
import smiteTroll.classes.Item;
import smiteTroll.classes.Relic;
import smiteTroll.repositories.GodRepository;
import smiteTroll.repositories.ItemRepository;
import smiteTroll.repositories.RelicRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    protected GodRepository godRepository;
    @Autowired
    protected ItemRepository itemRepository;
    @Autowired
    protected RelicRepository relicRepository;

    private Integer rerollAmount = 3;

    @RequestMapping("/")
    public String handleRoot(Model m, HttpSession session) {
        return handleIndex(m, session);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String handleIndex(Model m, HttpSession session) {
        rerollAmount = 3;
        Sessions sessions = new Sessions(session);

        God god = godRepository.getNewGod();
        List<Item> items = itemRepository.getItems(god);
        List<Relic> relics = relicRepository.getRelics();

        sessions.setCurrentGod(god);
        sessions.setItems(items);
        sessions.setRelics(relics);
        sessions.setRerollAmount(rerollAmount);

        populateModel(m, sessions);
        return "index";
    }

    @RequestMapping(value = "/godReroll", method = RequestMethod.POST)
    public String handleGodRerolls(Model m, HttpSession session) {
        Sessions sessions = new Sessions(session);
        if (checkRerollAmount(m, sessions)) return "index";

        God god = godRepository.reRoll(sessions.getCurrentGod());
        rerollAmount --;

        sessions.setCurrentGod(god);
        sessions.setItems(itemRepository.checkForSpecificItemsForGods(god, sessions.getItems()));
        sessions.setRerollAmount(rerollAmount);

        populateModel(m, sessions);
        return "index";
    }

    @RequestMapping(value = "/itemReroll", method = RequestMethod.POST)
    public String handleItemRerolls(Model m, HttpSession session, HttpServletRequest request) {
        Sessions sessions = new Sessions(session);
        if (checkRerollAmount(m, sessions)) return "index";

        int index = Integer.parseInt(request.getParameter("rerollIndex"));
        List<Item> items = sessions.getItems();
        Item item = itemRepository.reRoll(sessions.getCurrentGod(), items.get(index),sessions.getItems());
        items.set(index, item);
        rerollAmount --;

        sessions.setItems(items);
        sessions.setRerollAmount(rerollAmount);

        populateModel(m, sessions);
        return "index";
    }

    @RequestMapping(value = "/relicReroll", method = RequestMethod.POST)
    public String handleRelicOneRerolls(Model m, HttpSession session, HttpServletRequest request) {
        Sessions sessions = new Sessions(session);
        if (checkRerollAmount(m, sessions)) return "index";

        int index = Integer.parseInt(request.getParameter("rerollIndex"));
        List<Relic> relics = sessions.getRelics();
        Relic relic = relicRepository.reRollRelic(relics);
        relics.set(index, relic);
        rerollAmount --;

        sessions.setRelics(relics);
        sessions.setRerollAmount(rerollAmount);

        populateModel(m, sessions);
        return "index";
    }

    private void populateModel(Model m, Sessions sessions) {
        God god = sessions.getCurrentGod();
        List<Item> items = sessions.getItems();
        List<Relic> relics = sessions.getRelics();
        m.addAttribute("godName", god.getGodName());
        m.addAttribute("items", items);
        m.addAttribute("relics", relics);
        m.addAttribute("godImage", god.getGodImage());
        for(Item item : items){
            m.addAttribute("itemImage", item.getItemImage());
        }
    }

    private boolean checkRerollAmount(Model m, Sessions sessions) {
        if (rerollAmount == 0) {
            populateModel(m, sessions);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/godReroll", method = RequestMethod.GET)
    public String handleRefreshGod(Model m, HttpSession session) {
        return handleIndex(m, session);
    }

    @RequestMapping(value = "/itemReroll", method = RequestMethod.GET)
    public String handleRefreshOne(Model m, HttpSession session)  {
        return handleIndex(m, session);
    }

    @RequestMapping(value = "/relicReroll", method = RequestMethod.GET)
    public String handleRefreshRelicOne(Model m, HttpSession session) {
        return handleIndex(m, session);
    }
}