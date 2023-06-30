package tests;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import controllers.StoreController;
import models.Branch;
import models.Client;
import models.Store;

public class TestStoreController {
    private StoreController storeController = new StoreController();

    @Before
    public void setUp() {
        ArrayList<Branch> branches = Store.getBranches();
        ArrayList<Branch> clients = Store.getBranches();
        branches.clear();
        clients.clear();
    }

    @Test
    public void testDeleteClient() {
        storeController.createClient("Mateus", 19, "892.337.491-18");
        storeController.createClient("Henrique", 21, "307.545.071-57");
        storeController.createClient("Gabriel", 20, "722.623.841-18");

        ArrayList<Client> clients = Store.getClients();
        assertEquals(3, clients.size());

        storeController.removeClient("892.337.491-18");
        assertEquals(2, clients.size());

        String names[] = new String[2];
        String expectedNames[] = {"Henrique", "Gabriel"};
        for (int i = 0; i < clients.size(); i++) {
            names[i] = clients.get(i).getName();
        }
        assertArrayEquals(expectedNames, names);
    }
}
