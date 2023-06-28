package controllers;

import java.util.*;
import models.Client;
import models.Branch;
import models.Store;

public class StoreController {

    public String[] getBranchesAsHTMLTemplate() {
        ArrayList<Branch> branches = Store.getBranches();
        Integer numberOfBranches = branches.size();

        String branchesAsHTMLTemplate[] = new String[numberOfBranches];
        for (Integer index = 0; index < numberOfBranches; index++) {
            Branch branch = branches.get(index);
            String HTMLTemplate = String.format("""
                    <html>
                        <body>
                            UUID: %s
                            <br>
                            Endereço: %s
                        </body>
                    </html>
                    """, branch.getId(), branch.getAddress().toString());
            branchesAsHTMLTemplate[index] = HTMLTemplate;
        }

        return branchesAsHTMLTemplate;
    }

    public String[] getClientesAsHTMLTemplate() {
        ArrayList<Client> clients = Store.getClients();
        Integer numberOfClients = clients.size();

        String clientsAsHTMLTemplate[] = new String[numberOfClients];
        for (Integer index = 0; index < numberOfClients; index++) {
            Client client = clients.get(index);
            String HTMLTemplate = String.format("""
                    <html>
                        <body>
                            CPF: %s
                            <br>
                            Nome: %s
                        </body>
                    </html>
                    """, client.getCPF(), client.getName());
            clientsAsHTMLTemplate[index] = HTMLTemplate;
        }

        return clientsAsHTMLTemplate;
    }

    public Client getClientByCPF(String CPF) {
        Client client = Store.getClients().stream().filter(c -> c.getCPF().equals(CPF)).findFirst().orElse(null);
        return client;
    }

    public boolean authenticateStore(String password) {
        if (password.equals(Store.getpassword())) {
            return true;
        } else
            return false;
    }

    public void removeBranch(String branchUUID) {
        Branch branch = Store.getBranches().stream().filter(b -> b.getId().equals(branchUUID)).findFirst().orElse(null);
        Store.removeBranch(branch);
    }

    public void removeClient(String CPF) {
        Client client = getClientByCPF(CPF);
        Store.removeClient(client);
    }

    public void createClient(String name, Integer age, String CPF) {
        Client client = new Client(name, CPF, age);
        Store.registerClient(client);
    }

    public String getClientName(String CPF) {
        Client client = getClientByCPF(CPF);
        return client.getName();
    }
}
