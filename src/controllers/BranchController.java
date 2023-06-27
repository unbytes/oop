package controllers;

import java.util.*;
import models.Address;
import models.Branch;
import models.Cosmetic;
import models.Medicament;
import models.Product;
import models.Store;

public class BranchController {
    
    public String[] searchProductsByWordAsHTMLTemplate(String branchUUID, String word) {
        Branch branch = getBranchByUUID(branchUUID);
        ArrayList<Product> searchedProducts = branch.searchProductsByWord(word);
        if (searchedProducts == null) {
            return null;
        }
        Integer numberOfProducts = searchedProducts.size();
        String productsAsHTMLTemplate[] = new String[numberOfProducts];
        for (Integer index = 0; index < numberOfProducts; index++) {
            Product product = searchedProducts.get(index);
            String HTMLTemplate = String.format("""
                    <html>
                        <body>
                            Tipo de Produto: %s
                            <br>
                            Produto: %s
                            <br>
                            Preço: %.2f
                            <br>
                            Quantidade: %d
                            <br> 
                        </body>
                    </html>
                    """, handleProductType(product),product.getName(), product.getPrice(), branch.getProducts().get(product));
            productsAsHTMLTemplate[index] = HTMLTemplate;
        }

        return productsAsHTMLTemplate;
    }

    public String handleProductType(Product product) {
        if (product instanceof Medicament) {
            return "Medicamento";
        } else if (product instanceof Cosmetic) {
            return "Cosmético";
        }
        return null;
    }

    public void addProduct(String branchUUID, String productName, Float productPrice, Integer productQuantity, String productType) {
        Branch branch = getBranchByUUID(branchUUID);

        if (productType.equals("Medicamento")) {
            Product product = new Medicament(productName, productPrice,null,null,null);
            branch.addProduct(product, productQuantity);
        } else {
            Product product = new Cosmetic(productName, productPrice,null,null,null);
            branch.addProduct(product, productQuantity);
        }
    }

    public Branch getBranchByUUID(String branchUUID) {
        ArrayList<Branch> branches = Store.getBranches();
        Branch branch = branches.stream().filter(b -> b.getId().equals(branchUUID)).findFirst().orElse(null);
        return branch;
    }

    public void registerBranch(String password, String city, String region) {
        Address address = new Address(city, region);
        Branch branch = new Branch(password, address);
        Store.registerBranch(branch);
    }

    public boolean authenticateBranch(String branchUUID, String password) {
        Branch branch = getBranchByUUID(branchUUID);

        if (branch != null) {
            branch.login(password);
        }

        return branch.getIsAuthenticated();
    }

    public void logoutBranch(String branchUUID) {
        Branch branch = getBranchByUUID(branchUUID);

        if (branch != null) {
            branch.logout();
        }
    }

    public boolean updateBranchAddress(String branchUUID, String newCity, String newRegion) {
        Address newAddress = new Address(newCity, newRegion);
        Branch branch = getBranchByUUID(branchUUID);
        return branch.setAddress(newAddress);
    }

    public String getBranchCity(String branchUUID) {
        Branch branch = getBranchByUUID(branchUUID);
        return branch.getAddress().getCity();
    }

    public String getBranchRegion(String branchUUID) {
        Branch branch = getBranchByUUID(branchUUID);
        return branch.getAddress().getRegion();
    }
}
