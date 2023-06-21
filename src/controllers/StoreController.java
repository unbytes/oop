package controllers;

import java.util.*;

import models.Branch;
import models.Store;

public class StoreController {

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
    
    public boolean authenticateStore(String password){
        if (password.equals(Store.getpassword())){
            return true;
        } else return false;
    }

    public void removeBranch(String branchUUID) {
        Branch branch = Store.getBranches().stream().filter(b -> b.getId().equals(branchUUID)).findFirst().orElse(null);
        Store.removeBranch(branch);
    }
}
