package com.niit.Deskter.Dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.Deskter.model.JobApplication;

@Repository
public interface JobApplicationDao {
	
	public boolean saveJobApplication(JobApplication jobApplication);
	public boolean updateJobApplication(JobApplication jobApplication);
	public JobApplication getJobApplicationByJobApplicationId(int jobApplicationId);
	public List<JobApplication> jobApplicationsByJobId(int jobId);
	public List<JobApplication> listJobApplications();
	public List<JobApplication> jobApplicationsByUserId(String userId);
	public boolean isJobExist(String userId, int jobId);
	public List<JobApplication> getMyAppliedJobs(String userid);//public List<JobApplication> jobApplicationsByUserId(String userId); same method
	public List<JobApplication> getSelectedJobApplicationByUserId(String userId);
	public List<JobApplication> getCFIJobApplicationByUserId(String userId);//cfi= call for interview
	public List<JobApplication> getRejectetJobApplicationByUserId(String userId);

}
