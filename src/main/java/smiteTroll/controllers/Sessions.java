package smiteTroll.controllers;

import smiteTroll.classes.God;
import smiteTroll.classes.Item;
import smiteTroll.classes.Relic;

import javax.servlet.http.HttpSession;
import java.util.List;

public class Sessions {

    private HttpSession session;

    public Sessions(HttpSession session) {
        this.session = session;
    }

    //Gods
    public God getCurrentGod() {
        return (God) session.getAttribute("god");
    }

    public void setCurrentGod(God god) {
        session.setAttribute("god", god);
    }

    //Items
    public List<Item> getItems() {
        return (List<Item>) session.getAttribute("itemList");
    }

    public void setItems(List<Item> items) {
        session.setAttribute("itemList", items);
    }

    //Relics
    public List<Relic> getRelics(){
        return (List<Relic>) session.getAttribute("relicList");
    }

    public void setRelics(List<Relic> relics) {
        session.setAttribute("relicList", relics);
    }

    //RerollAmount
    public Integer getRerollAmount(){
        return (Integer) session.getAttribute("rerollAmount");
    }

    public void setRerollAmount(Integer rerollAmount){
        session.setAttribute("rerollAmount", rerollAmount);
    }
}
