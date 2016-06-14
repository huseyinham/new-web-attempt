package smiteTroll.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import smiteTroll.repositories.DataRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class DataController {

    @Autowired
    private DataRepository dataRepository;

    @RequestMapping(value = "/addData", method = RequestMethod.GET)
    public String handleAddData(Model m, HttpSession session) {
        return "addData";
    }

    @RequestMapping(value = "/addGodData", method = RequestMethod.POST)
    public String handleGodAdding(Model m, HttpSession session, HttpServletRequest request) {
        String name = request.getParameter("godName");
        String type = request.getParameter("godType");
        List<String> godTypes = dataRepository.getGodTypes();
        for(String godType : godTypes){
            if(StringUtils.hasText(name) && type.equals(godType.toString())) {
                dataRepository.addNewGodToDB(name, type);
            }
        }
        return "addData";
    }

    @RequestMapping(value = "/removeGodData", method = RequestMethod.POST)
    public String handleGodRemoving(Model m, HttpSession session, HttpServletRequest request) {
        String name = request.getParameter("godName");
        if(name != "") {
            dataRepository.deleteGodFromDB(name);
        }
        return "addData";
    }

    @RequestMapping(value = "/addItemData", method = RequestMethod.POST)
    public String handleItemAdding(Model m, HttpSession session, HttpServletRequest request) {
        String name = request.getParameter("itemName");
        String type = request.getParameter("itemType");
        List<String> itemTypes = dataRepository.getItemTypes();
        for(String itemType : itemTypes){
            if(StringUtils.hasText(name)&& type.equals(itemType.toString())) {
                dataRepository.addNewItemToDB(name, type);
            }
        }
        return "addData";
    }

    @RequestMapping(value = "/removeItemData", method = RequestMethod.POST)
    public String handleItemRemoving(Model m, HttpSession session, HttpServletRequest request) {
        String name = request.getParameter("itemName");
        if(name != "") {
            dataRepository.deleteItemFromDB(name);
        }
        return "addData";
    }

    @RequestMapping(value = "/addRelicData", method = RequestMethod.POST)
    public String handleRelicAdding(Model m, HttpSession session, HttpServletRequest request) {
        String name = request.getParameter("relicName");
        if(StringUtils.hasText(name)) {
            dataRepository.addNewRelicToDB(name);
        }
        return "addData";
    }

    @RequestMapping(value = "/removeRelicData", method = RequestMethod.POST)
    public String handleRelicRemoving(Model m, HttpSession session, HttpServletRequest request) {
        String name = request.getParameter("relicName");
        if(name != "") {
            dataRepository.deleteRelicFromDB(name);
        }
        return "addData";
    }

    @RequestMapping(value = "/addGodData", method = RequestMethod.GET)
    public String handleRefreshAddGodData(Model m, HttpSession session) {
        return handleAddData(m, session);
    }

    @RequestMapping(value = "/removeGodData", method = RequestMethod.GET)
    public String handleRefreshRemoveGodData(Model m, HttpSession session)  {
        return handleAddData(m, session);
    }

    @RequestMapping(value = "/addItemData", method = RequestMethod.GET)
    public String handleRefreshAddItemData(Model m, HttpSession session) {
        return handleAddData(m, session);
    }
    @RequestMapping(value = "/removeItemData", method = RequestMethod.GET)
    public String handleRefreshRemoveItemData(Model m, HttpSession session) {
        return handleAddData(m, session);
    }

    @RequestMapping(value = "/addRelicData", method = RequestMethod.GET)
    public String handleRefreshAddRelicData(Model m, HttpSession session)  {
        return handleAddData(m, session);
    }

    @RequestMapping(value = "/removeRelicData", method = RequestMethod.GET)
    public String handleRefreshRemoveRelicData(Model m, HttpSession session) {
        return handleAddData(m, session);
    }

}