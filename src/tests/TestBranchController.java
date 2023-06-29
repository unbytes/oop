package tests;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import controllers.BranchController;
import models.Address;
import models.Branch;
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

		Medicament dipirona = new Medicament("Dipirona", 21, "", 500, 15);

		branch1.login(password);
		branch1.addProduct(dipirona, 1);
		branch1.logout();

		String[] products = branchController.searchProductsByWordAsHTMLTemplate(branch1.getId(), "Dipirona");
		assertEquals(1, products.length);

		products = branchController.searchProductsByWordAsHTMLTemplate(branch1.getId(), "Wrong");
		assertNull(products);
	}
}
