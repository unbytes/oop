package tests;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import controllers.BranchController;
import models.Branch;
import models.Store;

public class TestBranchController {
	private BranchController branchController = new BranchController();

	@Test
	public void testCreateBranch() {
		String password = "123456";

		branchController.registerBranch(password, "Brasília", "Gama");
		branchController.registerBranch(password, "São Paulo", "Setor Bueno");

		ArrayList<Branch> branches = Store.getBranches();
		assertEquals(2, branches.size());
		assertEquals("Brasília | Gama\n", branches.get(0).getAddress().toString());
		assertEquals("São Paulo | Setor Bueno\n", branches.get(1).getAddress().toString());
	}
}
