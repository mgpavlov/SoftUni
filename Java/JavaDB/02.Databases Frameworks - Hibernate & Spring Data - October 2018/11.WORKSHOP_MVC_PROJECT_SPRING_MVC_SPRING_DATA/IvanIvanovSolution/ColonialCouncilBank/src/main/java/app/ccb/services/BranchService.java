package app.ccb.services;

public interface BranchService {

    Boolean branchesAreImported();

    String readBranchesJsonFile();

    String importBranches(String branchesJson);
}
