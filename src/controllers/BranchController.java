package controllers;

import java.util.*;

import models.Address;
import models.Branch;
import models.Store;

public class BranchController {
    public HashMap<String, String> getBranchesAsHTMLTemplate() {
        HashMap<String, String> branchesAsHTMLTemplate = new HashMap<String, String>();

        ArrayList<Branch> branches = Store.getBranches();
        for (Branch branch : branches) {
            String text = String.format("""
                    <html>
                        <body>
                            UUID: %s
                            <br>
                            Endereço: %s
                        </body>
                    </html>
                    """, branch.getId(), branch.getAddress().toString());
            branchesAsHTMLTemplate.put(branch.getId(), text);
        }

        return branchesAsHTMLTemplate;
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
}
