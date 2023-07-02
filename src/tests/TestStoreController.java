package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.util.*;
import controllers.StoreController;
import models.Branch;
import models.Client;
import models.Store;

/**
 * Classe de testes para a classe StoreController.
 *
 * @author Mateus, Henrique e Gabriel
 * @version 1.0
 * @since 2023
 * @see StoreController
 * @see Store
 */
public class TestStoreController {
    private StoreController storeController = new StoreController();

    /**
     * Configuração inicial para cada teste.
     * Esse método é executado antes de cada teste e limpa as listas de filiais e
     * clientes.
     */
    @BeforeEach
    public void setUp() {
        ArrayList<Branch> branches = Store.getBranches();
        ArrayList<Branch> clients = Store.getBranches();
        branches.clear();
        clients.clear();
    }

    /**
     * Testa a remoção de um cliente cadastrado na rede de farmácias.
     * O teste verifica se os clientes foram cadastrados corretamente e se a remoção
     * foi feita com sucesso.
     *
     * @see StoreController#createClient(String, int, String)
     * @see StoreController#removeClient(String)
     */
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
        String expectedNames[] = { "Henrique", "Gabriel" };
        for (int i = 0; i < clients.size(); i++) {
            names[i] = clients.get(i).getName();
        }
        assertArrayEquals(expectedNames, names);
    }
}
