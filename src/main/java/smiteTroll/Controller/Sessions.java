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
        List<Item> items = new ArrayList<>();
        items.add((Item) session.getAttribute("itemOne"));
        items.add((Item) session.getAttribute("itemTwo"));
        items.add((Item) session.getAttribute("itemThree"));
        items.add((Item) session.getAttribute("itemFour"));
        items.add((Item) session.getAttribute("itemFive"));
        items.add((Item) session.getAttribute("itemSix"));
        return items;
    }

    public void setItems(List<Item> items) {
        session.setAttribute("itemOne", items.get(0));
        session.setAttribute("itemTwo", items.get(1));
        session.setAttribute("itemThree", items.get(2));
        session.setAttribute("itemFour", items.get(3));
        session.setAttribute("itemFive", items.get(4));
        session.setAttribute("itemSix", items.get(5));
    }

    public Item getFirstItem() {
        return (Item) session.getAttribute("itemOne");
    }
    public Item getSecondItem() {
        return (Item) session.getAttribute("itemTwo");
    }
    public Item getThirdItem() {
        return (Item) session.getAttribute("itemThree");
    }
    public Item getFourthItem() {
        return (Item) session.getAttribute("itemFour");
    }
    public Item getFifthItem() {
        return (Item) session.getAttribute("itemFive");
    }
    public Item getSixthItem() {
        return (Item) session.getAttribute("itemSix");
    }

    //Relics
    public List<Relic> getRelics(){
        List<Relic> relics = new ArrayList<>();
        relics.add((Relic) session.getAttribute("relicOne"));
        relics.add((Relic) session.getAttribute("relicTwo"));
        return relics;
    }

    public void setRelics(List<Relic> relics) {
        session.setAttribute("relicOne", relics.get(0));
        session.setAttribute("relicTwo", relics.get(1));
    }

    public Relic getFirstRelic(){
        return (Relic) session.getAttribute("relicOne");
    }

    public Relic getSecondRelic(){
        return (Relic) session.getAttribute("relicTwo");
    }

    //RerollAmount
    public Integer getRerollAmount(){
        return (Integer) session.getAttribute("rerollAmount");
    }

    public void setRerollAmount(Integer rerollAmount){
        session.setAttribute("rerollAmount", rerollAmount);
    }
}
