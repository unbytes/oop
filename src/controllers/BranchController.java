package controllers;

import java.util.*;
import models.Address;
import models.Branch;
import models.Cosmetic;
import models.Medicament;
import models.Product;
import models.Store;

/**
 * Manipula os dados presentes nas filiais para serem utilizados
 * nas views de maneira mais fácil de apresentar
 *
 * @autor Mateus, Henrique e Gabriel
 * @version 1.1
 * @since 2023
 */
public class BranchController {
    /**
     * Cria um template em HTML com os detalhes de um
     * produto do tipo medicamento
     * 
     * @param product
     * @param branch
     * @return <code>String</code> Template HTML de um Medicamento
     */
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
                ((Medicament) product).getDosageMg(),
                ((Medicament) product).getMinimumAge());
        return HTMLTemplate;
    }

    /**
     * Cria um template em HTML com os detalhes de um
     * produto do tipo cosmético
     * 
     * @param product
     * @param branch
     * @return <code>String</code> Template HTML de um Cosmético
     */
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

    /**
     * Busca produtos por um expressão existente em seu nome 
     * 
     * @param branchUUID
     * @param word
     * @return <code>String[]</code> Array de strings com os produtos encontrados
     */
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

    /**
     * Identifica o tipo de um produto "Medicamento" ou "Cosmético"
     * 
     * @param product
     * @return <code>String</code> Tipo do produto traduzido em um string
     */
    public String handleProductType(Product product) {
        if (product instanceof Medicament) {
            return "Medicamento";
        } else if (product instanceof Cosmetic) {
            return "Cosmético";
        }
        return null;
    }

    /**
     * Adiciona um novo medicamento em uma filial específica
     * 
     * @param branchUUID
     * @param productName
     * @param productPrice
     * @param productQuantity
     * @param boxColor
     * @param dosageMg
     * @param minimumAge
     */
    public void addMedicament(String branchUUID, String productName, Integer productPrice, Integer productQuantity, String boxColor, Integer dosageMg, Integer minimumAge) {
        Branch branch = getBranchByUUID(branchUUID);

        Product product = new Medicament(productName, productPrice, boxColor, dosageMg, minimumAge);
        branch.addProduct(product, productQuantity);
    }

    /**
     * Adiciona um novo cosmético em uma filial específica
     * 
     * @param branchUUID
     * @param productName
     * @param productPrice
     * @param productQuantity
     * @param brand
     * @param type
     * @param uv
     */
    public void addCosmetic(String branchUUID, String productName, Integer productPrice, Integer productQuantity, String brand, String type, Boolean uv) {
        Branch branch = getBranchByUUID(branchUUID);

        Product product = new Cosmetic(productName, productPrice, brand, type, uv);
        branch.addProduct(product, productQuantity);
    }

    /**
     * Busca por um produto específico pelo seu nome
     * 
     * @param branchUUID
     * @param productName
     * @return <code>Product</code> produto encontrado
     */
    public Product getProductByName(String branchUUID, String productName) {
        Branch branch = getBranchByUUID(branchUUID);
        Product product = branch.searchProductByName(productName);
        return product;
    }

    /**
     * Busca a quantidade de um produto recebido, em uma filial específica
     * 
     * @param branchUUID
     * @param product
     * @return <code>Integer</code> quantidade do produto recebido
     */
    public Integer getProductQuantity(String branchUUID, Product product) {
        Branch branch = getBranchByUUID(branchUUID);
        return branch.getProducts().get(product);
    }

    /**
     * Busca por uma filial dado seu UUID
     * 
     * @param branchUUID
     * @return <code>Branch</code> Filial encontrada pelo UUID
     */
    public Branch getBranchByUUID(String branchUUID) {
        ArrayList<Branch> branches = Store.getBranches();
        Branch branch = branches.stream().filter(b -> b.getId().equals(branchUUID)).findFirst().orElse(null);
        return branch;
    }

    /**
     * Registra um nova filial dada a senha geral da rede,
     * a cidade e a região
     * 
     * @param password
     * @param city
     * @param region
     */
    public void registerBranch(String password, String city, String region) {
        Address address = new Address(city, region);
        Branch branch = new Branch(password, address);
        Store.registerBranch(branch);
    }

    /**
     * Tenta fazer o login de uma filial dado seu UUID e senha
     * 
     * @param branchUUID
     * @param password
     * @return <code>boolean</code> informa se foi possível ou não
     * a autenticação de uma filial
     */
    public boolean authenticateBranch(String branchUUID, String password) {
        Branch branch = getBranchByUUID(branchUUID);

        if (branch != null) {
            branch.login(password);
        }

        return branch.getIsAuthenticated();
    }
    
    /**
     * Faz o logout de uma filial dado seu UUID
     * 
     * @param branchUUID
     */
    public void logoutBranch(String branchUUID) {
        Branch branch = getBranchByUUID(branchUUID);

        if (branch != null) {
            branch.logout();
        }
    }

    /**
     * Tenta atualizar o endereço de uma filial dado seu UUID, cidade e região
     * 
     * @param branchUUID
     * @param newCity
     * @param newRegion
     * @return <code>boolean</code> informa se o update do
     * endereço foi possível ou não
     */
    public boolean updateBranchAddress(String branchUUID, String newCity, String newRegion) {
        Address newAddress = new Address(newCity, newRegion);
        Branch branch = getBranchByUUID(branchUUID);
        return branch.setAddress(newAddress);
    }

    /**
     * Informa a cidade de uma filial dado seu UUID
     * 
     * @param branchUUID
     * @return <code>String</code> Cidade da filial encontrada
     */
    public String getBranchCity(String branchUUID) {
        Branch branch = getBranchByUUID(branchUUID);
        return branch.getAddress().getCity();
    }

    /**
     * Informa a região da filial dado seu UUID
     * 
     * @param branchUUID
     * @return <code>String</code> Região da filial encontrada
     */
    public String getBranchRegion(String branchUUID) {
        Branch branch = getBranchByUUID(branchUUID);
        return branch.getAddress().getRegion();
    }

    /**
     * Atualiza todas as informações de um certo produto
     * dado seu nome
     * 
     * @param branchUUID
     * @param productName
     * @param productType
     * @param productData
     */
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
    
    /**
     * Remove um produto de uma filial específica dado
     * seu UUID a partir do nome de tal
     * 
     * @param branchUUID
     * @param productName
     */
    public void removeProduct(String branchUUID, String productName) {
        Branch branch = getBranchByUUID(branchUUID);
        Product product = getProductByName(branchUUID, productName);
        branch.removeProduct(product);
    }

    /**
     * Remove uma quantidade específica de um produto
     * dado o UUID da filial, o nome de tal e a quantidade
     * 
     * @param branchUUID
     * @param productName
     * @param quantity
     */
    public void removeProduct(String branchUUID, String productName, Integer quantity) {
        Branch branch = getBranchByUUID(branchUUID);
        Product product = getProductByName(branchUUID, productName);
        branch.removeProduct(product, quantity);
    }
}
