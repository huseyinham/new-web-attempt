package smiteTroll.controller;


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
import smiteTroll.specifics.SpecificItemsForGods;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String handleRoot(Model m, HttpSession session) {
        return handleIndex(m, session);
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String handleIndex(Model m, HttpSession session) {
        rerollAmount = 3;
        Sessions sessions = new Sessions(session, rerollAmount);
        sessions.setRerollAmount(rerollAmount);
        God god = godRepository.getNewGod();
        List<Item> items = itemRepository.getItems(god.getGodType());
        new SpecificItemsForGods(god, items).checkUniqueCircumstance();
        sessions.setItems(items);
        List<Relic> relics = relicRepository.getRelics();
        sessions.setRelics(relics);
        getBuildForGod(m, sessions, god);
        return "index";
    }

    @RequestMapping(value = "/godReroll", method = RequestMethod.POST)
    public String handleGodRerolls(Model m, HttpSession session) {
        Sessions sessions = new Sessions(session, rerollAmount);
        sessions.setRerollAmount(rerollAmount);
        if (outOfRerolls(sessions.getRerollAmount())) {
            getBuildForGod(m, sessions, sessions.getCurrentGod());
            return "index";
        }
        God god = godRepository.reRoll(sessions.getCurrentGod());
        rerollAmount --;
        sessions.setRerollAmount(rerollAmount);
        new SpecificItemsForGods(god, sessions.getItems()).checkUniqueCircumstance();
        if (sessions.getCurrentGod().getGodName().equals("Ratatoskr")){
            List<Item> items = sessions.getItems();
            Item item = itemRepository.reRoll(sessions.getCurrentGod(),items.get(0),sessions.getItems());
            items.set(0, item);
            sessions.setItems(items);
        }
        getBuildForGod(m, sessions, god);
        return "index";
    }

    @RequestMapping(value = "/itemReroll", method = RequestMethod.POST)
    public String handleItemRerolls(Model m, HttpSession session, HttpServletRequest request) {
        Sessions sessions = new Sessions(session, rerollAmount);
        sessions.setRerollAmount(rerollAmount);
        if (outOfRerolls(sessions.getRerollAmount())) {
            getBuildForGod(m, sessions, sessions.getCurrentGod());
            return "index";
        }
        int index = Integer.parseInt(request.getParameter("rerollIndex"));
        List<Item> items = sessions.getItems();
        Item item = itemRepository.reRoll(sessions.getCurrentGod(), items.get(index),sessions.getItems());
        rerollAmount --;
        items.set(index, item);
        sessions.setItems(items);
        getBuildForGod(m, sessions, sessions.getCurrentGod());
        return "index";
    }

    @RequestMapping(value = "/relicReroll", method = RequestMethod.POST)
    public String handleRelicOneRerolls(Model m, HttpSession session, HttpServletRequest request) {
        Sessions sessions = new Sessions(session, rerollAmount);
        sessions.setRerollAmount(rerollAmount);
        if (outOfRerolls(sessions.getRerollAmount())) {
            getBuildForGod(m, sessions, sessions.getCurrentGod());
            return "index";
        }
        int index = Integer.parseInt(request.getParameter("rerollIndex"));
        List<Relic> relics = sessions.getRelics();
        Relic relic = relicRepository.reRollRelic(relics);
        rerollAmount --;
        relics.set(index, relic);
        sessions.setRelics(relics);
        getBuildForGod(m, sessions, sessions.getCurrentGod());
        return "index";
    }

    private void getBuildForGod(Model m, Sessions sessions, God god) {
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