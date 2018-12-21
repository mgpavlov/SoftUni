package app.ccb.repositories;

import app.ccb.domain.dtos.BranchDto;
import app.ccb.domain.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Integer> {

    Branch findOneByName(String branchName);

    @Query("SELECT new app.ccb.domain.dtos.BranchDto(b.name) FROM Branch AS b")
    List<BranchDto> someTest();

}
