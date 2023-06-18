import java.util.Arrays;

import models.Address;
import models.Branch;
import models.Client;
import models.Cosmetic;
import models.Medicament;
import models.Store;
import views.Home;

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
        for (Branch branch : Arrays.asList(branch1, branch2)) {
            Store.registerBranch(branch);
        }

        for (Client client : Arrays.asList(gabriel, henrique, mateus)) {
            Store.registerClient(client);
        }

        branch1.login(password);
        branch1.addProduct(dipirona);
        branch1.addProduct(ipubrofeno);
        branch1.logout();

        branch2.login(password);
        branch2.addProduct(originalCare);
        branch2.logout();

        new Home();
    }
}
