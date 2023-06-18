package controllers;

import java.util.*;

import models.Address;
import models.Branch;
import models.Store;

public class BranchController {
    public String[] getBranchesAsHTMLTemplate() {
        ArrayList<Branch> branches = Store.getBranches();
        Integer numberOfBranches = Store.getBranches().size();

        String branchesAsHTMLTemplate[] = new String[numberOfBranches];
        for (Integer index = 0; index < numberOfBranches; index++) {
            Branch branch = branches.get(index);
            String HTMLTemplate = String.format("""
                    <html>
                        <body>
                            UUID: %s
                            <br>
                            Endereço: %s
                        </body>
                    </html>
                    """, branch.getId(), branch.getAddress().toString());
            branchesAsHTMLTemplate[index] = HTMLTemplate;
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
