package exam.service;

import exam.domain.entities.JobApplication;
import exam.domain.models.service.JobAppServiceModel;
import exam.repository.JobAppRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobAppServiceImpl implements JobAppService {

    private final JobAppRepository jobAppRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobAppServiceImpl(JobAppRepository jobAppRepository, ModelMapper modelMapper) {
        this.jobAppRepository = jobAppRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean jobAppRegister(JobAppServiceModel jobAppServiceModel) {
        JobApplication jobApp = this.modelMapper.map(jobAppServiceModel, JobApplication.class);

        return this.jobAppRepository.registerEntity(jobApp) != null;
    }

    @Override
    public boolean jobAppUpdate(JobAppServiceModel jobAppServiceModel) {
        JobApplication jobApp = this.jobAppRepository.updateEntity(this.modelMapper.map(jobAppServiceModel, JobApplication.class));

        return this.jobAppRepository.updateEntity(jobApp) != null;
    }

    @Override
    public boolean jobAppDelete(JobAppServiceModel jobAppServiceModel) {
        return this.jobAppRepository.deleteEntity(jobAppServiceModel.getId());
    }


    @Override
    public JobAppServiceModel findJobAppByJobAppSector(String sector) {
        return this.modelMapper.map(this.jobAppRepository.findBySector(sector), JobAppServiceModel.class);
    }

    @Override
    public List<JobAppServiceModel> findAllJobApps() {
        return this.jobAppRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, JobAppServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public JobAppServiceModel findJobAppById(String id) {
        return modelMapper.map(this.jobAppRepository.findById(id), JobAppServiceModel.class);
    }
}
