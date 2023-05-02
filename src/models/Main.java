package models;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static final String password = "12345";

    static Address address1 = new Address("São Paulo", "Pinheiros");
    static Address address2 = new Address("Brasília", "Park Way");
    static Address address3 = new Address("Brasília", "Taguatinga");

    static Branch branch1 = new Branch(password, address1);
    static Branch branch2 = new Branch(password, address2);
    static Branch branch3 = new Branch(password, address3);

    static Client gabriel = new Client("Gabriel", "405.765.230-20", 20);
    static Client henrique = new Client("Henrique", "151.029.870-38", 20);
    static Client mateus = new Client("Mateus", "138.890.550-79", 18);

    static Medicament dipirona = new Medicament("Dipirona", 21.35f, "", 500, 15);
    static Medicament ipubrofeno = new Medicament("Ipubrofeno", 28.67f, "", 100, 12);
    static Cosmetic originalCare = new Cosmetic("Hidratante Labial Original Care", 17.39f, "Nivea", "Lábios", false);

    public static void main(String[] args) throws Exception {
        System.out.println("--------------------------------------------------");
        System.out.println("BRANCHES AND CLIENTS:\n");

        for (Branch branch : Arrays.asList(branch1, branch2)) Store.registerBranch(branch);
        Store.listAllBranches();

        System.out.println("");

        for (Client client : Arrays.asList(gabriel, henrique, mateus)) Store.registerClient(client);
        Store.listAllClients();
        System.out.println("\n--------------------------------------------------");
 
        branch1.login(password);
        branch1.addProduct(dipirona);

        System.out.println("AUTH ERROR AND PRODUCT LIST:\n");
        try {
            branch2.addProduct(ipubrofeno);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("");
        branch1.listAllProducts();
        System.out.println("");
        branch2.listAllProducts();
        System.out.println("\n--------------------------------------------------");

        System.out.println("PRODUCT NOT FOUND, AUTH ERROR AND PRODUCT LIST:\n");
        try {
            branch1.removeProduct(ipubrofeno, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n");
        }

        try {
            branch1.logout();
            branch1.removeProduct(dipirona, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        branch1.listAllProducts();
        System.out.println("");
        branch2.listAllProducts();
        System.out.println("\n--------------------------------------------------");

        System.out.println("PRODUCTS REMOVED, LISTED AND ADDED:\n");
        branch1.login(password);
        branch1.removeProduct(dipirona, 1);

        branch1.listAllProducts();
        System.out.println("");
        branch2.listAllProducts();
        System.out.println("");

        branch1.addProduct(dipirona);
        branch1.addProduct(originalCare);
        branch1.listAllProducts();
        System.out.println("\n--------------------------------------------------");

        Product dipironaProduct = branch1.searchProductByName("Dipirona");
        ArrayList<Product> returnedProducts = branch1.searchProductsByWord("na");

        assert dipironaProduct == dipirona : "The product should be Dipirona";
        assert returnedProducts.size() == 2 : "The ArrayList should have 2 products";

        System.out.println("REMOVE SOME CLIENTS:\n");
        Store.removeClient(gabriel);
        Store.removeClient(henrique);
        Store.listAllClients();
        System.out.println("\n--------------------------------------------------");

        System.out.println("BUY PRODUCT, OUT OF STOCK AND AUTH ERROR:\n");
        branch1.buyProduct(mateus, dipirona);
        mateus.listPurchasedProducts();
        System.out.println("");
        branch1.listAllProducts();

        try {
            branch1.buyProduct(mateus, dipirona);
        } catch (Exception e) {
            System.out.println("\n" + e.getMessage());
        }

        try {
            branch1.logout();
            branch1.buyProduct(mateus, dipirona);
        } catch (Exception e) {
            System.out.println("\n" + e.getMessage());
        }
        System.out.println("\n--------------------------------------------------");

        System.out.println("REMOVE BRANCH AND AUTH ERROR:");
        try {
            Store.removeBranch(branch1, "wrong_password");
        } catch (Exception e) {
            System.out.println("\n" + e.getMessage());
        }

        Store.removeBranch(branch1, "d93724a2");
        System.out.println("");
        Store.listAllBranches();
        System.out.println("\n--------------------------------------------------");

        System.out.println("SEARCH BRANCH FROM CITY:\n");
        Store.registerBranch(branch1);
        Store.registerBranch(branch3);
        Store.listAllBranches();
        System.out.println("");
        Store.listBranchesFromCity("Brasília");
        System.out.println("\n--------------------------------------------------");
    }
}
