package exam.repository;

import exam.domain.entities.JobApplication;
import exam.domain.entities.User;

public interface JobAppRepository extends GenericRepository<JobApplication, String> {

    JobApplication findBySector(String username);

    Long countOfJobs();
}
