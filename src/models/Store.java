package models;

import java.util.ArrayList;

/**
 * Representa a base de dados da rede de farmácia "Drogas Lícitas"
 *
 * @author Mateus, Henrique e Gabriel
 * @version 1.1
 * @since 2023
 */
public abstract class Store {
    private static final String name = "Drogas Licitas";
    private static final String password = "drogaslicitas";
    private static ArrayList<Branch> branches = new ArrayList<Branch>();
    private static ArrayList<Client> clients = new ArrayList<Client>();

    /**
     * Registra uma nova filial na base de dados
     *
     * @param branch <code>Branch</code> Filial a ser registrada
     */
    public static void registerBranch(Branch branch) {
        branches.add(branch);
    }

    /**
     * Remove uma filial da base de dados
     *
     * @param branch <code>Branch</code> Filial a ser removida
     */
    public static void removeBranch(Branch branch) {
        branches.remove(branch);
    }

    public static ArrayList<Branch> getBranches() {
        return branches;
    }

    /**
     * Busca por uma filial dada uma cidade
     *
     * @param city <code>String</code> Cidade a ser buscada
     * @return <code>ArrayList&lt;Branch&gt;</code> Lista de filiais encontradas
     */
    public static ArrayList<Branch> searchBranchesFromCity(String city) {
        String manipulatedCity = city.toLowerCase().strip();

        ArrayList<Branch> branchesFromCity = new ArrayList<Branch>();
        for (Branch branch : branches) {
            if (branch.getAddress().getCity().toLowerCase().strip().contains(manipulatedCity)) {
                branchesFromCity.add(branch);
            }
        }

        return branchesFromCity;
    }

    /**
     * Registra um novo cliente na base de dados
     *
     * @param client <code>Client</code> Cliente a ser registrado
     */
    public static void registerClient(Client client) {
        clients.add(client);
    }

    /**
     * Remove um cliente da base de dados
     *
     * @param client <code>Client</code> Cliente a ser removido
     */
    public static void removeClient(Client client) {
        clients.remove(client);
    }

    public static ArrayList<Client> getClients() {
        return clients;
    }

    public static String getName() {
        return name;
    }

    public static String getPassword() {
        return password;
    }

}
