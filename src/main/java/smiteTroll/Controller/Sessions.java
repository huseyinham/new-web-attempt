package smiteTroll.Controller;

import smiteTroll.Classes.God;
import smiteTroll.Classes.Item;
import smiteTroll.Classes.Relic;

import javax.servlet.http.HttpSession;
import java.util.List;

public class Sessions {

    private HttpSession session;

    public Sessions(HttpSession session) {
        this.session = session;
    }

    public God getCurrentGod() {
        return (God) session.getAttribute("god");
    }

    public void setCurrentGod(God god) {
        session.setAttribute("god", god);
    }

    public List<Item> getItems() {
        return (List<Item>) session.getAttribute("items");
    }

    public void setItems(List<Item> items) {
        session.setAttribute("items", items);
    }

    public List<Relic> getRelics(){
        return (List<Relic>) session.getAttribute("relics");
    }

    public void setRelics(List<Relic> relics) {
        session.setAttribute("relics", relics);
    }
}
