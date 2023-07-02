package tests;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import controllers.BranchController;
import models.Address;
import models.Branch;
import models.Cosmetic;
import models.Medicament;
import models.Store;

/**
 * Classe de testes para a classe BranchController.
 *
 * @author Mateus, Henrique e Gabriel
 * @version 1.0
 * @since 2023
 * @see BranchController
 * @see Branch
 */
public class TestBranchController {
	private final String password = "12345";
	private BranchController branchController = new BranchController();

	/**
	 * Configuração inicial para cada teste.
	 *
	 * Esse método é executado antes de cada teste e limpa as listas de filiais e
	 * clientes.
	 */
	@Before
	public void setUp() {
		ArrayList<Branch> branches = Store.getBranches();
		ArrayList<Branch> clients = Store.getBranches();
		branches.clear();
		clients.clear();
	}

	/**
	 * Testa o cadastro de uma filial na rede de farmácias.
	 * <p>
	 * Este teste verifica se o método <code>registerBranch()</code>
	 * cadastrou as filiais corretamente.
	 *
	 * @see BranchController#registerBranch(String, String, String)
	 */
	@Test
	public void testCreateBranch() {
		branchController.registerBranch(password, "Brasília", "Gama");
		branchController.registerBranch(password, "São Paulo", "Setor Bueno");

		ArrayList<Branch> branches = Store.getBranches();
		assertEquals(2, branches.size());
		assertEquals("Brasília | Gama\n", branches.get(0).getAddress().toString());
		assertEquals("São Paulo | Setor Bueno\n", branches.get(1).getAddress().toString());
	}

	/**
	 * Testa a busca de um produto por expressão existente em seu nome.
	 * <p>
	 * Este teste verifica se o método
	 * <code>searchProductsByWordAsHTMLTemplate()</code>
	 * retorna corretamente um array de representações HTML dos produtos
	 * encontrados.
	 *
	 * @see BranchController#searchProductsByWordAsHTMLTemplate(int, String)
	 */
	@Test
	public void testSearchProductByWordAsHTMLTemplate() {
		Address address1 = new Address("São Paulo", "Pinheiros");
		Branch branch1 = new Branch(password, address1);
		Store.registerBranch(branch1);

		Medicament dipirona = new Medicament("Dipirona", 21, "Vermelha", 500, 15);
		Cosmetic shampoo = new Cosmetic("Shampoo", 210, "Kérastase", "Cabelo", false);

		branch1.login(password);
		branch1.addProduct(dipirona, 1);
		branch1.addProduct(shampoo, 5);
		branch1.logout();

		String[] products = branchController.searchProductsByWordAsHTMLTemplate(branch1.getId(), "Dipirona");
		assertEquals(1, products.length);

		products = branchController.searchProductsByWordAsHTMLTemplate(branch1.getId(), "");
		assertEquals("""
				<html>
				    <body>
				        Tipo de Produto: Medicamento
				        <br>
				        Produto: Dipirona
				        <br>
				        Preço: 21
				        <br>
				        Quantidade: 1
				        <br>
				        Cor da Caixa: Vermelha
				        <br>
				        Dosagem (mg): 500
				        <br>
				        Idade Mínima: 15
				        <br> 
				    </body>
				</html>
				""", products[0]);
		assertEquals("""
				<html>
				    <body>
				        Tipo de Produto: Cosmético
				        <br>
				        Produto: Shampoo
				        <br>
				        Preço: 210
				        <br>
				        Quantidade: 5
				        <br>
				        Marca: Kérastase
				        <br>
				        Tipo: Cabelo
				        <br>
				        Proteção Solar: Não
				        <br> 
				    </body>
				</html>
				""", products[1]);

		products = branchController.searchProductsByWordAsHTMLTemplate(branch1.getId(), "Wrong");
		assertNull(products);
	}
}
