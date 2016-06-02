package smiteTroll.Controller;

import smiteTroll.Classes.God;
import smiteTroll.Classes.Item;
import smiteTroll.Classes.Relic;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class Sessions {

    private HttpSession session;
    private Integer rerollAmount = 3;

    public Sessions(HttpSession session, int rerollAmount) {
        this.session = session;
        this.rerollAmount = rerollAmount;
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
