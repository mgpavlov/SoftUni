package app.ccb.services;

import app.ccb.constants.ResourcesPath;
import app.ccb.domain.dtos.BranchDto;
import app.ccb.domain.entities.Branch;
import app.ccb.repositories.BranchRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {


    private final BranchRepository branchRepository;
    private final ModelMapper mapper;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository,
                             ModelMapper mapper,
                             FileUtil fileUtil,
                             Gson gson,
                             ValidationUtil validationUtil) {
        this.branchRepository = branchRepository;
        this.mapper = mapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() {
        return this.fileUtil.readFile(ResourcesPath.JSON.BRANCHES);
    }

    @Override
    public String importBranches(String branchesJson) {
        List<String> responseMessages = new ArrayList<>();
        try {
            BranchDto[] branchDtos = this.gson.fromJson(branchesJson, BranchDto[].class);
            List<Branch> successCreated = new ArrayList<>(branchDtos.length);
            for (BranchDto branchDto : branchDtos) {
                if (this.validationUtil.isValid(branchDto)) {
                    Branch branch = this.mapper.map(branchDto, Branch.class);
                    successCreated.add(branch);
                    responseMessages.add(String.format("Successfully imported %s – %s.","Branch",branch.getName()));
                } else {
                    responseMessages.add("Error: Incorrect Data!");
                }
            }

            this.branchRepository.saveAll(successCreated);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseMessages.stream().collect(Collectors.joining(System.lineSeparator()));
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test() {
        List<BranchDto> dt = this.branchRepository.someTest();
        System.out.println();
    }
}
