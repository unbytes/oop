package models;

import java.util.ArrayList;

public abstract class Store {
    private static final String name = "Drogas Licitas";
    private static final String password = "d93724a2";
    private static ArrayList<Branch> branches = new ArrayList<Branch>();
    private static ArrayList<Client> clients = new ArrayList<Client>();

    public static void registerBranch(Branch branch) {
        branches.add(branch);
    }

    public static void removeBranch(Branch branch, String pass) throws Exception {
        if (password.equals(pass)) {
            if (branches.contains(branch)) {
                branches.remove(branch);
            } else {
                throw new IllegalArgumentException("Branch not found");
            }
        } else {
            throw new IllegalAccessException("You are not authenticated to remove a branch");
        }
    }

    public static ArrayList<Branch> getBranches() {
        return branches;
    }

    public static ArrayList<Branch> searchBranchesFromCity(String city) {
        String manipulatedCity = city.toLowerCase().strip();

        ArrayList<Branch> branchesFromCity = new ArrayList<Branch>();
        for (Branch branch : branches) {
            if (branch.getAddress().getCity().toLowerCase().strip().equals(manipulatedCity)) {
                branchesFromCity.add(branch);
            }
        }
        return branchesFromCity;
    }

    public static void registerClient(Client client) {
        clients.add(client);
    }

    public static void removeClient(Client client) {
        clients.remove(client);
    }

    public static ArrayList<Client> getClients() {
        return clients;
    }

    public static String getName() {
        return name;
    }
}
