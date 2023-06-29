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

public class TestBranchController {
	private final String password = "12345";
	private BranchController branchController = new BranchController();

	@Before
	public void setUp() {
		ArrayList<Branch> branches = Store.getBranches();
		ArrayList<Branch> clients = Store.getBranches();
		branches.clear();
		clients.clear();
	}

	@Test
	public void testCreateBranch() {
		branchController.registerBranch(password, "Brasília", "Gama");
		branchController.registerBranch(password, "São Paulo", "Setor Bueno");

		ArrayList<Branch> branches = Store.getBranches();
		assertEquals(2, branches.size());
		assertEquals("Brasília | Gama\n", branches.get(0).getAddress().toString());
		assertEquals("São Paulo | Setor Bueno\n", branches.get(1).getAddress().toString());
	}

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
