package controllers;

import java.util.*;
import models.Address;
import models.Branch;
import models.Cosmetic;
import models.Medicament;
import models.Product;
import models.Store;

public class BranchController {

    public String generateMedicamentHTMLTemplate(Product product, Branch branch) {
        String HTMLTemplate = String.format("""
                <html>
                    <body>
                        Tipo de Produto: %s
                        <br>
                        Produto: %s
                        <br>
                        Preço: %d
                        <br>
                        Quantidade: %d
                        <br>
                        Cor da Caixa: %s
                        <br>
                        Dosagem (mg): %d
                        <br>
                        Idade Mínima: %d
                        <br> 
                    </body>
                </html>
                """, handleProductType(product),
                product.getName(), product.getPrice(),
                branch.getProducts().get(product),
                ((Medicament) product).getBoxColor(),
                ((Medicament) product).getDosageMl(),
                ((Medicament) product).getMinimumAge());
        return HTMLTemplate;
    }

    public String generateCosmeticHTMLTemplate(Product product, Branch branch) {
        String HTMLTemplate = String.format("""
                <html>
                    <body>
                        Tipo de Produto: %s
                        <br>
                        Produto: %s
                        <br>
                        Preço: %d
                        <br>
                        Quantidade: %d
                        <br>
                        Marca: %s
                        <br>
                        Tipo: %s
                        <br>
                        Proteção Solar: %s
                        <br> 
                    </body>
                </html>
                """, handleProductType(product),
                product.getName(), product.getPrice(),
                branch.getProducts().get(product),
                ((Cosmetic) product).getBrand(),
                ((Cosmetic) product).getType(),
                ((Cosmetic) product).getContainsSunProtectionFactor() ? "Sim" : "Não");
        return HTMLTemplate;
    }

    public String[] searchProductsByWordAsHTMLTemplate(String branchUUID, String word) {
        Branch branch = getBranchByUUID(branchUUID);
        ArrayList<Product> searchedProducts = branch.searchProductsByWord(word);
        if (searchedProducts == null) {
            return null;
        }
        Integer numberOfProducts = searchedProducts.size();
        String productsAsHTMLTemplate[] = new String[numberOfProducts];
        for (Integer index = 0; index < numberOfProducts; index++) {
            String HTMLTemplate;
            Product product = searchedProducts.get(index);
            if (handleProductType(product) == "Medicamento") {
                HTMLTemplate = generateMedicamentHTMLTemplate(product, branch);
            } else {
                HTMLTemplate = generateCosmeticHTMLTemplate(product, branch);
            }
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

    public void addProduct(String branchUUID, String productName, Integer productPrice, Integer productQuantity,
            String productType) {
        Branch branch = getBranchByUUID(branchUUID);

        if (productType.equals("Medicamento")) {
            Product product = new Medicament(productName, productPrice, null, null, null);
            branch.addProduct(product, productQuantity);
        } else {
            Product product = new Cosmetic(productName, productPrice, null, null, null);
            branch.addProduct(product, productQuantity);
        }
    }

    public Product getProductByName(String branchUUID, String productName) {
        Branch branch = getBranchByUUID(branchUUID);
        Product product = branch.searchProductByName(productName);
        return product;
    }

    public Integer getProductQuantity(String branchUUID, Product product) {
        Branch branch = getBranchByUUID(branchUUID);
        return branch.getProducts().get(product);
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

    public void updateProduct(String branchUUID, String productName, String productType,
            LinkedHashMap<String, String> productData) {
        Branch branch = getBranchByUUID(branchUUID);
        Product product = getProductByName(branchUUID, productName);

        String newProductName = productData.get("Nome");
        Integer price = Integer.parseInt(productData.get("Preço"));
        Integer quantity = Integer.parseInt(productData.get("Quantidade"));
        if (productType.equals("Medicamento")) {
            String boxColor = productData.get("Cor da Caixa");
            Integer dosageMl = Integer.parseInt(productData.get("Dosagem (mg)"));
            Integer minimumAge = Integer.parseInt(productData.get("Idade Mínima"));
            Medicament medicament = new Medicament(newProductName, price, boxColor, dosageMl, minimumAge);
            branch.addProduct(medicament, quantity);
        } else {
            String brand = productData.get("Marca");
            String type = productData.get("Tipo");
            Boolean UV = Boolean.parseBoolean(productData.get("UV"));
            Cosmetic cosmetic = new Cosmetic(newProductName, price, brand, type, UV);
            branch.addProduct(cosmetic, quantity);
        }
        branch.removeProduct(product);
    }

    public void removeProduct(String branchUUID, String productName) {
        Branch branch = getBranchByUUID(branchUUID);
        Product product = getProductByName(branchUUID, productName);
        branch.removeProduct(product);
    }

    public void removeProduct(String branchUUID, String productName, Integer quantity) {
        Branch branch = getBranchByUUID(branchUUID);
        Product product = getProductByName(branchUUID, productName);
        branch.removeProduct(product, quantity);
    }
}
