package smiteTroll;

import org.junit.Assert;
import org.junit.Test;
import smiteTroll.Classes.God;
import smiteTroll.Repositories.GodRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GodRepositoryTest {

    private GodRepository godRepo = new GodRepository();
    private God previousGod = new God("Thanatos", "physical");

    @Test
    public void testPreviousGodNotReselected() throws SQLException, IOException, ClassNotFoundException {
        God selectedGod = godRepo.reRoll(previousGod);
        Assert.assertTrue(!selectedGod.getGodName().contains(previousGod.getGodName()));
    }

    @Test
    public void testSelectedGodIsInTheList() throws SQLException, IOException, ClassNotFoundException {
        God selectedGod = godRepo.reRoll(previousGod);
        List<God> listOfGods = getPotentialRerollableGods();
        Boolean found = false;
        for (God god : listOfGods) {
            if (selectedGod.getGodName().equals(god.getGodName())) {
                found = true;
            }
        }
        Assert.assertTrue(found);
    }

    private List<God> getPotentialRerollableGods() {
        List<God> listOfGods = new ArrayList<>();
        listOfGods.add(new God("Agni", "magical"));
        listOfGods.add(new God("Ah Muzen Cab", "physical"));
        listOfGods.add(new God("Amaterasu", "physical"));
        listOfGods.add(new God("Ah Puch", "magical"));
        listOfGods.add(new God("Anhur", "physical"));
        listOfGods.add(new God("Anubis", "magical"));
        listOfGods.add(new God("Ao Kuang", "magical"));
        listOfGods.add(new God("Aphrodite", "magical"));
        listOfGods.add(new God("Apollo", "physical"));
        listOfGods.add(new God("Arachne", "physical"));
        listOfGods.add(new God("Ares", "magical"));
        listOfGods.add(new God("Artemis", "physical"));
        listOfGods.add(new God("Athena", "magical"));
        listOfGods.add(new God("Awilix", "physical"));
        listOfGods.add(new God("Bacchus", "magical"));
        listOfGods.add(new God("Bakasura", "physical"));
        listOfGods.add(new God("Bastet", "physical"));
        listOfGods.add(new God("Bellona", "physical"));
        listOfGods.add(new God("Cabrakan", "magical"));
        listOfGods.add(new God("Chaac", "physical"));
        listOfGods.add(new God("Change", "magical"));
        listOfGods.add(new God("Chiron", "physical"));
        listOfGods.add(new God("Chronos", "magical"));
        listOfGods.add(new God("Cupid", "physical"));
        listOfGods.add(new God("Fenrir", "physical"));
        listOfGods.add(new God("Freya", "magical"));
        listOfGods.add(new God("Geb", "magical"));
        listOfGods.add(new God("Guan Yu", "physical"));
        listOfGods.add(new God("Hades", "magical"));
        listOfGods.add(new God("He Bo", "magical"));
        listOfGods.add(new God("Hel", "magical"));
        listOfGods.add(new God("Hercules", "physical"));
        listOfGods.add(new God("Hou Yi", "physical"));
        listOfGods.add(new God("Hun Batz", "physical"));
        listOfGods.add(new God("Isis", "magical"));
        listOfGods.add(new God("Janus", "magical"));
        listOfGods.add(new God("Jing Wei", "physical"));
        listOfGods.add(new God("Kali", "physical"));
        listOfGods.add(new God("Khepri", "magical"));
        listOfGods.add(new God("Kukulkan", "magical"));
        listOfGods.add(new God("Kumbhakarna", "magical"));
        listOfGods.add(new God("Loki", "physical"));
        listOfGods.add(new God("Medusa", "physical"));
        listOfGods.add(new God("Mercury", "physical"));
        listOfGods.add(new God("Ne Zha", "physical"));
        listOfGods.add(new God("Neith", "physical"));
        listOfGods.add(new God("Nemesis", "physical"));
        listOfGods.add(new God("Nox", "magical"));
        listOfGods.add(new God("Nu Wa", "magical"));
        listOfGods.add(new God("Odin", "physical"));
        listOfGods.add(new God("Osiris", "physical"));
        listOfGods.add(new God("Poseidon", "magical"));
        listOfGods.add(new God("Ra", "magical"));
        listOfGods.add(new God("Raijin", "magical"));
        listOfGods.add(new God("Rama", "physical"));
        listOfGods.add(new God("Ratatoskr", "physical"));
        listOfGods.add(new God("Ravana", "physical"));
        listOfGods.add(new God("Scylla", "magical"));
        listOfGods.add(new God("Serqet", "physical"));
        listOfGods.add(new God("Skadi", "physical"));
        listOfGods.add(new God("Sobek", "magical"));
        listOfGods.add(new God("Sol", "magical"));
        listOfGods.add(new God("Sun Wukong", "physical"));
        listOfGods.add(new God("Susano", "physical"));
        listOfGods.add(new God("Sylvanus", "magical"));
        listOfGods.add(new God("Thor", "physical"));
        listOfGods.add(new God("Tyr", "physical"));
        listOfGods.add(new God("Ullr", "physical"));
        listOfGods.add(new God("Vamana", "physical"));
        listOfGods.add(new God("Vulcan", "magical"));
        listOfGods.add(new God("Xbalanque", "physical"));
        listOfGods.add(new God("Xing Tian", "magical"));
        listOfGods.add(new God("Ymir", "magical"));
        listOfGods.add(new God("Zeus", "magical"));
        listOfGods.add(new God("Zhong Kui", "magical"));
        return listOfGods;
    }
}
