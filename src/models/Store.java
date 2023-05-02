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

    public static void listAllBranches() {
        System.out.println("Branches:");
        for (Branch branch : branches) {
            System.out.print("    " + branch.toString());
        }
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

    public static void listBranchesFromCity(String city) {
        System.out.println("Branches from " + city.strip() + ":");
        for (Branch branch : searchBranchesFromCity(city)) {
            System.out.print("    " + branch.toString());
        }
    }

    public static void registerClient(Client client) {
        clients.add(client);
    }

    public static void removeClient(Client client) {
        clients.remove(client);
    }

    public static void listAllClients() {
        System.out.println("Clients:");
        for (Client client : clients) {
            System.out.print(client.toString());
        }
    }

    public static String getName() {
        return name;
    }
}
