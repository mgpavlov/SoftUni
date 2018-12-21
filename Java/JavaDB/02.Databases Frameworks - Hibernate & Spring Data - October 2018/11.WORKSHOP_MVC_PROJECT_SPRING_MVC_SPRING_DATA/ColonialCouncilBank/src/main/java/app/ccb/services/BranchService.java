package app.ccb.services;

import org.springframework.stereotype.Service;

import java.io.IOException;

public interface BranchService {

    Boolean branchesAreImported();

    String readBranchesJsonFile() throws IOException;

    String importBranches(String branchesJson);
}
