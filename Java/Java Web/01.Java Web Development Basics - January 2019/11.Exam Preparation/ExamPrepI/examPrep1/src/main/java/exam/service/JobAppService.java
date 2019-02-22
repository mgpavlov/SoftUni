package exam.service;

import exam.domain.models.service.JobAppServiceModel;

import java.util.List;

public interface JobAppService {

    boolean jobAppRegister(JobAppServiceModel jobAppServiceModel);

    boolean jobAppUpdate(JobAppServiceModel jobAppServiceModel);

    boolean jobAppDelete(JobAppServiceModel jobAppServiceModel);

    JobAppServiceModel findJobAppByJobAppSector(String sector);

    JobAppServiceModel findJobAppById(String id);

    List<JobAppServiceModel> findAllJobApps();
}
