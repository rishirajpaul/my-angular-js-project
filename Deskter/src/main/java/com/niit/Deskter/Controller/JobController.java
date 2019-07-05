package com.niit.Deskter.Controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.Deskter.Dao.JobApplicationDao;
import com.niit.Deskter.Dao.JobDao;
import com.niit.Deskter.model.Job;
import com.niit.Deskter.model.JobApplication;
import com.niit.Deskter.model.UserDetails;


@RestController
public class JobController {

	// job status will be *New
	// JobApplication status will be * selected * pending * rejected 
	
	
	@Autowired
	Job job;
	@Autowired
	JobDao jobDao;
	@Autowired
	UserDetails userDetails;
	
	@Autowired
	JobApplication jobApplication;
	@Autowired
	JobApplicationDao jobApplicationDao;
	
	
	
	
	
	//********************************************JOB*****************************************************
	//****************************************************************************************************
	
	// 1 Get all job List....
	
	@RequestMapping(value="/jobs", method=RequestMethod.GET)
	public ResponseEntity<List<Job>> list(){
		List<Job> jobs=jobDao.list();
		if(jobs.isEmpty()){
			return new ResponseEntity<List<Job>>(jobs, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}
	
	// 2 Create new Job .....
	
		@RequestMapping(value="/newJob/", method=RequestMethod.POST)
		public ResponseEntity<Job> createJob(@RequestBody Job jobs,HttpSession session){
			System.out.println("Create Job");
			jobs.setJobDate(new Date(System.currentTimeMillis()));
			jobs.setStatus("Active");
			jobDao.saveJob(jobs);
			System.out.println("jobs" + jobs.getJobId());
			return new ResponseEntity<Job>(jobs,HttpStatus.OK);
		   }
		
		// 3 Get all information About a single job......
		
		@RequestMapping(value= "/jobDetails/{id}",method=RequestMethod.GET)
		public ResponseEntity<Job>getJobByJobId(@PathVariable("id")int id){
			Job job = jobDao.getJobByJobId(id);
			if (job == null){
				job = new Job();
				job.setErrorMessage("Job does not exist with id : " + id);
					return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
					
			}
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
		
		// 4 Update Job Details ........
		
		@RequestMapping(value="/updateJobDetails/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Job> updatejob(@PathVariable("id") int id, @RequestBody Job job){
			if(jobDao.list()==null){
				job =new Job();
				job.setErrorMessage("Job does not exist with id : " +job.getJobId());
				return new ResponseEntity<Job>(job, HttpStatus.NO_CONTENT);
			}
			jobDao.update(job);
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
		
		
		// 5 Delete A Job By Job Id...........
		
		@RequestMapping(value="/deleteJob/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Job>delete(@PathVariable("id")int id){
			Job job=jobDao.getJobByJobId(id);
			if(job == null){
				job = new Job();
				job.setErrorMessage("Job does not exist with id : " + id);
				return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
				
			}
			jobDao.delete(job);
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
		
		
		
		
		
	//**************************************JOBAPPLICATION**********************************************
	//**************************************************************************************************
		
		
	// 1 Get all jobApplication List....
	@RequestMapping(value="/jobApplications", method=RequestMethod.GET)
	public ResponseEntity<List<JobApplication>> listJobApplications(){
		List<JobApplication> jobapp=jobDao.listJobApplications();
		if(jobapp.isEmpty()){
			return new ResponseEntity<List<JobApplication>>(jobapp, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<JobApplication>>(jobapp, HttpStatus.OK);
 	}
	
	
	
	// 2 Create new JobApplication .....
		@RequestMapping(value="/newJobApplication/{id}", method=RequestMethod.POST)
		public ResponseEntity<JobApplication> createJobApplication(@RequestBody JobApplication jobapp,@PathVariable("id")int id,HttpSession session){
			System.out.println("Create JobApplication");
			
			UserDetails user=(UserDetails) session.getAttribute("loggedInUser");
			
			jobapp.setUserid(user.getUserId());
			jobapp.setJobid(id);
			jobapp.setStatus("Pending");
			jobApplicationDao.saveJobApplication(jobapp);
			System.out.println("jobapp" + jobapp.getJobApplicationId());
			return new ResponseEntity<JobApplication>(jobapp,HttpStatus.OK);
		   }
	
	
	
	// 3 Get all information About a single jobApplication......
	@RequestMapping(value= "/jobApplicationDetails/{id}",method=RequestMethod.GET)
	public ResponseEntity<JobApplication>getJobApplicationByJobApplicationId(@PathVariable("id")int id){
		JobApplication jobApp = jobApplicationDao.getJobApplicationByJobApplicationId(id);
		if (jobApp == null){
			jobApp = new JobApplication();
			jobApp.setErrorMessage("JobApplication does not exist with id : " + id);
				return new ResponseEntity<JobApplication>(jobApp, HttpStatus.NOT_FOUND);
				
		}
		return new ResponseEntity<JobApplication>(jobApp, HttpStatus.OK);
	}
	
	
	
	// 4 Update JobApplication Details ........
		@RequestMapping(value="/updateJobApplication/{id}", method=RequestMethod.PUT)
		public ResponseEntity<JobApplication> updatejobApplication(@PathVariable("id") int id, @RequestBody JobApplication jobApp){
			if(jobApplicationDao.listJobApplications()==null){
				jobApp =new JobApplication();
				jobApp.setErrorMessage("JobApplication does not exist with id : " +jobApp.getJobApplicationId());
				return new ResponseEntity<JobApplication>(jobApp, HttpStatus.NO_CONTENT);
			}
			jobApplicationDao.updateJobApplication(jobApp);
			return new ResponseEntity<JobApplication>(jobApp, HttpStatus.OK);
		}
	

		
		// 5 Show pending Job Application List......To User....By User Id....
		
		@RequestMapping(value= "/appliedJobs",method=RequestMethod.GET)
		public ResponseEntity<List<JobApplication>> getAppliedJobs(HttpSession session)
		{
			
			try
			{
			
				UserDetails user=(UserDetails) session.getAttribute("loggedInUser");
				return new ResponseEntity<List<JobApplication>>(jobApplicationDao.jobApplicationsByUserId(user.getUserId()),HttpStatus.OK);
			}
			catch(NullPointerException e)
			{ 
				e.printStackTrace(); 
				System.out.println("user not logged in");
				return new ResponseEntity<List<JobApplication>>(HttpStatus.NO_CONTENT);
			}
			
			
		}

		// 6 Show Selected Job Application List......To User....By User Id....
		
		@RequestMapping(value= "/selectedJobApplication/{id}",method=RequestMethod.GET)
		public ResponseEntity<List<JobApplication>>getSelectedJobApplicationByUserId(@PathVariable("id") String id){
			List<JobApplication> jobApplications=jobApplicationDao.getSelectedJobApplicationByUserId(id);
			if (jobApplications == null){
				jobApplication.setErrorMessage("Job Application does not exist with userId : " + id);
					return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.NOT_FOUND);
					
			}
			return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.OK);
		}
		
    
		// 7 Show Rejected Job Application List......To User....By User Id....
		
		@RequestMapping(value= "/rejectedJobApplication/{id}",method=RequestMethod.GET)
		public ResponseEntity<List<JobApplication>>getRejectetJobApplicationByUserId(@PathVariable("id") String id){
			List<JobApplication> jobApplications=jobApplicationDao.getRejectetJobApplicationByUserId(id);
			if (jobApplications == null){
				jobApplication.setErrorMessage("Job Application does not exist with userId : " + id);
					return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.NOT_FOUND);
					
			}
			return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.OK);
		}
	
		
		
		// 8 Show Call For Interview  Job Application List......To User....By User Id....
		
		@RequestMapping(value= "/callForInterviewJobApplication/{id}",method=RequestMethod.GET)
		public ResponseEntity<List<JobApplication>>getCFIJobApplicationByUserId(@PathVariable("id") String id){
			List<JobApplication> jobApplications=jobApplicationDao.getCFIJobApplicationByUserId(id);
			if (jobApplications == null){
				jobApplication.setErrorMessage("Job Application does not exist with userId : " + id);
					return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.NOT_FOUND);
					
			}
			return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.OK);
		}
		
		// 9 New Job Application Pending by Admin ...........
		
		@RequestMapping(value="/PendingJobApplicationByADMIN/{id}", method=RequestMethod.PUT)
		public ResponseEntity<JobApplication>pendingJobApplication(@PathVariable("id") int id,HttpSession session)
		{	
			
			JobApplication jobApp=jobApplicationDao.getJobApplicationByJobApplicationId(id);
				if(((UserDetails)session.getAttribute("loggedInUser")).getRole().equals("ADMIN")&&
						(jobApp!=null))
				{
					jobApp.setStatus("Pending");
					jobApplicationDao.updateJobApplication(jobApp);
							return new ResponseEntity<JobApplication>(jobApp,HttpStatus.OK);
						}
				else
				{
							
							return new ResponseEntity<JobApplication>(jobApp,HttpStatus.NOT_FOUND);
						}
				
			}
		
		   // 10 New Job Application Selected by Admin ...........
		
		@RequestMapping(value="/SelectedJobApplicationByADMIN/{id}", method=RequestMethod.PUT)
		public ResponseEntity<JobApplication>selectedJobApplication(@PathVariable("id") int id,HttpSession session)
		{	
					
		   JobApplication jobApp=jobApplicationDao.getJobApplicationByJobApplicationId(id);
				if(((UserDetails)session.getAttribute("loggedInUser")).getRole().equals("ADMIN")&&(jobApp!=null))
						{
							jobApp.setStatus("Call For Interview");
							jobApplicationDao.updateJobApplication(jobApp);
									return new ResponseEntity<JobApplication>(jobApp,HttpStatus.OK);
								}
						else
						{
									
									return new ResponseEntity<JobApplication>(jobApp,HttpStatus.NOT_FOUND);
								}
						
					}
				
				// 11 New Job Application Rejected by Admin ...........
				
				@RequestMapping(value="/RejectedJobApplicationByADMIN/{id}", method=RequestMethod.PUT)
				public ResponseEntity<JobApplication>rejectedJobApplication(@PathVariable("id") int id,HttpSession session)
				{	
					
					JobApplication jobApp=jobApplicationDao.getJobApplicationByJobApplicationId(id);
						if(((UserDetails)session.getAttribute("loggedInUser")).getRole().equals("ADMIN")&&
								(jobApp!=null))
						{
							jobApp.setStatus("Rejected");
							jobApplicationDao.updateJobApplication(jobApp);
									return new ResponseEntity<JobApplication>(jobApp,HttpStatus.OK);
								}
						else
						{
									
									return new ResponseEntity<JobApplication>(jobApp,HttpStatus.NOT_FOUND);
								}
						
					}
				
				// 12 New Job Application Call For Interview by Admin ...........
				
				@RequestMapping(value="/CallForInterviewJobApplicationByADMIN/{id}", method=RequestMethod.PUT)
				public ResponseEntity<JobApplication>callForInterviewJobApplication(@PathVariable("id") int id,HttpSession session)
				{	
					
					JobApplication jobApp=jobApplicationDao.getJobApplicationByJobApplicationId(id);
						if(((UserDetails)session.getAttribute("loggedInUser")).getRole().equals("ADMIN")&&
								(jobApp!=null))
						{
							jobApp.setStatus("Call For Interview");
							jobApplicationDao.updateJobApplication(jobApp);
									return new ResponseEntity<JobApplication>(jobApp,HttpStatus.OK);
								}
						else
						{
									
									return new ResponseEntity<JobApplication>(jobApp,HttpStatus.NOT_FOUND);
								}
						
					}
				
				
				// 13 List Of Job Application By Job Id.....
				
				@RequestMapping(value= "/JobApplicationByJobId/{id}",method=RequestMethod.GET)
				public ResponseEntity<List<JobApplication>>jobApplicationsByJobId(@PathVariable("id") int id){
					List<JobApplication> jobApplications=jobApplicationDao.jobApplicationsByJobId(id);
					if (jobApplications == null){
						jobApplication.setErrorMessage("Job Application does not exist with jobId : " + id);
							return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.NOT_FOUND);
							
					}
					return new ResponseEntity<List<JobApplication>>(jobApplications, HttpStatus.OK);
				}
				
				
				
				
					
				
					
					
				}
		

