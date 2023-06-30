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
    static Address address4 = new Address("São Paulo", "Vila Madalena");
    static Address address5 = new Address("São Paulo", "Santana");
    static Address address6 = new Address("Rio de Janeiro", "Benfica");
    static Address address7 = new Address("Brasília", "Paranoá");

    static Branch branch1 = new Branch(password, address1);
    static Branch branch2 = new Branch(password, address2);
    static Branch branch3 = new Branch(password, address3);
    static Branch branch4 = new Branch(password, address4);
    static Branch branch5 = new Branch(password, address5);
    static Branch branch6 = new Branch(password, address6);
    static Branch branch7 = new Branch(password, address7);

    static Client gabriel = new Client("Gabriel", "405.765.230-20", 20);
    static Client henrique = new Client("Henrique", "151.029.870-38", 20);
    static Client mateus = new Client("Mateus", "138.890.550-79", 18);
    static Client bagriel = new Client("Bagriel", "087.632.109-23", 99);
    static Client matue = new Client("Matuê", "456.123.190-52",29);

    static Medicament dipirona = new Medicament("Dipirona", 21, "Amarela", 500, 15);
    static Medicament ipubrofeno = new Medicament("Ipubrofeno", 28, "Amarela", 100, 12);
    static Medicament paracetamol = new Medicament("Paracetamol", 15, "Sem cor", 100, 10);
    static Medicament dorflex = new Medicament("Dorflex", 30, "Sem cor", 100, 18);
    static Medicament doril = new Medicament("Doril", 10, "Amarela", 100, 15);
    static Cosmetic originalCare = new Cosmetic("Hidratante Labial Original Care", 17, "Nivea", "Lábios", false);
    static Cosmetic morangoShine = new Cosmetic("Hidratante Labial Morango Shine", 17, "Nivea", "Lábios", false);
    static Cosmetic melanciaShine = new Cosmetic("Hidratante Labial Melancia Shine", 17, "Nivea", "Lábios", false);
    static Cosmetic puroShine = new Cosmetic("Hidratante Labial Puro Shine", 17, "Nivea", "Lábios", false);
    static Cosmetic lipBalm = new Cosmetic("Lip Balm", 10, "Nivea", "Lábios", true);

    public static void main(String[] args) throws Exception {
        for (Branch branch : Arrays.asList(branch1, branch2, branch3, branch4, branch5, branch6, branch7)) {
            Store.registerBranch(branch);
        }

        for (Client client : Arrays.asList(gabriel, henrique, mateus, bagriel, matue)) {
            Store.registerClient(client);
        }

        branch1.login(password);
        branch1.addProduct(dipirona, 5);
        branch1.addProduct(ipubrofeno,6);
        branch1.addProduct(paracetamol, 2);
        branch1.addProduct(dorflex, 3);
        branch1.buyProduct(gabriel, dipirona);
        branch1.buyProduct(gabriel, ipubrofeno);
        branch1.buyProduct(henrique, paracetamol);
        branch1.buyProduct(mateus, dorflex);
        branch1.logout();

        branch2.login(password);
        branch2.addProduct(originalCare,1);
        branch2.addProduct(morangoShine, 1);
        branch2.addProduct(melanciaShine, 6);
        branch2.addProduct(puroShine, 3);
        branch2.logout();

        branch3.login(password);
        branch3.addProduct(doril, 1);
        branch3.addProduct(lipBalm, 1);
        branch3.logout();

        branch4.login(password);
        branch4.addProduct(dipirona, 4);
        branch4.addProduct(ipubrofeno, 6);
        branch4.addProduct(paracetamol, 7);
        branch4.addProduct(dorflex, 2);
        branch4.addProduct(doril, 60);
        branch4.addProduct(originalCare, 45);
        branch4.addProduct(morangoShine, 23);
        branch4.addProduct(melanciaShine, 10);
        branch4.addProduct(puroShine, 2);
        branch4.addProduct(lipBalm, 90);
        branch4.logout();

        branch5.login(password);
        branch5.addProduct(dipirona, 7);
        branch5.addProduct(ipubrofeno, 1);
        branch5.addProduct(paracetamol, 5);
        branch5.addProduct(dorflex, 3);
        branch5.addProduct(doril, 1);
        branch5.addProduct(originalCare, 8);
        branch5.addProduct(morangoShine, 1);
        branch5.logout();

        branch6.login(password);
        branch6.addProduct(dipirona, 9);
        branch6.addProduct(ipubrofeno, 34);
        branch6.addProduct(paracetamol, 1);
        branch6.addProduct(dorflex, 45);
        branch6.addProduct(doril, 2);
        branch6.addProduct(originalCare, 7);
        branch6.addProduct(morangoShine, 1);
        branch6.addProduct(melanciaShine, 1);
        branch6.addProduct(puroShine, 1);
        branch6.addProduct(lipBalm, 92);
        branch6.buyProduct(bagriel, dipirona);
        branch6.buyProduct(matue, dipirona);
        branch6.buyProduct(matue, dorflex);
        branch6.logout();

        branch7.login(password);
        branch7.addProduct(dipirona, 15);
        branch7.addProduct(ipubrofeno, 9);
        branch7.addProduct(paracetamol, 8);
        branch7.addProduct(morangoShine, 23);
        branch7.addProduct(melanciaShine, 59);
        branch7.logout();

        new Home();
    }
}
