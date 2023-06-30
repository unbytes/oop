package controllers;

import java.util.*;
import models.Client;
import models.Branch;
import models.Store;

/**
 * Manipula os dados presentes na rede de farmácia para serem
 * utilizados nas views de maneira mais fácil de apresentar
 *
 * @autor Mateus, Henrique e Gabriel
 * @version 1.1
 * @since 2023
 */
public class StoreController {

    /**
     * Cria um template em HTML com os detalhes de todas as filiais
     * 
     * @return <code>String[]</code>
     */
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

    /**
     * Cria um template em HTML com os detalhes de todos os clientes
     * 
     * @return <code>String[]</code>
     */
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

    /**
     * Busca por um cliente dado seu CPF
     * 
     * @param CPF
     * @return <code>Client</code> cliente encontrado
     */
    public Client getClientByCPF(String CPF) {
        Client client = Store.getClients().stream().filter(c -> c.getCPF().equals(CPF)).findFirst().orElse(null);
        return client;
    }

    
    /** 
     * Faz login na rede de farmácias pela senha informada
     * 
     * @param password
     * @return <code>boolean</code> informa se foi possível logar na rede
     */
    public boolean authenticateStore(String password) {
        if (password.equals(Store.getPassword())) {
            return true;
        } else
            return false;
    }

    public String[] searchBranchesByCity(String city) {
        ArrayList<Branch> branchesByCity = Store.searchBranchesFromCity(city);

        Integer numberOfBranches = branchesByCity.size();
        String branchesAsHTMLTemplate[] = new String[numberOfBranches];
        
        for (Integer index = 0; index < numberOfBranches; index++) {
            Branch branch = branchesByCity.get(index);
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

    /**
     * Remove uma filial a partir do UUID informada
     * 
     * @param branchUUID
     */
    public void removeBranch(String branchUUID) {
        Branch branch = Store.getBranches().stream().filter(b -> b.getId().equals(branchUUID)).findFirst().orElse(null);
        Store.removeBranch(branch);
    }
                                                
    /**
     * Remove um cliente a partir do seu CPF
     * 
     * @param CPF
     */
    public void removeClient(String CPF) {
        Client client = getClientByCPF(CPF);
        Store.removeClient(client);
    }

    /**
     * Cria um cliente a partir das informações dadas
     * 
     * @param name
     * @param age
     * @param CPF
     */
    public void createClient(String name, Integer age, String CPF) {
        Client client = new Client(name, CPF, age);
        Store.registerClient(client);
    }

    /**
     * Busca um cliente específico a partir de um CPF
     * 
     * @param CPF
     * @return <code>String</code> Nome do cliente encontrado
     */
    public String getClientName(String CPF) {
        Client client = getClientByCPF(CPF);
        return client.getName();
    }
}
