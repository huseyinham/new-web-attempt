package smiteTroll.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import smiteTroll.classes.God;
import smiteTroll.classes.Item;
import smiteTroll.classes.Relic;
import smiteTroll.repositories.GodRepository;
import smiteTroll.repositories.ItemRepository;
import smiteTroll.repositories.RelicRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class IndexControllerTest {

    private Model model;
    private HttpSession session;
    private IndexController indexController;

    private GodRepository godRepository = mock(GodRepository.class);
    private ItemRepository itemRepository = mock(ItemRepository.class);
    private RelicRepository relicRepository = mock(RelicRepository.class);

    private God god = new God("Name", "Type", "");
    List<Item> items = new ArrayList<>();
    List<Relic> relics = new ArrayList<>();

    private final static Item ITEM_ONE = new Item("itemOne", "type", "");
    private final static Item ITEM_TWO = new Item("itemTwo", "type", "");
    private final static Item ITEM_THREE = new Item("itemThree", "type", "");
    private final static Item ITEM_FOUR = new Item("itemFour", "type", "");
    private final static Item ITEM_FIVE = new Item("itemFive", "type", "");
    private final static Item ITEM_SIX = new Item("itemSix", "type", "");

    private final static Relic RELIC_ONE = new Relic("relicOne", "");
    private final static Relic RELIC_TWO = new Relic("relicTwo", "");

    @Before
    public void setup() {
        model = new ExtendedModelMap();
        session = new MockHttpSession();
        indexController = new IndexController();
        items.add(ITEM_ONE);
        items.add(ITEM_TWO);
        items.add(ITEM_THREE);
        items.add(ITEM_FOUR);
        items.add(ITEM_FIVE);
        items.add(ITEM_SIX);

        relics.add(RELIC_ONE);
        relics.add(RELIC_TWO);

        indexController.godRepository = godRepository;
        indexController.itemRepository = itemRepository;
        indexController.relicRepository = relicRepository;

        given(godRepository.getNewGod()).willReturn(god);
        given(itemRepository.getItems(god)).willReturn(items);
        given(relicRepository.getRelics()).willReturn(relics);
        /*given(itemRepository.checkForSpecificItemsForGods(eq(god), anyListOf(Item.class)))
                .willReturn(Arrays.asList(new Item("Item1", "ItemType", ""), new Item("Item2", "ItemType", "")));*/
    }

    @Test
    public void testIndexIsReturnedWhenIndexUrlIsHit() {
        String view = indexController.handleIndex(model, session);
        assertEquals(view, "index");
    }

    @Test
    public void testRerollAmountIsThreeWhenAtIndex() {
        indexController.handleIndex(model, session);
        int result = (int) session.getAttribute("rerollAmount");
        assertEquals(3, result);
    }

    @Test
    public void testGodAttributesHaveBeenAddedToModelOnIndex() {
        indexController.handleIndex(model, session);
        assertEquals("Name", model.asMap().get("godName"));
        assertEquals("", model.asMap().get("godImage"));
    }

    @Test
    public void testItemAttributesHaveBeenAddedToModelOnIndex() {
        indexController.handleIndex(model, session);
        assertEquals(items, model.asMap().get("items"));
        assertEquals("", model.asMap().get("itemImage"));
    }

    @Test
    public void testRelicAttributesHaveBeenAddedToModelOnIndex() {
        indexController.handleIndex(model, session);
        assertEquals(relics, model.asMap().get("relics"));
    }



}

