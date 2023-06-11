package controllers;

import java.util.*;

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
}
